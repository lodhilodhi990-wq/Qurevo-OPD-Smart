package com.opd.token.unified.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opd.token.shared.designsystem.theme.PrimaryBlue
import com.opd.token.shared.models.UserRole

@Composable
fun UnifiedNavGraph() {
    var loggedInRole by remember { mutableStateOf<UserRole?>(null) }

    if (loggedInRole == null) {
        // Unified Login Screen
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("QUREVO Role-Based Portal", fontSize = 24.sp, color = PrimaryBlue)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { loggedInRole = UserRole.RECEPTIONIST }, modifier = Modifier.fillMaxWidth()) {
                Text("Login as RECEPTIONIST")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { loggedInRole = UserRole.DOCTOR }, modifier = Modifier.fillMaxWidth()) {
                Text("Login as DOCTOR")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { loggedInRole = UserRole.HOSPITAL_ADMIN }, modifier = Modifier.fillMaxWidth()) {
                Text("Login as HOSPITAL ADMIN")
            }
        }
    } else {
        // Dynamic Role Workspace
        Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
            Text("Active Role Workspace: ${loggedInRole?.name}", fontSize = 20.sp, color = PrimaryBlue)
            Spacer(modifier = Modifier.height(16.dp))
            when (loggedInRole) {
                UserRole.RECEPTIONIST -> Text("Features: Register Patients, Generate Tokens, Print Slips")
                UserRole.DOCTOR -> Text("Features: Consultation Console, Call Next, Recall, Hold, Complete")
                UserRole.HOSPITAL_ADMIN -> Text("Features: Departments, Doctors, Rooms, Devices & Analytics")
                else -> Text("Standard User Features")
            }
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedButton(onClick = { loggedInRole = null }) {
                Text("Logout")
            }
        }
    }
}
