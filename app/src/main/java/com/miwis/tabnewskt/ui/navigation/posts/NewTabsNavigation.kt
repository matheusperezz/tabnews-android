package com.miwis.tabnewskt.ui.navigation.posts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.miwis.tabnewskt.ui.screens.NewTabsScreen
import com.miwis.tabnewskt.ui.viewmodels.NewTabsViewModel

const val newTabsRoute = "news"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.newTabsScreen(navController: NavHostController) {
  composable(newTabsRoute) {
    NewTabsScreen(
      onPostClick = { post ->
        navController.navigateToPostDetails(
          post.id,
          postOwner = post.owner_username,
          postSlug = post.slug
        )
      }
    )
  }
}

fun NavController.navigateToNewsTabs(navOptions: NavOptions? = null) {
  navigate(newTabsRoute, navOptions)
}