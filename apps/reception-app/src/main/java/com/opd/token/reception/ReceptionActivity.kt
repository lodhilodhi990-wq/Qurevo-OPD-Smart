package com.opd.token.reception

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.opd.token.reception.navigation.ReceptionNavGraph
import com.opd.token.shared.designsystem.theme.OPDTheme

class ReceptionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate()
        setContent {
            OPDTheme {
                ReceptionNavGraph()
            }
        }
    }
}
