package com.miwis.tabnewskt.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.miwis.tabnewskt.ui.screens.NewTabsScreen

const val newTabsRoute = "news"

fun NavGraphBuilder.newTabsScreen(navController: NavHostController) {
  composable(newTabsRoute) {
    NewTabsScreen()
  }
}

fun NavController.navigateToNewsTabs(navOptions: NavOptions? = null) {
  navigate(newTabsRoute, navOptions)
}