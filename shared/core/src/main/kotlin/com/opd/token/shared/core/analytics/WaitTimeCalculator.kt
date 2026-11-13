package com.opd.token.shared.core.analytics

import com.opd.token.shared.models.Token
import com.opd.token.shared.models.TokenStatus
import kotlinx.serialization.Serializable

@Serializable
data class QueueAnalyticsReport(
    val doctorId: String,
    val doctorName: String,
    val waitingCount: Int,
    val completedCount: Int,
    val averageConsultationMinutes: Int,
    val estimatedWaitMinutesForNewToken: Int
)

class WaitTimeCalculator {

    fun calculateEstimatedWaitMinutes(
        waitingPosition: Int,
        averageConsultationMinutes: Int = 5
    ): Int {
        if (waitingPosition <= 0) return 0
        return waitingPosition * averageConsultationMinutes
    }

    fun calculateAnalyticsReport(
        doctorId: String,
        doctorName: String,
        tokens: List<Token>
    ): QueueAnalyticsReport {
        val doctorTokens = tokens.filter { it.doctorId == doctorId }
        val waitingCount = doctorTokens.count { it.status == TokenStatus.WAITING }
        val completedCount = doctorTokens.count { it.status == TokenStatus.COMPLETED }
        val avgConsultation = 5 // Standard rolling average in minutes
        val estWait = calculateEstimatedWaitMinutes(waitingCount, avgConsultation)

        return QueueAnalyticsReport(
            doctorId = doctorId,
            doctorName = doctorName,
            waitingCount = waitingCount,
            completedCount = completedCount,
            averageConsultationMinutes = avgConsultation,
            estimatedWaitMinutesForNewToken = estWait
        )
    }
}
