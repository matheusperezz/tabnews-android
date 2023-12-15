package com.miwis.tabnewskt

import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miwis.tabnewskt.data.services.PostService
import com.miwis.tabnewskt.ui.components.BottomBarNavigation
import com.miwis.tabnewskt.ui.components.TabnewsKtFab
import com.miwis.tabnewskt.ui.components.TabnewsKtTopBar
import com.miwis.tabnewskt.ui.navigation.TabnewsNavHost
import com.miwis.tabnewskt.ui.navigation.auth.authenticationGraphRoute
import com.miwis.tabnewskt.ui.navigation.auth.loginRoute
import com.miwis.tabnewskt.ui.navigation.auth.navigateToAuthenticationGraph
import com.miwis.tabnewskt.ui.navigation.posts.bottomAppBarItems
import com.miwis.tabnewskt.ui.navigation.posts.navigateToNewPost
import com.miwis.tabnewskt.ui.navigation.posts.navigateToSettings
import com.miwis.tabnewskt.ui.navigation.posts.newPostRoute
import com.miwis.tabnewskt.ui.navigation.posts.newTabsRoute
import com.miwis.tabnewskt.ui.navigation.posts.postDetailsRoute
import com.miwis.tabnewskt.ui.navigation.posts.relevantTabsListRoute
import com.miwis.tabnewskt.ui.navigation.posts.settingsRoute
import com.miwis.tabnewskt.ui.theme.TabnewsKtTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *  Próximos passos:
 *
 *  TODO: Implementar os comentários de cada post
 *  TODO: Implementar login?
 *
 * **/

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      val navBackStackEntry by navController.currentBackStackEntryAsState()
      val currentDestination = navBackStackEntry?.destination

      TabnewsKtTheme {
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
            loginRoute, newPostRoute -> false
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

