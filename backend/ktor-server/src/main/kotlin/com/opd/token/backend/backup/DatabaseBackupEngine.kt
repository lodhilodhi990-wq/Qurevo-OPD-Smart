package com.opd.token.backend.backup

import com.opd.token.shared.core.security.AesGcmCipher
import com.opd.token.shared.models.*
import java.io.File
import java.security.MessageDigest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class DatabaseBackupEngine(
    private val backupDirectoryPath: String = "E:/QUREVO/backups",
    private val cipher: AesGcmCipher = AesGcmCipher()
) {
    private val lock = ReentrantLock()
    private val backupMetadataMap = ConcurrentHashMap<String, BackupMetadata>()
    private var defaultKeyHex = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef" // 256-bit test key

    init {
        val dir = File(backupDirectoryPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
    }

    fun getDefaultKeyHex(): String = defaultKeyHex

    fun createBackup(dbDataBytes: ByteArray, secretKeyHex: String = defaultKeyHex): BackupMetadata {
        lock.withLock {
            val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
            val backupId = UUID.randomUUID().toString()
            val filename = "opd_backup_$timestamp.enc"
            val file = File(backupDirectoryPath, filename)

            // Calculate plaintext SHA-256 checksum
            val checksum = calculateSha256(dbDataBytes)

            // Encrypt database snapshot using AES-256-GCM
            val encryptedBytes = cipher.encrypt(dbDataBytes, secretKeyHex)
            file.writeBytes(encryptedBytes)

            val metadata = BackupMetadata(
                id = backupId,
                filename = filename,
                timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                checksumSha256 = checksum,
                sizeBytes = encryptedBytes.size.toLong(),
                isEncrypted = true,
                status = BackupStatus.COMPLETED
            )

            backupMetadataMap[backupId] = metadata
            return metadata
        }
    }

    fun listBackups(): List<BackupMetadata> {
        return backupMetadataMap.values.sortedByDescending { it.timestamp }
    }

    fun verifyBackupIntegrity(backupId: String, secretKeyHex: String = defaultKeyHex): Boolean {
        val metadata = backupMetadataMap[backupId] ?: return false
        val file = File(backupDirectoryPath, metadata.filename)
        if (!file.exists()) return false

        return try {
            val encryptedBytes = file.readBytes()
            val decryptedBytes = cipher.decrypt(encryptedBytes, secretKeyHex)
            val currentChecksum = calculateSha256(decryptedBytes)
            currentChecksum == metadata.checksumSha256
        } catch (e: Exception) {
            false
        }
    }

    fun restoreBackup(backupId: String, secretKeyHex: String = defaultKeyHex): ByteArray {
        lock.withLock {
            val metadata = backupMetadataMap[backupId] ?: throw IllegalArgumentException("Backup metadata not found")
            val file = File(backupDirectoryPath, metadata.filename)
            if (!file.exists()) throw IllegalStateException("Backup file does not exist on disk")

            val encryptedBytes = file.readBytes()
            val decryptedBytes = cipher.decrypt(encryptedBytes, secretKeyHex)

            val currentChecksum = calculateSha256(decryptedBytes)
            if (currentChecksum != metadata.checksumSha256) {
                throw IllegalStateException("Backup SHA-256 checksum verification failed! File is corrupted.")
            }

            // Return plain DB bytes for atomic swap
            return decryptedBytes
        }
    }

    fun pruneOldBackups(retentionDays: Int = 30): Int {
        lock.withLock {
            val cutoff = LocalDateTime.now().minusDays(retentionDays.toLong())
            var prunedCount = 0

            backupMetadataMap.values.forEach { metadata ->
                try {
                    val dt = LocalDateTime.parse(metadata.timestamp, DateTimeFormatter.ISO_DATE_TIME)
                    if (dt.isBefore(cutoff)) {
                        val file = File(backupDirectoryPath, metadata.filename)
                        if (file.exists()) file.delete()
                        backupMetadataMap.remove(metadata.id)
                        prunedCount++
                    }
                } catch (e: Exception) {
                    // Ignore date parse exception
                }
            }
            return prunedCount
        }
    }

    private fun calculateSha256(bytes: ByteArray): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(bytes)
        return hash.joinToString("") { "%02x".format(it) }
    }
}
