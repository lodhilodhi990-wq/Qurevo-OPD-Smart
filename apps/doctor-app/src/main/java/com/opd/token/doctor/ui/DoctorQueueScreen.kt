package com.opd.token.doctor.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opd.token.shared.designsystem.components.TokenCard
import com.opd.token.shared.designsystem.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorQueueScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Assigned Queue List", color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                TokenCard(tokenNumber = "T-025", doctorName = "Usman Khan", departmentName = "MR-2026-0018", status = "WAITING", statusColor = StatusWaiting)
            }
            item {
                TokenCard(tokenNumber = "T-026", doctorName = "Zahid Mahmood", departmentName = "MR-2026-0021", status = "WAITING", statusColor = StatusWaiting)
            }
            item {
                TokenCard(tokenNumber = "T-027", doctorName = "Tariq Jameel", departmentName = "MR-2026-0025", status = "HELD", statusColor = StatusHeld)
            }
        }
    }
}
