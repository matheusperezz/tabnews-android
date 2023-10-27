package com.miwis.tabnewskt.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.miwis.tabnewskt.ui.screens.OldTabsScreen

const val oldTabsRoute = "olds"

fun NavGraphBuilder.oldTabsScreen(navController: NavHostController) {
  composable(oldTabsRoute) {
    OldTabsScreen()
  }
}

fun NavController.navigateToOlds(navOptions: NavOptions? = null) {
  navigate(oldTabsRoute, navOptions)
}