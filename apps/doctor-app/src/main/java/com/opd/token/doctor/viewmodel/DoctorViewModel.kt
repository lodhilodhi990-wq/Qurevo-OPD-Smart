package com.opd.token.doctor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opd.token.shared.core.NetworkResult
import com.opd.token.shared.core.network.OpdApiClient
import com.opd.token.shared.models.Token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class DoctorUiState {
    object Idle : DoctorUiState()
    object Loading : DoctorUiState()
    data class ActivePatient(val token: Token) : DoctorUiState()
    data class Error(val message: String) : DoctorUiState()
}

class DoctorViewModel : ViewModel() {
    private val apiClient = OpdApiClient()
    private val doctorId = "doc-1"

    private val _uiState = MutableStateFlow<DoctorUiState>(DoctorUiState.Idle)
    val uiState: StateFlow<DoctorUiState> = _uiState

    fun callNextPatient() {
        viewModelScope.launch {
            _uiState.value = DoctorUiState.Loading
            when (val result = apiClient.callNextToken(doctorId)) {
                is NetworkResult.Success -> {
                    _uiState.value = DoctorUiState.ActivePatient(result.data)
                }
                is NetworkResult.Error -> {
                    _uiState.value = DoctorUiState.Error(result.message)
                }
                else -> {}
            }
        }
    }

    fun updateTokenStatus(tokenId: String, action: String) {
        viewModelScope.launch {
            apiClient.updateTokenStatus(tokenId, action)
        }
    }
}
