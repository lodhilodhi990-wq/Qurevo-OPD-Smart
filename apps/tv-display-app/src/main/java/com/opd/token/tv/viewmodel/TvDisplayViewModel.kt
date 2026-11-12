package com.opd.token.tv.viewmodel

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

data class TvDisplayState(
    val currentTokenNumber: String = "T-024",
    val doctorName: String = "Dr. Ahmed",
    val roomNumber: String = "02",
    val departmentName: String = "Cardiology",
    val nextTokens: List<String> = listOf("T-025", "T-026", "T-027", "T-028"),
    val announcementMessage: String = "Token T-024, Dr. Ahmed ke Room 2 mein aaiye"
)

class TvDisplayViewModel : ViewModel(), TextToSpeech.OnInitListener {
    private val _uiState = MutableStateFlow(TvDisplayState())
    val uiState: StateFlow<TvDisplayState> = _uiState

    private var tts: TextToSpeech? = null
    private var isTtsReady = false

    fun initTts(context: Context) {
        tts = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale("ur", "PK"))
            isTtsReady = result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED
            if (!isTtsReady) {
                tts?.setLanguage(Locale.ENGLISH)
                isTtsReady = true
            }
        }
    }

    fun announceToken(tokenNumber: String, doctorName: String, roomNumber: String, language: String = "ur") {
        val announcementText = if (language == "ur") {
            "Token number $tokenNumber, Doctor $doctorName ke room number $roomNumber mein tashreef le kar aayein"
        } else {
            "Token number $tokenNumber, please proceed to Doctor $doctorName, Room number $roomNumber"
        }

        _uiState.value = _uiState.value.copy(
            currentTokenNumber = tokenNumber,
            doctorName = doctorName,
            roomNumber = roomNumber,
            announcementMessage = announcementText
        )

        if (isTtsReady) {
            tts?.speak(announcementText, TextToSpeech.QUEUE_FLUSH, null, "token_announcement_id")
        }
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
    }
}
