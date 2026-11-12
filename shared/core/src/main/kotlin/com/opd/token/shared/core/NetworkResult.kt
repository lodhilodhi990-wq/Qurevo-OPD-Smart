package com.opd.token.shared.core

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String, val code: Int? = null) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}

object ApiEndpoints {
    const val DEFAULT_PORT = 27800
    const val BASE_PATH = "/api/v1"

    const val LOGIN = "$BASE_PATH/auth/login"
    const val ME = "$BASE_PATH/auth/me"

    const val PATIENTS = "$BASE_PATH/patients"
    const val PATIENT_SEARCH = "$BASE_PATH/patients/search"

    const val TOKENS = "$BASE_PATH/tokens"
    const val TOKENS_NEXT = "$BASE_PATH/tokens/next"

    const val QUEUES = "$BASE_PATH/queues"
    const val DOCTORS = "$BASE_PATH/doctors"
    const val DEPARTMENTS = "$BASE_PATH/departments"
    const val ROOMS = "$BASE_PATH/rooms"
    const val DEVICES = "$BASE_PATH/devices"
    const val REPORTS = "$BASE_PATH/reports"
    const val BACKUPS = "$BASE_PATH/backups"

    const val WS_ENDPOINT = "/ws"
}

object SystemConstants {
    const val APP_NAME = "OPD Smart Token System"
    const val LAN_DISCOVERY_PORT = 27801
}
