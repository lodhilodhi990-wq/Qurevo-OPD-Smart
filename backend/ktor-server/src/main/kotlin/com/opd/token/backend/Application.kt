package com.opd.token.backend

import com.opd.token.backend.database.DatabaseFactory
import com.opd.token.backend.repository.TokenRepository
import com.opd.token.backend.routes.*
import com.opd.token.backend.sync.ConnectivityManager
import com.opd.token.backend.sync.OutboxRepository
import com.opd.token.backend.sync.SupabaseSyncEngine
import com.opd.token.backend.websocket.QueueWebSocket
import com.opd.token.shared.core.ApiEndpoints
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.serialization.json.Json
import java.time.Duration

fun main() {
    embeddedServer(Netty, port = ApiEndpoints.DEFAULT_PORT, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()

    val outboxRepository = OutboxRepository()
    val repository = TokenRepository(outboxRepository)
    val connectivityManager = ConnectivityManager()
    val syncEngine = SupabaseSyncEngine(outboxRepository, connectivityManager)

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    install(CORS) {
        anyHost()
    }

    routing {
        get("/") {
            call.respondText("OPD Smart Token & Hospital Platform Server is running")
        }
        get("${ApiEndpoints.BASE_PATH}/health") {
            val syncCount = syncEngine.syncPendingOutboxEvents()
            call.respondText("""{"status":"UP","system":"OPD Smart Token Server","mode":"HYBRID_OFFLINE_ONLINE","synced_events":$syncCount}""")
        }

        webSocket("/ws") {
            QueueWebSocket.addSession(this)
            try {
                for (frame in incoming) {
                    // Frame receiver
                }
            } finally {
                QueueWebSocket.removeSession(this)
            }
        }

        authRoutes()
        patientRoutes(repository)
        tokenRoutes(repository)
        saasAdminRoutes()
    }
}
