package com.miwis.tabnewskt

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miwis.tabnewskt.data.services.PostService
import com.miwis.tabnewskt.data.utils.showMessage
import com.miwis.tabnewskt.ui.navigation.TabnewsNavHost
import com.miwis.tabnewskt.ui.navigation.bottomAppBarItems
import com.miwis.tabnewskt.ui.theme.TabnewsKtTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  Próximos passos:
 *
 *  TODO: Implementar as outras telas
 *  TODO: Implementar os comentários de cada post
 *  TODO: Implementar login?
 *
 *  TODO: FIX: O app não abre sem internet
 * **/

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var service: PostService

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launch {
      val posts = service.fetchFirstRelevants()
      showMessage("Result: $posts")
    }

    setContent {
      val navController = rememberNavController()
      val navBackStackEntry by navController.currentBackStackEntryAsState()
      val currentDestination = navBackStackEntry?.destination

      TabnewsKtTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          TabnewsKtApp(
            navController = navController,
            currentDestination = currentDestination
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
  // onFabClick: () -> Unit = {},
  content: @Composable () -> Unit
) {
  Scaffold(
    bottomBar = {
      NavigationBar {
        bottomAppBarItems.forEach { screen ->
          val isSelected =
            currentDestination?.hierarchy?.any { it.route == screen.route } == true
          val icon = screen.icon
          NavigationBarItem(
            selected = isSelected,
            onClick = {
              navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                  saveState = true
                }
                launchSingleTop = true
                restoreState = true
              }
            },
            icon = {
              Icon(
                imageVector = icon,
                contentDescription = null
              )
            },
            label = {
              Text(text = screen.label)
            }
          )
        }
      }
    },
  ) {
    Box(modifier = Modifier
      .padding(it)
      .padding(8.dp)
    ) {
      content()
    }
  }
}
