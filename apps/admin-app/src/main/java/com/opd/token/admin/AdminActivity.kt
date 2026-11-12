package com.opd.token.admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.opd.token.admin.navigation.AdminNavGraph
import com.opd.token.shared.designsystem.theme.OPDTheme

class AdminActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate()
        setContent {
            OPDTheme {
                AdminNavGraph()
            }
        }
    }
}
