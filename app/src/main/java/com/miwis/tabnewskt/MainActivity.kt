package com.miwis.tabnewskt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.miwis.tabnewskt.ui.components.BottomAppBarItem
import com.miwis.tabnewskt.ui.components.TabnewsBottomAppBar
import com.miwis.tabnewskt.ui.components.bottomAppBarItems
import com.miwis.tabnewskt.ui.navigation.TabnewsNavHost
import com.miwis.tabnewskt.ui.navigation.navigateSingleTopWithPopUpTo
import com.miwis.tabnewskt.ui.theme.TabnewsKtTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {

      val navController = rememberNavController()

      TabnewsKtTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          TabnewsKtApp(
            onBottomBarItemSelectChange = { item ->
              navController.navigateSingleTopWithPopUpTo(item)
            },
            navController = navController
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
  bottomBarAppItemSelected: BottomAppBarItem = bottomAppBarItems.first(),
  onBottomBarItemSelectChange: (BottomAppBarItem) -> Unit = {},
  onFabClick: () -> Unit = {},
  content: @Composable () -> Unit
) {
  Scaffold(
    bottomBar = {
      TabnewsBottomAppBar(
        item = bottomBarAppItemSelected,
        items = bottomAppBarItems,
        onItemChange = onBottomBarItemSelectChange,
        navController = navController
      )
    }
  ) {
    Box(modifier = Modifier.padding(it)){
      content()
    }
  }
}