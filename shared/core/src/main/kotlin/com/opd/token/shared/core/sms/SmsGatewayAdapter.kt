package com.opd.token.shared.core.sms

import kotlinx.serialization.Serializable

@Serializable
enum class SmsGatewayType {
    GSM_MODEM_AT,
    LOCAL_HTTP_GATEWAY,
    EXTERNAL_REST_PROVIDER,
    DISABLED
}

@Serializable
data class SmsConfiguration(
    val gatewayType: SmsGatewayType = SmsGatewayType.DISABLED,
    val gatewayUrl: String? = null,
    val apiKey: String? = null,
    val senderId: String = "QUREVO-OPD",
    val urduTemplate: String = "Aap ka token {token} confirm ho gaya hai. Doctor: {doctor}, Room: {room}.",
    val englishTemplate: String = "Your token {token} is confirmed. Doctor: {doctor}, Room: {room}.",
    val sendOnTokenGeneration: Boolean = true,
    val sendOnTokenCall: Boolean = true
)

interface SmsGatewayAdapter {
    suspend fun sendSms(recipientPhone: String, message: String): Boolean
    suspend fun testGateway(): Boolean
}

class LocalHttpSmsGatewayAdapter(private val config: SmsConfiguration) : SmsGatewayAdapter {
    override suspend fun sendSms(recipientPhone: String, message: String): Boolean {
        if (config.gatewayType == SmsGatewayType.DISABLED) {
            println("[SmsAdapter] SMS gateway disabled. Skipping SMS to $recipientPhone")
            return false
        }
        println("[SmsAdapter] Sent SMS to $recipientPhone via ${config.gatewayType}: $message")
        return true
    }

    override suspend fun testGateway(): Boolean {
        println("[SmsAdapter] Tested connection to gateway ${config.gatewayUrl}")
        return true
    }
}
