package com.opd.token.shared.core.security

import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

class AesGcmCipher {

    private val GCM_IV_LENGTH = 12 // 12 bytes IV recommended for GCM
    private val GCM_TAG_LENGTH = 128 // 128 bit authentication tag

    fun encrypt(plainBytes: ByteArray, secretKeyHex: String): ByteArray {
        val keyBytes = secretKeyHex.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
        val key: SecretKey = SecretKeySpec(keyBytes, "AES")

        val iv = ByteArray(GCM_IV_LENGTH)
        SecureRandom().nextBytes(iv)

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val parameterSpec = GCMParameterSpec(GCM_TAG_LENGTH, iv)
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec)

        val cipherBytes = cipher.doFinal(plainBytes)

        // Prepend IV to ciphertext (IV + CipherText)
        val combined = ByteArray(iv.size + cipherBytes.size)
        System.arraycopy(iv, 0, combined, 0, iv.size)
        System.arraycopy(cipherBytes, 0, combined, iv.size, cipherBytes.size)

        return combined
    }

    fun decrypt(encryptedBytes: ByteArray, secretKeyHex: String): ByteArray {
        if (encryptedBytes.size <= GCM_IV_LENGTH) {
            throw IllegalArgumentException("Invalid encrypted payload size")
        }

        val keyBytes = secretKeyHex.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
        val key: SecretKey = SecretKeySpec(keyBytes, "AES")

        val iv = ByteArray(GCM_IV_LENGTH)
        System.arraycopy(encryptedBytes, 0, iv, 0, GCM_IV_LENGTH)

        val cipherTextSize = encryptedBytes.size - GCM_IV_LENGTH
        val cipherBytes = ByteArray(cipherTextSize)
        System.arraycopy(encryptedBytes, GCM_IV_LENGTH, cipherBytes, 0, cipherTextSize)

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val parameterSpec = GCMParameterSpec(GCM_TAG_LENGTH, iv)
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec)

        return cipher.doFinal(cipherBytes)
    }

    fun generate256BitKeyHex(): String {
        val keyBytes = ByteArray(32) // 256 bits
        SecureRandom().nextBytes(keyBytes)
        return keyBytes.joinToString("") { "%02x".format(it) }
    }
}
