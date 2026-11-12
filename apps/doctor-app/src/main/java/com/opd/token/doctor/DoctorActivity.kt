package com.opd.token.doctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.opd.token.doctor.navigation.DoctorNavGraph
import com.opd.token.shared.designsystem.theme.OPDTheme

class DoctorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate()
        setContent {
            OPDTheme {
                DoctorNavGraph()
            }
        }
    }
}
