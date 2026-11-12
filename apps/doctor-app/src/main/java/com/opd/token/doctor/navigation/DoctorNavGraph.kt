package com.opd.token.doctor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.opd.token.doctor.ui.*

sealed class DoctorScreen(val route: String) {
    object Login : DoctorScreen("login")
    object Dashboard : DoctorScreen("dashboard")
    object CurrentPatient : DoctorScreen("current_patient")
    object Queue : DoctorScreen("queue")
    object History : DoctorScreen("history")
    object Settings : DoctorScreen("settings")
}

@Composable
fun DoctorNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = DoctorScreen.Login.route) {
        composable(DoctorScreen.Login.route) {
            DoctorLoginScreen(onLoginSuccess = { navController.navigate(DoctorScreen.Dashboard.route) })
        }
        composable(DoctorScreen.Dashboard.route) {
            DoctorDashboardScreen(
                onNavigateToCurrentPatient = { navController.navigate(DoctorScreen.CurrentPatient.route) },
                onNavigateToQueue = { navController.navigate(DoctorScreen.Queue.route) },
                onNavigateToHistory = { navController.navigate(DoctorScreen.History.route) },
                onNavigateToSettings = { navController.navigate(DoctorScreen.Settings.route) }
            )
        }
        composable(DoctorScreen.CurrentPatient.route) {
            CurrentPatientScreen(onBack = { navController.popBackStack() })
        }
        composable(DoctorScreen.Queue.route) {
            DoctorQueueScreen(onBack = { navController.popBackStack() })
        }
        composable(DoctorScreen.History.route) {
            DoctorHistoryScreen(onBack = { navController.popBackStack() })
        }
        composable(DoctorScreen.Settings.route) {
            DoctorSettingsScreen(
                onBack = { navController.popBackStack() },
                onLogout = { navController.navigate(DoctorScreen.Login.route) }
            )
        }
    }
}
