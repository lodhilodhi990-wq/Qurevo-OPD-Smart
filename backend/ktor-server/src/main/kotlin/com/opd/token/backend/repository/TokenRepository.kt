package com.opd.token.backend.repository

import com.opd.token.backend.sync.OutboxRepository
import com.opd.token.shared.models.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import java.util.concurrent.atomic.AtomicInteger

class TokenRepository(private val outboxRepository: OutboxRepository = OutboxRepository()) {
    private val tokenList = mutableListOf<Token>()
    private val patientsList = mutableListOf<Patient>()
    private val doctorsList = mutableListOf<Doctor>()
    private val departmentsList = mutableListOf<Department>()
    private val tokenCounter = AtomicInteger(24)

    init {
        // Seed initial data
        departmentsList.add(Department("dep-1", "hosp-1", "Cardiology", "CARD"))
        departmentsList.add(Department("dep-2", "hosp-1", "General OPD", "GEN"))

        doctorsList.add(Doctor("doc-1", "hosp-1", "dep-1", "rm-2", "Dr. Ahmed", "MBBS, FCPS (Cardiology)", true))
        doctorsList.add(Doctor("doc-2", "hosp-1", "dep-2", "rm-1", "Dr. Fatima", "MBBS, MD", true))

        patientsList.add(Patient("p-1", "MR-2026-0012", "Muhammad Ali", "Aslam", 35, "Male", "03001234567"))
        patientsList.add(Patient("p-2", "MR-2026-0018", "Usman Khan", "Tariq", 28, "Male", "03009876543"))

        // Initial token
        tokenList.add(
            Token(
                id = "tok-1",
                hospitalId = "hosp-1",
                visitId = "vis-1",
                departmentId = "dep-1",
                doctorId = "doc-1",
                tokenNumber = "T-024",
                tokenPrefix = "T",
                queueDate = "2026-03-30",
                priority = TokenPriority.NORMAL,
                status = TokenStatus.CALLED,
                createdAt = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                createdBy = "reception1"
            )
        )
    }

    @Synchronized
    fun generateToken(patientId: String, doctorId: String, departmentId: String, priority: TokenPriority): Token {
        val nextNum = tokenCounter.incrementAndGet()
        val numStr = String.format("%03d", nextNum)
        val token = Token(
            id = UUID.randomUUID().toString(),
            hospitalId = "hosp-1",
            visitId = UUID.randomUUID().toString(),
            departmentId = departmentId,
            doctorId = doctorId,
            tokenNumber = "T-$numStr",
            tokenPrefix = "T",
            queueDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
            priority = priority,
            status = TokenStatus.WAITING,
            createdAt = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
            createdBy = "reception1"
        )
        tokenList.add(token)

        // Write to Local Outbox Pattern for eventual cloud synchronization
        outboxRepository.addEvent("hosp-1", "TOKEN", token.id, "CREATE", token)

        return token
    }

    @Synchronized
    fun getTokens(): List<Token> = tokenList.toList()

    @Synchronized
    fun getNextWaitingToken(doctorId: String): Token? {
        val token = tokenList.firstOrNull { it.doctorId == doctorId && it.status == TokenStatus.WAITING }
        if (token != null) {
            val updated = token.copy(
                status = TokenStatus.CALLED,
                calledAt = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
            )
            val index = tokenList.indexOfFirst { it.id == token.id }
            if (index != -1) tokenList[index] = updated

            // Write status update to Outbox
            outboxRepository.addEvent("hosp-1", "TOKEN", updated.id, "UPDATE_CALLED", updated)

            return updated
        }
        return null
    }

    @Synchronized
    fun updateTokenStatus(tokenId: String, status: TokenStatus): Token? {
        val index = tokenList.indexOfFirst { it.id == tokenId }
        if (index != -1) {
            val current = tokenList[index]
            val updated = current.copy(status = status)
            tokenList[index] = updated

            // Write status update to Outbox
            outboxRepository.addEvent("hosp-1", "TOKEN", updated.id, "UPDATE_STATUS_${status.name}", updated)

            return updated
        }
        return null
    }

    fun searchPatients(query: String): List<Patient> {
        return patientsList.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.mrNumber.contains(query, ignoreCase = true) ||
                    (it.phone != null && it.phone.contains(query))
        }
    }

    fun addPatient(patient: Patient): Patient {
        patientsList.add(patient)
        outboxRepository.addEvent("hosp-1", "PATIENT", patient.id, "CREATE", patient)
        return patient
    }

    fun getDoctors(): List<Doctor> = doctorsList
    fun getDepartments(): List<Department> = departmentsList
}
