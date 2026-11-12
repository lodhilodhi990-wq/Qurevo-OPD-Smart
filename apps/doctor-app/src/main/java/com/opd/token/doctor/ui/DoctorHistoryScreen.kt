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
import com.opd.token.shared.designsystem.theme.PrimaryBlue
import com.opd.token.shared.designsystem.theme.StatusInConsultation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorHistoryScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Consulted Patients History", color = MaterialTheme.colorScheme.onPrimary) },
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
                TokenCard(tokenNumber = "T-023", doctorName = "Bilal Farooq", departmentName = "Consultation Time: 12 mins", status = "COMPLETED", statusColor = StatusInConsultation)
            }
            item {
                TokenCard(tokenNumber = "T-022", doctorName = "Saad Rafique", departmentName = "Consultation Time: 8 mins", status = "COMPLETED", statusColor = StatusInConsultation)
            }
        }
    }
}
