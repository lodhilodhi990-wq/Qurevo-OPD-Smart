package com.opd.token.backend.sync

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

enum class OutboxSyncStatus {
    PENDING,
    UPLOADING,
    ACKNOWLEDGED,
    RETRY_REQUIRED,
    CONFLICT,
    FAILED_PERMANENTLY
}

object SyncOutboxTable : Table("sync_outbox") {
    val id = varchar("id", 36)
    val hospitalId = varchar("hospital_id", 36)
    val deviceId = varchar("device_id", 36).nullable()
    val entityType = varchar("entity_type", 50) // TOKEN, PATIENT, VISIT, QUEUE_EVENT
    val entityId = varchar("entity_id", 36)
    val operationType = varchar("operation_type", 30) // CREATE, UPDATE, DELETE
    val eventVersion = integer("event_version").default(1)
    val payload = text("payload")
    val syncStatus = varchar("sync_status", 30).default(OutboxSyncStatus.PENDING.name)
    val localTimestamp = datetime("local_timestamp").default(LocalDateTime.now())

    override val primaryKey = PrimaryKey(id)
}
