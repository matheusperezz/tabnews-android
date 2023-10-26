package com.miwis.tabnewskt.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

internal const val mainGraphRoute = "mainGraph"

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
  navigation(
    startDestination = relevantTabsListRoute,
    route = mainGraphRoute
  ) {
    relevantTabsScreen(navController)
    newTabsScreen(navController)
    oldTabsScreen(navController)
  }
}

fun NavController.navigateToMainGraph() {
  navigate(mainGraphRoute)
}