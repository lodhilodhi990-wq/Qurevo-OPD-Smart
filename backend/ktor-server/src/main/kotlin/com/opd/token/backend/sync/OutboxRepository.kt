package com.opd.token.backend.sync

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Serializable
data class OutboxEvent(
    val id: String,
    val hospitalId: String,
    val entityType: String,
    val entityId: String,
    val operationType: String,
    val payload: String,
    var syncStatus: String = OutboxSyncStatus.PENDING.name,
    val localTimestamp: String = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
)

class OutboxRepository {
    private val outboxList = mutableListOf<OutboxEvent>()

    @Synchronized
    fun addEvent(hospitalId: String, entityType: String, entityId: String, operationType: String, payload: Any): OutboxEvent {
        val jsonPayload = if (payload is String) payload else Json.encodeToString(payload)
        val event = OutboxEvent(
            id = UUID.randomUUID().toString(),
            hospitalId = hospitalId,
            entityType = entityType,
            entityId = entityId,
            operationType = operationType,
            payload = jsonPayload
        )
        outboxList.add(event)
        return event
    }

    @Synchronized
    fun getPendingEvents(): List<OutboxEvent> {
        return outboxList.filter { it.syncStatus == OutboxSyncStatus.PENDING.name || it.syncStatus == OutboxSyncStatus.RETRY_REQUIRED.name }
    }

    @Synchronized
    fun markAcknowledged(eventIds: List<String>) {
        outboxList.forEach { event ->
            if (eventIds.contains(event.id)) {
                event.syncStatus = OutboxSyncStatus.ACKNOWLEDGED.name
            }
        }
    }
}
