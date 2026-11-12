package com.opd.token.reception.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opd.token.shared.core.NetworkResult
import com.opd.token.shared.core.network.OpdApiClient
import com.opd.token.shared.models.Patient
import com.opd.token.shared.models.Token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ReceptionUiState {
    object Idle : ReceptionUiState()
    object Loading : ReceptionUiState()
    data class Success(val message: String, val token: Token? = null) : ReceptionUiState()
    data class Error(val error: String) : ReceptionUiState()
}

class ReceptionViewModel : ViewModel() {
    private val apiClient = OpdApiClient()

    private val _uiState = MutableStateFlow<ReceptionUiState>(ReceptionUiState.Idle)
    val uiState: StateFlow<ReceptionUiState> = _uiState

    fun setServerIp(ip: String) {
        apiClient.setServerAddress(ip)
    }

    fun registerAndIssueToken(patient: Patient, doctorId: String, departmentId: String) {
        viewModelScope.launch {
            _uiState.value = ReceptionUiState.Loading
            when (val regResult = apiClient.registerPatient(patient)) {
                is NetworkResult.Success -> {
                    when (val tokenResult = apiClient.generateToken(regResult.data.id, doctorId, departmentId)) {
                        is NetworkResult.Success -> {
                            _uiState.value = ReceptionUiState.Success("Token Generated Successfully", tokenResult.data)
                        }
                        is NetworkResult.Error -> {
                            _uiState.value = ReceptionUiState.Error(tokenResult.message)
                        }
                        else -> {}
                    }
                }
                is NetworkResult.Error -> {
                    _uiState.value = ReceptionUiState.Error(regResult.message)
                }
                else -> {}
            }
        }
    }
}
