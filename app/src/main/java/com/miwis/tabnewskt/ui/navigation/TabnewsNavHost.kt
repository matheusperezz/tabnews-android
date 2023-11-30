package com.miwis.tabnewskt.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.miwis.tabnewskt.ui.navigation.auth.authenticationGraph
import com.miwis.tabnewskt.ui.navigation.auth.authenticationGraphRoute
import com.miwis.tabnewskt.ui.navigation.posts.createPostScreen
import com.miwis.tabnewskt.ui.navigation.posts.mainGraph
import com.miwis.tabnewskt.ui.navigation.posts.postDetailsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TabnewsNavHost(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = authenticationGraphRoute
  ){
    mainGraph(navController)
    authenticationGraph(navController)
    postDetailsScreen(navController)
    createPostScreen(navController)
  }
}