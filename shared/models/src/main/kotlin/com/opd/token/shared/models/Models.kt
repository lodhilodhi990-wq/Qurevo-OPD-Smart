package com.opd.token.shared.models

import kotlinx.serialization.Serializable

@Serializable
enum class TokenStatus {
    WAITING,
    CALLED,
    IN_CONSULTATION,
    HELD,
    SKIPPED,
    COMPLETED,
    CANCELLED
}

@Serializable
enum class TokenPriority(val rank: Int) {
    NORMAL(0),
    PRIORITY(1),
    URGENT(2),
    EMERGENCY(3)
}

@Serializable
data class PriorityElevationRequest(
    val tokenId: String,
    val newPriority: TokenPriority,
    val reason: String,
    val requestedBy: String
)

@Serializable
data class PriorityAuditLog(
    val id: String,
    val tokenId: String,
    val oldPriority: TokenPriority,
    val newPriority: TokenPriority,
    val reason: String,
    val changedBy: String,
    val timestamp: String
)

@Serializable
enum class UserRole {
    SUPER_ADMIN,
    HOSPITAL_ADMIN,
    RECEPTIONIST,
    DOCTOR,
    DISPLAY_DEVICE
}

@Serializable
data class User(
    val id: String,
    val username: String,
    val fullName: String,
    val role: UserRole,
    val hospitalId: String,
    val doctorId: String? = null,
    val isActive: Boolean = true
)

@Serializable
data class Department(
    val id: String,
    val hospitalId: String,
    val name: String,
    val codePrefix: String,
    val isActive: Boolean = true
)

@Serializable
data class Room(
    val id: String,
    val hospitalId: String,
    val departmentId: String,
    val roomNumber: String,
    val name: String? = null
)

@Serializable
data class Doctor(
    val id: String,
    val hospitalId: String,
    val departmentId: String,
    val roomId: String? = null,
    val name: String,
    val qualification: String? = null,
    val isAvailable: Boolean = true
)

@Serializable
data class Patient(
    val id: String,
    val mrNumber: String,
    val name: String,
    val guardianName: String? = null,
    val age: Int,
    val gender: String,
    val phone: String? = null,
    val address: String? = null
)

@Serializable
data class Token(
    val id: String,
    val hospitalId: String,
    val visitId: String,
    val departmentId: String,
    val doctorId: String,
    val tokenNumber: String,
    val tokenPrefix: String,
    val queueDate: String,
    val priority: TokenPriority = TokenPriority.NORMAL,
    val status: TokenStatus = TokenStatus.WAITING,
    val createdAt: String,
    val calledAt: String? = null,
    val completedAt: String? = null,
    val createdBy: String,
    val idempotencyKey: String? = null
)

@Serializable
data class Visit(
    val id: String,
    val patientId: String,
    val departmentId: String,
    val doctorId: String,
    val visitDate: String,
    val visitType: String = "NORMAL",
    val notes: String? = null
)

@Serializable
data class QueueSummary(
    val doctorId: String,
    val doctorName: String,
    val roomNumber: String,
    val currentToken: Token? = null,
    val waitingCount: Int = 0,
    val completedCount: Int = 0,
    val heldCount: Int = 0
)

@Serializable
data class AuthRequest(
    val username: String,
    val password: String
)

@Serializable
data class AuthResponse(
    val token: String,
    val user: User
)

@Serializable
data class WebSocketMessage(
    val type: String,
    val payload: String
)
