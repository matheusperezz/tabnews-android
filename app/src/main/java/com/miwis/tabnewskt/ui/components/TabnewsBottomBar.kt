package com.miwis.tabnewskt.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class BottomAppBarItem(
  val label: String,
  val icon: ImageVector
) {
  object Relevants : BottomAppBarItem(
    label = "Relevantes",
    icon = Icons.Filled.Star
  )

  object News : BottomAppBarItem(
    label = "Recentes",
    icon = Icons.Filled.MailOutline
  )

  object Olds : BottomAppBarItem(
    label = "Antigos",
    icon = Icons.Filled.DateRange
  )
}

val bottomAppBarItems = listOf(
  BottomAppBarItem.Relevants,
  BottomAppBarItem.News,
  BottomAppBarItem.Olds
)

@Composable
fun TabnewsBottomAppBar(
  item: BottomAppBarItem,
  modifier: Modifier = Modifier,
  items: List<BottomAppBarItem> = emptyList(),
  navController: NavHostController,
  onItemChange: (BottomAppBarItem) -> Unit = {}
) {

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  NavigationBar(modifier) {
    items.forEach { screen ->
      val label = screen.label
      val icon = screen.icon
      NavigationBarItem(
        selected = currentDestination?.hierarchy?.any { it.route == screen.label } == true,
        label = { Text(label) },
        onClick = { onItemChange(screen) },
        icon = { Icon(icon, contentDescription = null) })
    }
  }
}