package com.miwis.tabnewskt.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.miwis.tabnewskt.ui.screens.RelevantTabsScreen
import com.miwis.tabnewskt.ui.viewmodels.RelevantTabsViewModel

const val relevantTabsListRoute = "relevants"

fun NavGraphBuilder.relevantTabsScreen(navController: NavHostController) {
  composable(relevantTabsListRoute) {
    val viewModel = hiltViewModel<RelevantTabsViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    RelevantTabsScreen(
      uiState = uiState,
      onPostClick = { post ->
        navController.navigateToPostDetails(post.id)
      }
    )
  }
}

fun NavController.navigateToRelevant(navOptions: NavOptions? = null) {
  navigate(relevantTabsListRoute, navOptions)
}