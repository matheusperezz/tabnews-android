package com.miwis.tabnewskt.ui.navigation.posts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.miwis.tabnewskt.ui.screens.posts.PostDetailsScreen

const val postDetailsRoute = "postDetails"
const val postIdArgument = "id"
const val postSlugArgument = "postSlug"
const val postOwnerArgument = "postOwner"

fun NavGraphBuilder.postDetailsScreen(navController: NavHostController) {
  composable(
    route = "$postDetailsRoute/{$postIdArgument}?{$postOwnerArgument}?{$postSlugArgument}",
    arguments = listOf(
      navArgument(postIdArgument) {
        NavType.StringType
      },
      navArgument(postOwnerArgument) {
        NavType.StringType
        nullable = true
        defaultValue = ""
      },
      navArgument(postSlugArgument){
        NavType.StringType
        nullable = true
        defaultValue = ""
      }
    )
  ) { backStackEntry ->
    val postOwner = backStackEntry.arguments?.getString(postOwnerArgument)
    val postSlug = backStackEntry.arguments?.getString(postSlugArgument)

    if (postOwner != null && postSlug != null){
      PostDetailsScreen(postOwner = postOwner, postSlug = postSlug, navController = navController)
    } else {
      navController.popBackStack()
    }
  }
}

fun NavController.navigateToPostDetails(id: String, postOwner: String? = null, postSlug: String? = null) {
  navigate("postDetails/$id?$postOwner?$postSlug")
}