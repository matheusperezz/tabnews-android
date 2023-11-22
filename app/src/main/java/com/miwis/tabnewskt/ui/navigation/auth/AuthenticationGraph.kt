package com.miwis.tabnewskt.ui.navigation.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.miwis.tabnewskt.ui.navigation.posts.mainGraphRoute
import com.miwis.tabnewskt.ui.navigation.posts.relevantTabsListRoute

internal const val authenticationGraphRoute = "loginGraph"

fun NavGraphBuilder.authenticationGraph(navController: NavHostController) {
  navigation(
    startDestination = loginRoute,
    route = authenticationGraphRoute
  ) {
    loginScreen(navController)
  }
}

fun NavController.navigateToAuthenticationGraph() {
  navigate(mainGraphRoute)
}