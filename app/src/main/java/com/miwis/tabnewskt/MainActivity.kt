package com.miwis.tabnewskt

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miwis.tabnewskt.ui.components.TabnewsApp
import com.miwis.tabnewskt.ui.theme.TabnewsKtTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Próximos passos:
 *
 *  TODO: Up/DownVote implementado, somente layout
 *  TODO: Cache funcionando sem internet, porém está salvando tudo (Tem que limitar)
 *  TODO: Implementar o acesso dos posts via cache
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


