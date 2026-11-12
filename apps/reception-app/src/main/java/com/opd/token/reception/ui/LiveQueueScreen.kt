package com.opd.token.reception.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opd.token.shared.designsystem.components.TokenCard
import com.opd.token.shared.designsystem.theme.PrimaryBlue
import com.opd.token.shared.designsystem.theme.StatusCalled
import com.opd.token.shared.designsystem.theme.StatusWaiting

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveQueueScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Live Hospital OPD Queue", color = MaterialTheme.colorScheme.onPrimary) },
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
                TokenCard(
                    tokenNumber = "T-024",
                    doctorName = "Dr. Ahmed",
                    departmentName = "Cardiology - Room 2",
                    status = "NOW SERVING",
                    statusColor = StatusCalled
                )
            }
            item {
                TokenCard(
                    tokenNumber = "T-025",
                    doctorName = "Dr. Ahmed",
                    departmentName = "Cardiology - Room 2",
                    status = "WAITING",
                    statusColor = StatusWaiting
                )
            }
            item {
                TokenCard(
                    tokenNumber = "T-026",
                    doctorName = "Dr. Ahmed",
                    departmentName = "Cardiology - Room 2",
                    status = "WAITING",
                    statusColor = StatusWaiting
                )
            }
        }
    }
}
