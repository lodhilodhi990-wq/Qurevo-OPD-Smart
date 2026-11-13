package com.opd.token.backend

import com.opd.token.backend.backup.DatabaseBackupEngine
import com.opd.token.shared.core.security.AesGcmCipher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.File

class DatabaseBackupEngineTest {

    private lateinit var backupEngine: DatabaseBackupEngine
    private val cipher = AesGcmCipher()
    private val testDir = "build/test_backups"

    @Before
    fun setUp() {
        val dir = File(testDir)
        if (dir.exists()) {
            dir.deleteRecursively()
        }
        backupEngine = DatabaseBackupEngine(backupDirectoryPath = testDir, cipher = cipher)
    }

    @Test
    fun testCreateBackup_encryptsBytesWithAes256GcmAndCalculatesChecksum() {
        val originalData = "PATIENT_DATABASE_SNAPSHOT_BINARY_BYTES_12345".toByteArray(Charsets.UTF_8)
        val metadata = backupEngine.createBackup(originalData)

        assertNotNull(metadata)
        assertTrue(metadata.sizeBytes > originalData.size) // AES-GCM IV (12 bytes) + tag (16 bytes)
        assertTrue(metadata.isEncrypted)
        assertNotNull(metadata.checksumSha256)
    }

    @Test
    fun testVerifyBackupIntegrity_returnsTrueForValidBackup() {
        val originalData = "VALID_DB_DATA".toByteArray(Charsets.UTF_8)
        val metadata = backupEngine.createBackup(originalData)

        val isValid = backupEngine.verifyBackupIntegrity(metadata.id)
        assertTrue(isValid)
    }

    @Test
    fun testRestoreBackup_decryptsAndReturnsOriginalData() {
        val originalData = "SENSITIVE_PATIENT_RECORDS_DATA".toByteArray(Charsets.UTF_8)
        val keyHex = backupEngine.getDefaultKeyHex()
        val metadata = backupEngine.createBackup(originalData, keyHex)

        val restoredBytes = backupEngine.restoreBackup(metadata.id, keyHex)
        assertArrayEquals(originalData, restoredBytes)
    }

    @Test
    fun testRestoreBackup_rejectsWrongKey() {
        val originalData = "SECRET_PATIENT_DATA".toByteArray(Charsets.UTF_8)
        val correctKey = backupEngine.getDefaultKeyHex()
        val metadata = backupEngine.createBackup(originalData, correctKey)

        val wrongKey = cipher.generate256BitKeyHex()

        try {
            backupEngine.restoreBackup(metadata.id, wrongKey)
            fail("Expected exception for wrong decryption key")
        } catch (e: Exception) {
            assertTrue(e is javax.crypto.AEADBadTagException || e is IllegalArgumentException || e is IllegalStateException)
        }
    }

    @Test
    fun testRestoreBackup_rejectsCorruptedFile() {
        val originalData = "UNTOUCHED_DATA".toByteArray(Charsets.UTF_8)
        val keyHex = backupEngine.getDefaultKeyHex()
        val metadata = backupEngine.createBackup(originalData, keyHex)

        // Corrupt file on disk
        val file = File(testDir, metadata.filename)
        val fileBytes = file.readBytes()
        fileBytes[fileBytes.size - 1] = (fileBytes[fileBytes.size - 1] + 1).toByte()
        file.writeBytes(fileBytes)

        try {
            backupEngine.restoreBackup(metadata.id, keyHex)
            fail("Expected exception for corrupted backup file")
        } catch (e: Exception) {
            assertTrue(e is javax.crypto.AEADBadTagException || e is IllegalStateException)
        }
    }
}
