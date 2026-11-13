package com.opd.token.backend

import com.opd.token.backend.repository.TokenRepository
import com.opd.token.shared.models.TokenPriority
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class QueuePriorityTest {
    private lateinit var repository: TokenRepository

    @Before
    fun setUp() {
        repository = TokenRepository()
    }

    @Test
    fun testGetNextWaitingToken_prioritizesEmergencyTokensOverNormalTokens() {
        // Generate normal token first
        val normalToken = repository.generateToken("p-1", "doc-1", "dep-1", TokenPriority.NORMAL)
        // Generate emergency token second
        val emergencyToken = repository.generateToken("p-2", "doc-1", "dep-1", TokenPriority.EMERGENCY)

        // getNextWaitingToken must return EMERGENCY token first
        val calledToken = repository.getNextWaitingToken("doc-1")

        assertNotNull(calledToken)
        assertEquals(emergencyToken.id, calledToken?.id)
        assertEquals(TokenPriority.EMERGENCY, calledToken?.priority)
    }

    @Test
    fun testElevateTokenPriority_updatesTokenPriorityAndAuditLog() {
        val token = repository.generateToken("p-1", "doc-1", "dep-1", TokenPriority.NORMAL)
        val elevated = repository.elevateTokenPriority(token.id, TokenPriority.URGENT, "Chest Pain Emergency", "drahmed")

        assertNotNull(elevated)
        assertEquals(TokenPriority.URGENT, elevated?.priority)
    }
}
