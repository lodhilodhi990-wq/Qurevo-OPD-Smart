package com.opd.token.backend.routes

import com.opd.token.shared.core.ApiEndpoints
import com.opd.token.shared.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes() {
    post(ApiEndpoints.LOGIN) {
        val request = call.receive<AuthRequest>()
        if (request.username.isNotBlank() && request.password.isNotBlank()) {
            val role = if (request.username.contains("doc")) UserRole.DOCTOR else UserRole.RECEPTIONIST
            val user = User(
                id = "usr-1",
                username = request.username,
                fullName = if (role == UserRole.DOCTOR) "Dr. Ahmed" else "Receptionist Staff",
                role = role,
                hospitalId = "hosp-1",
                doctorId = if (role == UserRole.DOCTOR) "doc-1" else null
            )
            call.respond(AuthResponse(token = "jwt-mock-token-xyz", user = user))
        } else {
            call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "Invalid credentials"))
        }
    }
}
