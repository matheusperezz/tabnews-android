package com.miwis.tabnewskt

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miwis.tabnewskt.ui.components.BottomBarNavigation
import com.miwis.tabnewskt.ui.components.TabnewsApp
import com.miwis.tabnewskt.ui.components.TabnewsKtApp
import com.miwis.tabnewskt.ui.components.TabnewsKtFab
import com.miwis.tabnewskt.ui.components.TabnewsKtTopBar
import com.miwis.tabnewskt.ui.navigation.TabnewsNavHost
import com.miwis.tabnewskt.ui.navigation.auth.loginRoute
import com.miwis.tabnewskt.ui.navigation.auth.navigateToAuthenticationGraph
import com.miwis.tabnewskt.ui.navigation.posts.navigateToNewPost
import com.miwis.tabnewskt.ui.navigation.settings.navigateToSettings
import com.miwis.tabnewskt.ui.navigation.posts.newPostRoute
import com.miwis.tabnewskt.ui.navigation.posts.newTabsRoute
import com.miwis.tabnewskt.ui.navigation.posts.postDetailsRoute
import com.miwis.tabnewskt.ui.navigation.posts.relevantTabsListRoute
import com.miwis.tabnewskt.ui.navigation.settings.settingsRoute
import com.miwis.tabnewskt.ui.theme.TabnewsKtTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Próximos passos:
 *
 *  TODO: Implementar o UPVote e o DownVote
 *  TODO: Implementar o cache nos posts
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
        TabnewsApp(currentDestination, navController)
      }
    }
  }
}


