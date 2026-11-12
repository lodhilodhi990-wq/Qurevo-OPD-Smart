package com.opd.token.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.opd.token.shared.designsystem.theme.OPDTheme
import com.opd.token.tv.navigation.TvNavGraph

class TvDisplayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate()
        setContent {
            OPDTheme {
                TvNavGraph()
            }
        }
    }
}
