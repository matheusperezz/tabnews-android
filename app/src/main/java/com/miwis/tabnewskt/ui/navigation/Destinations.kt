package com.miwis.tabnewskt.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomAppBarItem(
  val label: String,
  val icon: ImageVector,
  val route: String
) {
  object Relevants : BottomAppBarItem(
    label = "Relevantes",
    icon = Icons.Filled.Star,
    route = relevantTabsListRoute
  )

  object News : BottomAppBarItem(
    label = "Recentes",
    icon = Icons.Filled.MailOutline,
    route = newTabsRoute
  )

  object Olds : BottomAppBarItem(
    label = "Antigos",
    icon = Icons.Filled.DateRange,
    route = oldTabsRoute
  )

  object Settings : BottomAppBarItem(
    label = "Configurações",
    icon = Icons.Filled.Settings,
    route = settingsRoute
  )
}

val bottomAppBarItems = listOf(
  BottomAppBarItem.Relevants,
  BottomAppBarItem.News,
  BottomAppBarItem.Olds,
  BottomAppBarItem.Settings
)