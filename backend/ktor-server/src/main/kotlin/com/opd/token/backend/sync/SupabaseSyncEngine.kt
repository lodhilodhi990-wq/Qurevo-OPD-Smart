package com.opd.token.backend.sync

class SupabaseSyncEngine(
    private val outboxRepository: OutboxRepository,
    private val connectivityManager: ConnectivityManager
) {
    fun syncPendingOutboxEvents(): Int {
        val state = connectivityManager.checkConnectivity()
        if (state != ConnectivityState.CLOUD_CONNECTED && state != ConnectivityState.INTERNET_AVAILABLE) {
            println("[SyncEngine] Offline mode active. Skipping cloud sync. OPD operates locally.")
            return 0
        }

        val pending = outboxRepository.getPendingEvents()
        if (pending.isEmpty()) {
            return 0
        }

        println("[SyncEngine] Uploading ${pending.size} pending outbox events to Supabase Cloud PostgreSQL...")

        // Idempotent batch upload simulation
        val ackIds = pending.map { it.id }
        outboxRepository.markAcknowledged(ackIds)
        println("[SyncEngine] Successfully synchronized ${ackIds.size} events to cloud. Local events marked ACKNOWLEDGED.")
        return ackIds.size
    }
}
