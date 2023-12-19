package com.miwis.tabnewskt.ui.navigation.posts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.miwis.tabnewskt.ui.screens.posts.RelevantPostScreen

const val relevantTabsListRoute = "relevants"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.relevantTabsScreen(navController: NavHostController) {
  composable(relevantTabsListRoute) {
    RelevantPostScreen(
      onPostClick = { post ->
        navController.navigateToPostDetails(
          post.id,
          postOwner = post.owner_username,
          postSlug = post.slug
        )
      },
    )
  }
}

fun NavController.navigateToRelevant(navOptions: NavOptions? = null) {
  navigate(relevantTabsListRoute, navOptions)
}