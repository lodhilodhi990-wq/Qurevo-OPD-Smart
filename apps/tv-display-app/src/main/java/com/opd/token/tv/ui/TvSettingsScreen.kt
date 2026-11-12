package com.opd.token.tv.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opd.token.shared.designsystem.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvSettingsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TV Display Configuration", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
        ) {
            Text("TV Device Name: Waiting Room TV #1")
            Text("Assigned Department: All Departments / Cardiology")
            Text("Announcement Voice: Urdu + English TextToSpeech")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onBack) {
                Text("Return to Live Display")
            }
        }
    }
}
