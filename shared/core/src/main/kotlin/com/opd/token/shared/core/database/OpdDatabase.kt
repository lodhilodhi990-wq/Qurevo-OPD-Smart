package com.opd.token.shared.core.database

import com.opd.token.shared.models.Patient
import com.opd.token.shared.models.Token
import com.opd.token.shared.models.TokenPriority
import com.opd.token.shared.models.TokenStatus

// Room cache data classes representation for offline cache on Android devices
data class CachedToken(
    val id: String,
    val tokenNumber: String,
    val doctorId: String,
    val status: String,
    val createdAt: String
)

data class CachedPatient(
    val id: String,
    val mrNumber: String,
    val name: String,
    val phone: String?
)
