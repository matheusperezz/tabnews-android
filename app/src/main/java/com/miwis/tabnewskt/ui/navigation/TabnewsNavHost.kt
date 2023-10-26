package com.miwis.tabnewskt.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun TabnewsNavHost(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = mainGraphRoute
  ){
    mainGraph(navController)
  }
}