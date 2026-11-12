package com.opd.token.unified

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.opd.token.shared.designsystem.theme.OPDTheme
import com.opd.token.unified.navigation.UnifiedNavGraph

class UnifiedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate()
        setContent {
            OPDTheme {
                UnifiedNavGraph()
            }
        }
    }
}
