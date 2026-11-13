package com.opd.token.backend.routes

import com.opd.token.shared.core.sms.LocalHttpSmsGatewayAdapter
import com.opd.token.shared.core.sms.SmsConfiguration
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.smsRoutes() {
    var activeSmsConfig = SmsConfiguration()

    route("/api/v1/sms") {
        get("/config") {
            call.respond(activeSmsConfig)
        }
        post("/config") {
            val req = call.receive<SmsConfiguration>()
            activeSmsConfig = req
            call.respond(HttpStatusCode.OK, activeSmsConfig)
        }
        post("/test") {
            val params = call.receive<Map<String, String>>()
            val phone = params["phone"] ?: "03001234567"
            val adapter = LocalHttpSmsGatewayAdapter(activeSmsConfig)
            val success = adapter.sendSms(phone, "QUREVO Test SMS: Gateway connection verified successfully.")
            call.respond(mapOf("success" to success, "phone" to phone))
        }
    }
}
