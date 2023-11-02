package com.miwis.tabnewskt.ui.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val postDetailsRoute = "postDetails"
const val postIdArgument = "postId"

fun NavGraphBuilder.postDetailsScreen(navController: NavHostController) {
  composable(
    "$postDetailsRoute/{$postIdArgument}"
  ) { backStackEntry ->
    backStackEntry.arguments?.getString(postIdArgument)?.let { id ->
      Text(text = "Product details $id")
    }
  }
}

fun NavController.navigateToPostDetails(id: String) {
  navigate("$postDetailsRoute/$id")
}