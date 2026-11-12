package com.opd.token.admin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opd.token.shared.designsystem.components.DashboardCard
import com.opd.token.shared.designsystem.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    onNavigateDepartments: () -> Unit,
    onNavigateDoctors: () -> Unit,
    onNavigateRooms: () -> Unit,
    onNavigateDevices: () -> Unit,
    onNavigateReports: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hospital Admin Panel", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                DashboardCard(title = "Total Doctors", value = "12", modifier = Modifier.weight(1f))
                DashboardCard(title = "Departments", value = "6", modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text("Hospital Management Modules", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = onNavigateDepartments, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Icon(Icons.Default.Business, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Manage Departments & Token Prefixes")
            }
            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = onNavigateDoctors, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Icon(Icons.Default.MedicalServices, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Manage Doctors & Schedules")
            }
            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = onNavigateRooms, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Icon(Icons.Default.MeetingRoom, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Manage Consultation Rooms")
            }
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(onClick = onNavigateDevices, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Icon(Icons.Default.Devices, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Device Pairing & Authorization")
            }
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(onClick = onNavigateReports, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Icon(Icons.Default.Assessment, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Reports & Analytics")
            }
        }
    }
}
