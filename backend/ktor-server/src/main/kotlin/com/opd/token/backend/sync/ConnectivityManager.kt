package com.opd.token.backend.sync

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.net.HttpURLConnection
import java.net.URL

enum class ConnectivityState {
    LOCAL_ONLY,
    INTERNET_AVAILABLE,
    CLOUD_CONNECTED,
    SYNCING,
    SYNC_ERROR
}

class ConnectivityManager(
    private val supabaseUrl: String = "https://xyz.supabase.co",
    private val firebaseUrl: String = "https://firestore.googleapis.com"
) {
    private val _state = MutableStateFlow(ConnectivityState.LOCAL_ONLY)
    val state: StateFlow<ConnectivityState> = _state

    fun checkConnectivity(): ConnectivityState {
        val isInternetActive = checkPing("https://1.1.1.1") || checkPing("https://8.8.8.8")
        if (!isInternetActive) {
            _state.value = ConnectivityState.LOCAL_ONLY
            return ConnectivityState.LOCAL_ONLY
        }

        val isSupabaseReachable = checkPing(supabaseUrl)
        if (isSupabaseReachable) {
            _state.value = ConnectivityState.CLOUD_CONNECTED
            return ConnectivityState.CLOUD_CONNECTED
        }

        _state.value = ConnectivityState.INTERNET_AVAILABLE
        return ConnectivityState.INTERNET_AVAILABLE
    }

    private fun checkPing(urlString: String): Boolean {
        return try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 2000
            connection.readTimeout = 2000
            connection.requestMethod = "HEAD"
            val responseCode = connection.responseCode
            responseCode in 200..399
        } catch (e: Exception) {
            false
        }
    }
}
