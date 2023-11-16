package com.miwis.tabnewskt.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.miwis.tabnewskt.ui.screens.PostDetailsScreen
import com.miwis.tabnewskt.ui.viewmodels.PostDetailsViewModel

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
    val viewModel = hiltViewModel<PostDetailsViewModel>()

    if (postOwner != null && postSlug != null){
      PostDetailsScreen(viewModel = viewModel, postOwner = postOwner, postSlug = postSlug, navController = navController)
    } else {
      navController.popBackStack()
    }

/*    Column {
      postOwner?.let { name ->
        Text(text = "name: $name")
      }

      postSlug?.let { slug ->
        Text(text = "slug: $slug")
      }
    }*/
  }
}

fun NavController.navigateToPostDetails(id: String, postOwner: String? = null, postSlug: String? = null) {
  navigate("postDetails/$id?$postOwner?$postSlug")
}