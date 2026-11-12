package com.opd.token.backend.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class RegisterHospitalRequest(
    val hospitalName: String,
    val city: String,
    val adminUsername: String,
    val planName: String = "FREE_LAN"
)

@Serializable
data class PaymentRecordDto(
    val id: String,
    val hospitalName: String,
    val amount: Double,
    val method: String,
    val ref: String,
    val isVerified: Boolean
)

fun Route.saasAdminRoutes() {
    route("/api/saas") {
        get("/hospitals") {
            call.respond(listOf(
                mapOf("id" to "hosp-1", "name" to "City General Hospital", "city" to "Lahore", "plan" to "FREE_LAN", "status" to "ACTIVE"),
                mapOf("id" to "hosp-2", "name" to "Mayo Care Clinic", "city" to "Karachi", "plan" to "FREE_LAN", "status" to "ACTIVE")
            ))
        }

        post("/hospitals") {
            val req = call.receive<RegisterHospitalRequest>()
            call.respond(HttpStatusCode.Created, mapOf(
                "message" to "Hospital registered successfully",
                "hospitalId" to "hosp-${System.currentTimeMillis()}",
                "name" to req.hospitalName
            ))
        }

        get("/payments") {
            call.respond(listOf(
                PaymentRecordDto("pay-101", "Mayo Care Clinic", 5000.0, "JazzCash", "TXN-882391", true),
                PaymentRecordDto("pay-102", "City General Hospital", 0.0, "Free LAN Plan", "N/A", true)
            ))
        }

        post("/payments/{id}/verify") {
            val id = call.parameters["id"]
            call.respond(mapOf("status" to "VERIFIED", "paymentId" to id))
        }
    }
}
