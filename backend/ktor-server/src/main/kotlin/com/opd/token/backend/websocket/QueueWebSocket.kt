package com.opd.token.backend.websocket

import io.ktor.websocket.*
import kotlinx.coroutines.channels.ClosedSendChannelException
import java.util.Collections
import java.util.concurrent.ConcurrentHashMap

object QueueWebSocket {
    private val sessions = Collections.newSetFromMap(ConcurrentHashMap<DefaultWebSocketSession, Boolean>())

    fun addSession(session: DefaultWebSocketSession) {
        sessions.add(session)
    }

    fun removeSession(session: DefaultWebSocketSession) {
        sessions.remove(session)
    }

    suspend fun broadcast(message: String) {
        sessions.forEach { session ->
            try {
                session.send(Frame.Text(message))
            } catch (e: ClosedSendChannelException) {
                sessions.remove(session)
            }
        }
    }
}
