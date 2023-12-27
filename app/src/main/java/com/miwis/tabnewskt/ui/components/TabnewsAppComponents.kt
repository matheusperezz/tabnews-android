package com.miwis.tabnewskt.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.miwis.tabnewskt.ui.navigation.TabnewsNavHost
import com.miwis.tabnewskt.ui.navigation.auth.loginRoute
import com.miwis.tabnewskt.ui.navigation.auth.navigateToAuthenticationGraph
import com.miwis.tabnewskt.ui.navigation.posts.navigateToNewPost
import com.miwis.tabnewskt.ui.navigation.posts.newPostRoute
import com.miwis.tabnewskt.ui.navigation.posts.newTabsRoute
import com.miwis.tabnewskt.ui.navigation.posts.postDetailsRoute
import com.miwis.tabnewskt.ui.navigation.posts.relevantTabsListRoute
import com.miwis.tabnewskt.ui.navigation.settings.navigateToSettings
import com.miwis.tabnewskt.ui.navigation.settings.settingsRoute

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TabnewsApp(
  currentDestination: NavDestination?,
  navController: NavHostController
) {
  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

    val currentRoute = currentDestination?.route

    val isShowBottomBar = when (currentRoute) {
      relevantTabsListRoute, newTabsRoute, settingsRoute, postDetailsRoute -> true
      else -> false
    }

    val isShowTopBar = when (currentRoute) {
      loginRoute -> false
      else -> true
    }

    val isShowFab = when (currentRoute) {
      loginRoute, newPostRoute, postDetailsRoute -> false
      else -> true
    }

    TabnewsKtApp(
      navController = navController,
      currentDestination = currentDestination,
      isShowBottomBar = isShowBottomBar,
      isShowTopBar = isShowTopBar,
      isShowFab = isShowFab,
    ) {
      TabnewsNavHost(navController = navController)
    }
  }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabnewsKtApp(
  navController: NavHostController,
  currentDestination: NavDestination?,
  isShowBottomBar: Boolean = false,
  isShowTopBar: Boolean = false,
  isShowFab: Boolean = false,
  content: @Composable () -> Unit
) {
  Scaffold(
    bottomBar = {
      if (isShowBottomBar) {
        BottomBarNavigation(currentDestination, navController)
      }
    },
    topBar = {
      if (isShowTopBar) {
        TabnewsKtTopBar(
          onBackButtonClick = {
            navController.popBackStack()
          },
          onSettingsClick = {
            navController.navigateToSettings()
          },
          onProfileClick = {
            navController.navigateToAuthenticationGraph()
          }
        )
      }
    },
    floatingActionButton = {
      if (isShowFab) {
        TabnewsKtFab(text = "Criar post", icon = Icons.Filled.Add, onClick = {
          navController.navigateToNewPost()
        })
      }
    }
  ) {
    Box(
      modifier = Modifier
        .padding(it)
        .padding(horizontal = 8.dp)
    ) {
      content()
    }
  }
}
