package com.opd.token.doctor.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opd.token.shared.designsystem.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentPatientScreen(onBack: () -> Unit) {
    var currentTokenNumber by remember { mutableStateOf("T-024") }
    var patientName by remember { mutableStateOf("Muhammad Ali") }
    var patientMr by remember { mutableStateOf("MR-2026-0012") }
    var statusText by remember { mutableStateOf("IN CONSULTATION") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Consultation Console", color = MaterialTheme.colorScheme.onPrimary) },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("CURRENT PATIENT TOKEN", fontSize = 14.sp, color = TextSecondary)
                    Text(currentTokenNumber, fontSize = 64.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(patientName, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text(patientMr, fontSize = 14.sp, color = TextSecondary)
                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        color = StatusInConsultation,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = statusText,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Doctor Action Controls", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(12.dp))

            // Primary Controls Grid
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {
                        currentTokenNumber = "T-025"
                        patientName = "Usman Khan"
                        patientMr = "MR-2026-0018"
                        statusText = "CALLED"
                    },
                    modifier = Modifier.weight(1f).height(54.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                ) {
                    Text("NEXT", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = {
                        statusText = "RECALLED"
                    },
                    modifier = Modifier.weight(1f).height(54.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SecondaryBlue)
                ) {
                    Text("RECALL", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = { statusText = "HELD" },
                    modifier = Modifier.weight(1f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = StatusHeld)
                ) {
                    Text("HOLD")
                }
                Button(
                    onClick = { statusText = "SKIPPED" },
                    modifier = Modifier.weight(1f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = StatusSkipped)
                ) {
                    Text("SKIP")
                }
                Button(
                    onClick = { statusText = "COMPLETED" },
                    modifier = Modifier.weight(1f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = StatusInConsultation)
                ) {
                    Text("COMPLETE")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { statusText = "CALLING EMERGENCY" },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = StatusEmergency)
            ) {
                Text("CALL EMERGENCY PATIENT", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}
