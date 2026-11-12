package com.opd.token.backend

import com.opd.token.backend.repository.TokenRepository
import com.opd.token.shared.models.TokenPriority
import com.opd.token.shared.models.TokenStatus
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TokenRepositoryTest {
    private lateinit var repository: TokenRepository

    @Before
    fun setUp() {
        repository = TokenRepository()
    }

    @Test
    fun testGenerateToken_createsUniqueSequentialTokenNumber() {
        val t1 = repository.generateToken("p-1", "doc-1", "dep-1", TokenPriority.NORMAL)
        val t2 = repository.generateToken("p-2", "doc-1", "dep-1", TokenPriority.NORMAL)

        assertNotNull(t1.tokenNumber)
        assertNotNull(t2.tokenNumber)
        assertNotEquals(t1.tokenNumber, t2.tokenNumber)
        assertEquals(TokenStatus.WAITING, t1.status)
    }

    @Test
    fun testGetNextWaitingToken_atomicallyCallsNextInQueue() {
        val t1 = repository.generateToken("p-1", "doc-1", "dep-1", TokenPriority.NORMAL)
        val calledToken = repository.getNextWaitingToken("doc-1")

        assertNotNull(calledToken)
        assertEquals(t1.id, calledToken?.id)
        assertEquals(TokenStatus.CALLED, calledToken?.status)
    }

    @Test
    fun testUpdateTokenStatus_updatesStateCorrectly() {
        val t1 = repository.generateToken("p-1", "doc-1", "dep-1", TokenPriority.NORMAL)
        val updated = repository.updateTokenStatus(t1.id, TokenStatus.COMPLETED)

        assertNotNull(updated)
        assertEquals(TokenStatus.COMPLETED, updated?.status)
    }

    @Test
    fun testPatientSearch_returnsMatchingPatients() {
        val results = repository.searchPatients("Muhammad")
        assertTrue(results.isNotEmpty())
        assertTrue(results[0].name.contains("Muhammad"))
    }
}
