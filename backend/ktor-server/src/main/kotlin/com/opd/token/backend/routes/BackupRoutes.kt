package com.opd.token.backend.routes

import com.opd.token.backend.backup.DatabaseBackupEngine
import com.opd.token.shared.core.ApiEndpoints
import com.opd.token.shared.models.RestoreRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.backupRoutes(backupEngine: DatabaseBackupEngine) {
    route(ApiEndpoints.BACKUPS) {
        get {
            call.respond(backupEngine.listBackups())
        }
        post {
            val dummyDbBytes = "QUREVO_DATABASE_SNAPSHOT_BINARY_DATA".toByteArray(Charsets.UTF_8)
            val metadata = backupEngine.createBackup(dummyDbBytes)
            call.respond(HttpStatusCode.Created, metadata)
        }
        post("/verify") {
            val params = call.receive<Map<String, String>>()
            val backupId = params["backupId"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val keyHex = params["keyHex"] ?: backupEngine.getDefaultKeyHex()
            val isValid = backupEngine.verifyBackupIntegrity(backupId, keyHex)
            call.respond(mapOf("backupId" to backupId, "isValid" to isValid))
        }
        post("/restore") {
            val req = call.receive<RestoreRequest>()
            try {
                val dbBytes = backupEngine.restoreBackup(req.backupId, req.encryptionKeyHex)
                call.respond(HttpStatusCode.OK, mapOf("status" to "RESTORED", "restoredSizeBytes" to dbBytes.size))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to (e.message ?: "Restore failed")))
            }
        }
    }
}
