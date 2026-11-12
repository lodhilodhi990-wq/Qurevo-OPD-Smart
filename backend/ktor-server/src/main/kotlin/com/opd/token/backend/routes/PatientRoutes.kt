package com.opd.token.backend.routes

import com.opd.token.backend.repository.TokenRepository
import com.opd.token.shared.core.ApiEndpoints
import com.opd.token.shared.models.Patient
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.patientRoutes(repository: TokenRepository) {
    route(ApiEndpoints.PATIENTS) {
        get {
            call.respond(repository.searchPatients(""))
        }
        post {
            val patient = call.receive<Patient>()
            val created = repository.addPatient(patient)
            call.respond(HttpStatusCode.Created, created)
        }
        get("/search") {
            val q = call.request.queryParameters["q"] ?: ""
            call.respond(repository.searchPatients(q))
        }
    }
}
