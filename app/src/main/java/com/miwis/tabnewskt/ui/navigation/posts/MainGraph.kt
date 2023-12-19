package com.miwis.tabnewskt.ui.navigation.posts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.miwis.tabnewskt.ui.navigation.settings.settingsScreen

internal const val mainGraphRoute = "mainGraph"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.mainGraph(navController: NavHostController) {
  navigation(
    startDestination = relevantTabsListRoute,
    route = mainGraphRoute
  ) {
    relevantTabsScreen(navController)
    newTabsScreen(navController)
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
  }

  val navOptions = navOptions {
    launchSingleTop = true
    popUpTo(route)
  }
  navigate(navOptions)
}