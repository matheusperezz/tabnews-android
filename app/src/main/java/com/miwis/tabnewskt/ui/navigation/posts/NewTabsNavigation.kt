package com.miwis.tabnewskt.ui.navigation.posts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.miwis.tabnewskt.ui.screens.posts.RecentPostScreen

const val recentPostRoute = "news"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.recentPostScreen(navController: NavHostController) {
  composable(recentPostRoute) {
    RecentPostScreen(
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
  navigate(recentPostRoute, navOptions)
}