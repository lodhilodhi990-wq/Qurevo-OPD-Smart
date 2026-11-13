package com.opd.token.shared.models

import kotlinx.serialization.Serializable

@Serializable
enum class BackupStatus {
    COMPLETED,
    FAILED,
    IN_PROGRESS,
    CORRUPTED
}

@Serializable
data class BackupMetadata(
    val id: String,
    val filename: String,
    val timestamp: String,
    val checksumSha256: String,
    val sizeBytes: Long,
    val isEncrypted: Boolean = true,
    val status: BackupStatus = BackupStatus.COMPLETED
)

@Serializable
data class BackupConfiguration(
    val autoBackupEnabled: Boolean = true,
    val cronSchedule: String = "0 0 * * *", // Daily at midnight
    val retentionDays: Int = 30,
    val backupDirectory: String = "E:/QUREVO/backups"
)

@Serializable
data class RestoreRequest(
    val backupId: String,
    val encryptionKeyHex: String,
    val forceRestore: Boolean = false
)
