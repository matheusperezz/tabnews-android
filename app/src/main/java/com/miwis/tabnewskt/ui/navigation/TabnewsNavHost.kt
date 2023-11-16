package com.miwis.tabnewskt.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TabnewsNavHost(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = mainGraphRoute
  ){
    mainGraph(navController)
    postDetailsScreen(navController)
  }
}