package com.opd.token.backend.sync

import kotlinx.serialization.Serializable

@Serializable
data class DevicePresence(
    val deviceId: String,
    val hospitalId: String,
    val role: String,
    val lastActive: String,
    val isOnline: Boolean
)

class FirebaseControlPlane {
    fun updateDevicePresence(presence: DevicePresence) {
        // Spark Free Plan non-authoritative control-plane presence update
        println("[FirebaseControlPlane] Updated presence for device: ${presence.deviceId} (${presence.role})")
    }

    fun publishNotificationMetadata(title: String, body: String) {
        // FCM notification dispatch metadata on Spark Free tier
        println("[FirebaseControlPlane] FCM Notification: $title - $body")
    }
}
