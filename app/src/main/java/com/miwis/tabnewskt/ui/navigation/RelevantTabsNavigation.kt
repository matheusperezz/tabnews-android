package com.miwis.tabnewskt.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.miwis.tabnewskt.ui.screens.RelevantTabsScreen

const val relevantTabsListRoute = "relevants"

fun NavGraphBuilder.relevantTabsScreen(navController: NavHostController) {
  composable(relevantTabsListRoute) {
    RelevantTabsScreen(
      onPostClick = { post ->
        navController.navigateToPostDetails(post.id)
      }
    )
  }
}

fun NavController.navigateToRelevant(navOptions: NavOptions? = null) {
  navigate(relevantTabsListRoute, navOptions)
}