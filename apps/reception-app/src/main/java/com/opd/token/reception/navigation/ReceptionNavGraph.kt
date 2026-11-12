package com.opd.token.reception.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.opd.token.reception.ui.*

sealed class ReceptionScreen(val route: String) {
    object Splash : ReceptionScreen("splash")
    object Connection : ReceptionScreen("connection")
    object Login : ReceptionScreen("login")
    object Dashboard : ReceptionScreen("dashboard")
    object RegisterPatient : ReceptionScreen("register_patient")
    object SearchPatient : ReceptionScreen("search_patient")
    object GenerateToken : ReceptionScreen("generate_token")
    object PrintPreview : ReceptionScreen("print_preview")
    object LiveQueue : ReceptionScreen("live_queue")
    object Settings : ReceptionScreen("settings")
}

@Composable
fun ReceptionNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ReceptionScreen.Splash.route) {
        composable(ReceptionScreen.Splash.route) {
            SplashScreen(onTimeout = { navController.navigate(ReceptionScreen.Connection.route) })
        }
        composable(ReceptionScreen.Connection.route) {
            ServerConnectionScreen(onConnected = { navController.navigate(ReceptionScreen.Login.route) })
        }
        composable(ReceptionScreen.Login.route) {
            LoginScreen(onLoginSuccess = { navController.navigate(ReceptionScreen.Dashboard.route) })
        }
        composable(ReceptionScreen.Dashboard.route) {
            ReceptionDashboardScreen(
                onNavigateToRegister = { navController.navigate(ReceptionScreen.RegisterPatient.route) },
                onNavigateToGenerateToken = { navController.navigate(ReceptionScreen.GenerateToken.route) },
                onNavigateToSearch = { navController.navigate(ReceptionScreen.SearchPatient.route) },
                onNavigateToQueue = { navController.navigate(ReceptionScreen.LiveQueue.route) },
                onNavigateToSettings = { navController.navigate(ReceptionScreen.Settings.route) }
            )
        }
        composable(ReceptionScreen.RegisterPatient.route) {
            PatientRegistrationScreen(
                onBack = { navController.popBackStack() },
                onPatientRegistered = { navController.navigate(ReceptionScreen.GenerateToken.route) }
            )
        }
        composable(ReceptionScreen.SearchPatient.route) {
            PatientSearchScreen(
                onBack = { navController.popBackStack() },
                onSelectPatient = { navController.navigate(ReceptionScreen.GenerateToken.route) }
            )
        }
        composable(ReceptionScreen.GenerateToken.route) {
            TokenGenerationScreen(
                onBack = { navController.popBackStack() },
                onTokenGenerated = { navController.navigate(ReceptionScreen.PrintPreview.route) }
            )
        }
        composable(ReceptionScreen.PrintPreview.route) {
            TokenPrintPreviewScreen(onBack = { navController.navigate(ReceptionScreen.Dashboard.route) })
        }
        composable(ReceptionScreen.LiveQueue.route) {
            LiveQueueScreen(onBack = { navController.popBackStack() })
        }
        composable(ReceptionScreen.Settings.route) {
            ReceptionSettingsScreen(
                onBack = { navController.popBackStack() },
                onLogout = { navController.navigate(ReceptionScreen.Login.route) }
            )
        }
    }
}
