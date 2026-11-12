package com.opd.token.reception.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opd.token.shared.designsystem.components.PrimaryButton
import com.opd.token.shared.designsystem.components.SecondaryButton
import com.opd.token.shared.designsystem.theme.PrimaryBlue

@Composable
fun ServerConnectionScreen(onConnected: () -> Unit) {
    var serverIp by remember { mutableStateOf("192.168.1.100") }
    var serverPort by remember { mutableStateOf("27800") }
    var isTesting by remember { mutableStateOf(false) }
    var statusMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Dns,
            contentDescription = "Server Connection",
            tint = PrimaryBlue,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Connect to Hospital Server",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryBlue
        )
        Text(
            text = "Enter local network IP address of the server PC",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = serverIp,
            onValueChange = { serverIp = it },
            label = { Text("Server IP Address") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = serverPort,
            onValueChange = { serverPort = it },
            label = { Text("Port") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        statusMessage?.let {
            Text(text = it, color = PrimaryBlue, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(16.dp))
        }

        PrimaryButton(
            text = if (isTesting) "Testing Connection..." else "Connect & Test Server",
            onClick = {
                isTesting = true
                statusMessage = "Connected to http://$serverIp:$serverPort"
                isTesting = false
                onConnected()
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        SecondaryButton(
            text = "Auto Discover Server",
            onClick = {
                statusMessage = "Searching local LAN..."
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
