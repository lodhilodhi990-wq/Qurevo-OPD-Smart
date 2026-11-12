package com.opd.token.shared.core.network

import com.opd.token.shared.core.ApiEndpoints
import com.opd.token.shared.core.NetworkResult
import com.opd.token.shared.models.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class OpdApiClient(private var serverHost: String = "192.168.1.100", private var serverPort: Int = ApiEndpoints.DEFAULT_PORT) {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(WebSockets)
    }

    fun setServerAddress(host: String, port: Int = ApiEndpoints.DEFAULT_PORT) {
        this.serverHost = host
        this.serverPort = port
    }

    private val baseUrl: String
        get() = "http://$serverHost:$serverPort"

    suspend fun login(username: String, password: String): NetworkResult<AuthResponse> {
        return try {
            val response: AuthResponse = client.post("$baseUrl${ApiEndpoints.LOGIN}") {
                contentType(ContentType.Application.Json)
                setBody(AuthRequest(username, password))
            }.body()
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Connection failed")
        }
    }

    suspend fun registerPatient(patient: Patient): NetworkResult<Patient> {
        return try {
            val response: Patient = client.post("$baseUrl${ApiEndpoints.PATIENTS}") {
                contentType(ContentType.Application.Json)
                setBody(patient)
            }.body()
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Failed to register patient")
        }
    }

    suspend fun generateToken(patientId: String, doctorId: String, departmentId: String): NetworkResult<Token> {
        return try {
            val response: Token = client.post("$baseUrl${ApiEndpoints.TOKENS}") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("patientId" to patientId, "doctorId" to doctorId, "departmentId" to departmentId))
            }.body()
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Failed to generate token")
        }
    }

    suspend fun callNextToken(doctorId: String): NetworkResult<Token> {
        return try {
            val response: Token = client.post("$baseUrl${ApiEndpoints.TOKENS_NEXT}?doctorId=$doctorId").body()
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "No waiting patient")
        }
    }

    suspend fun updateTokenStatus(tokenId: String, action: String): NetworkResult<Token> {
        return try {
            val response: Token = client.post("$baseUrl${ApiEndpoints.TOKENS}/$tokenId/$action").body()
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Failed to update status")
        }
    }
}
