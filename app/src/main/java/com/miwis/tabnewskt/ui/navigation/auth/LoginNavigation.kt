package com.miwis.tabnewskt.ui.navigation.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.miwis.tabnewskt.ui.navigation.posts.navigateToMainGraph
import com.miwis.tabnewskt.ui.screens.auth.LoginScreen

const val loginRoute = "login"

fun NavGraphBuilder.loginScreen(navController: NavHostController){
  composable(loginRoute) {
    LoginScreen(
      onSucessNavigate = {
        navController.navigateToMainGraph()
      }
    )
  }
}

fun NavController.navigateToLogin(navOptions: NavOptions? = null){
  navigate(loginRoute, navOptions)
}