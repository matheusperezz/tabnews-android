package com.miwis.tabnewskt.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
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
    settingsScreen(navController)
  }
}

fun NavController.navigateToMainGraph() {
  navigate(mainGraphRoute)
}

fun NavController.navigateSingleTopWithPopUpTo(
  item: BottomAppBarItem
) {
  val (route, navigate) = when (item) {
    BottomAppBarItem.Relevants -> Pair(
      relevantTabsListRoute,
      ::navigateToRelevant
    )
    BottomAppBarItem.News -> Pair(
      newTabsRoute,
      ::navigateToNewsTabs
    )
    BottomAppBarItem.Olds -> Pair(
      oldTabsRoute,
      ::navigateToOlds
    )
    BottomAppBarItem.Settings -> Pair(
      settingsRoute,
      ::navigateToSettings
    )
  }

  val navOptions = navOptions {
    launchSingleTop = true
    popUpTo(route)
  }
  navigate(navOptions)
}