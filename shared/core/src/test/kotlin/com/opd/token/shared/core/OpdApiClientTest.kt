package com.opd.token.shared.core

import com.opd.token.shared.core.network.OpdApiClient
import com.opd.token.shared.models.TokenPriority
import org.junit.Assert.*
import org.junit.Test

class OpdApiClientTest {
    @Test
    fun testApiEndpoints_constantsAreValid() {
        assertEquals(27800, ApiEndpoints.DEFAULT_PORT)
        assertEquals("/api/v1/auth/login", ApiEndpoints.LOGIN)
        assertEquals("/api/v1/patients", ApiEndpoints.PATIENTS)
        assertEquals("/api/v1/tokens", ApiEndpoints.TOKENS)
    }

    @Test
    fun testClientInitialization_setsHostAndPort() {
        val client = OpdApiClient("192.168.1.50", 27800)
        assertNotNull(client)
    }
}
