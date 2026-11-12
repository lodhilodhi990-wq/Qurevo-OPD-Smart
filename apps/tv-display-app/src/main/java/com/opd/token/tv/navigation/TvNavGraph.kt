package com.opd.token.tv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.opd.token.tv.ui.MainTvDisplayScreen
import com.opd.token.tv.ui.TvSettingsScreen

sealed class TvScreen(val route: String) {
    object MainDisplay : TvScreen("main_display")
    object Settings : TvScreen("settings")
}

@Composable
fun TvNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = TvScreen.MainDisplay.route) {
        composable(TvScreen.MainDisplay.route) {
            MainTvDisplayScreen()
        }
        composable(TvScreen.Settings.route) {
            TvSettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
