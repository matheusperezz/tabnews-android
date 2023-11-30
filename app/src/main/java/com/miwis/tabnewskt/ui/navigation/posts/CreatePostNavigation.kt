package com.miwis.tabnewskt.ui.navigation.posts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.miwis.tabnewskt.ui.screens.NewPostScreen

const val newPostRoute = "newPost"

fun NavGraphBuilder.createPostScreen(navController: NavHostController) {
  composable(newPostRoute) {
    NewPostScreen()
  }
}

fun NavController.navigateToNewPost(navOptions: NavOptions? = null) {
  navigate(newPostRoute, navOptions)
}