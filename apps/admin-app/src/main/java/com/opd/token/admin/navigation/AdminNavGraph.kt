package com.opd.token.admin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.opd.token.admin.ui.*

sealed class AdminScreen(val route: String) {
    object Dashboard : AdminScreen("dashboard")
    object Departments : AdminScreen("departments")
    object Doctors : AdminScreen("doctors")
    object Rooms : AdminScreen("rooms")
    object Devices : AdminScreen("devices")
    object Reports : AdminScreen("reports")
}

@Composable
fun AdminNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AdminScreen.Dashboard.route) {
        composable(AdminScreen.Dashboard.route) {
            AdminDashboardScreen(
                onNavigateDepartments = { navController.navigate(AdminScreen.Departments.route) },
                onNavigateDoctors = { navController.navigate(AdminScreen.Doctors.route) },
                onNavigateRooms = { navController.navigate(AdminScreen.Rooms.route) },
                onNavigateDevices = { navController.navigate(AdminScreen.Devices.route) },
                onNavigateReports = { navController.navigate(AdminScreen.Reports.route) }
            )
        }
        composable(AdminScreen.Departments.route) {
            DepartmentManagementScreen(onBack = { navController.popBackStack() })
        }
        composable(AdminScreen.Doctors.route) {
            DoctorManagementScreen(onBack = { navController.popBackStack() })
        }
        composable(AdminScreen.Rooms.route) {
            RoomManagementScreen(onBack = { navController.popBackStack() })
        }
        composable(AdminScreen.Devices.route) {
            DeviceManagementScreen(onBack = { navController.popBackStack() })
        }
        composable(AdminScreen.Reports.route) {
            ReportsScreen(onBack = { navController.popBackStack() })
        }
    }
}
