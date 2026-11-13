package com.opd.token.backend.routes

import com.opd.token.backend.repository.TokenRepository
import com.opd.token.backend.websocket.QueueWebSocket
import com.opd.token.shared.core.ApiEndpoints
import com.opd.token.shared.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Route.tokenRoutes(repository: TokenRepository) {
    route(ApiEndpoints.TOKENS) {
        get {
            call.respond(repository.getTokens())
        }
        post {
            val params = call.receive<Map<String, String>>()
            val patientId = params["patientId"] ?: "p-1"
            val doctorId = params["doctorId"] ?: "doc-1"
            val departmentId = params["departmentId"] ?: "dep-1"
            val priorityStr = params["priority"] ?: "NORMAL"
            val priority = try { TokenPriority.valueOf(priorityStr) } catch (e: Exception) { TokenPriority.NORMAL }

            val token = repository.generateToken(patientId, doctorId, departmentId, priority)

            QueueWebSocket.broadcast(Json.encodeToString(WebSocketMessage("TOKEN_CREATED", token.tokenNumber)))
            call.respond(HttpStatusCode.Created, token)
        }
        post("/{id}/priority") {
            val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val req = call.receive<PriorityElevationRequest>()
            val updated = repository.elevateTokenPriority(id, req.newPriority, req.reason, req.requestedBy)
            if (updated != null) {
                QueueWebSocket.broadcast(Json.encodeToString(WebSocketMessage("TOKEN_PRIORITY_ELEVATED", updated.tokenNumber)))
                call.respond(updated)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        post("/next") {
            val doctorId = call.request.queryParameters["doctorId"] ?: "doc-1"
            val nextToken = repository.getNextWaitingToken(doctorId)
            if (nextToken != null) {
                QueueWebSocket.broadcast(Json.encodeToString(WebSocketMessage("TOKEN_CALLED", nextToken.tokenNumber)))
                call.respond(nextToken)
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("message" to "No waiting patients in queue"))
            }
        }
        post("/{id}/recall") {
            val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val updated = repository.updateTokenStatus(id, TokenStatus.CALLED)
            if (updated != null) {
                QueueWebSocket.broadcast(Json.encodeToString(WebSocketMessage("TOKEN_RECALLED", updated.tokenNumber)))
                call.respond(updated)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        post("/{id}/hold") {
            val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val updated = repository.updateTokenStatus(id, TokenStatus.HELD)
            if (updated != null) call.respond(updated) else call.respond(HttpStatusCode.NotFound)
        }
        post("/{id}/skip") {
            val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val updated = repository.updateTokenStatus(id, TokenStatus.SKIPPED)
            if (updated != null) call.respond(updated) else call.respond(HttpStatusCode.NotFound)
        }
        post("/{id}/complete") {
            val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val updated = repository.updateTokenStatus(id, TokenStatus.COMPLETED)
            if (updated != null) {
                QueueWebSocket.broadcast(Json.encodeToString(WebSocketMessage("TOKEN_COMPLETED", updated.tokenNumber)))
                call.respond(updated)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
