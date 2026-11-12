package com.opd.token.reception.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opd.token.shared.designsystem.components.PrimaryButton
import com.opd.token.shared.designsystem.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TokenGenerationScreen(
    onBack: () -> Unit,
    onTokenGenerated: () -> Unit
) {
    var department by remember { mutableStateOf("Cardiology") }
    var doctor by remember { mutableStateOf("Dr. Ahmed (Room 2)") }
    var tokenCategory by remember { mutableStateOf("Normal") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Generate Token", color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = PrimaryBlue.copy(alpha = 0.1f))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Token Preview", fontSize = 14.sp, color = PrimaryBlue)
                    Text("T-025", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                    Text("Est. Wait Time: ~15 mins", fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = department,
                onValueChange = { department = it },
                label = { Text("Department") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = doctor,
                onValueChange = { doctor = it },
                label = { Text("Doctor & Room") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton(
                text = "Generate & Issue Token",
                onClick = onTokenGenerated,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
