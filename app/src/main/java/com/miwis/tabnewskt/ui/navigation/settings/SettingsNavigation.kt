package com.miwis.tabnewskt.ui.navigation.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.miwis.tabnewskt.ui.screens.settings.SettingsScreen

const val settingsRoute = "settings"

fun NavGraphBuilder.settingsScreen(navController: NavHostController) {
  composable(settingsRoute) {
    SettingsScreen()
  }
}

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
  navigate(settingsRoute, navOptions)
}