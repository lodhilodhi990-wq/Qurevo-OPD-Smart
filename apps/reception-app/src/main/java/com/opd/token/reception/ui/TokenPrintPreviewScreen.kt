package com.opd.token.reception.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Print
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opd.token.shared.designsystem.components.PrimaryButton
import com.opd.token.shared.designsystem.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TokenPrintPreviewScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Token Thermal Print Ticket", color = MaterialTheme.colorScheme.onPrimary) },
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
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                shadowElevation = 8.dp,
                modifier = Modifier.width(280.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("CITY GENERAL HOSPITAL", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("OPD Token Ticket", fontSize = 12.sp, color = Color.Gray)
                    Divider(modifier = Modifier.padding(vertical = 12.dp))

                    Text("TOKEN NUMBER", fontSize = 12.sp, color = PrimaryBlue)
                    Text("T-025", fontSize = 42.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)

                    Divider(modifier = Modifier.padding(vertical = 12.dp))
                    Text("Patient: Muhammad Ali", fontSize = 14.sp)
                    Text("Doctor: Dr. Ahmed", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    Text("Department: Cardiology", fontSize = 12.sp)
                    Text("Room: 02 (1st Floor)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Date: 2026-03-30 | 10:45 AM", fontSize = 10.sp, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton(
                text = "Print Thermal Ticket",
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
