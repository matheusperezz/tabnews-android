package com.miwis.tabnewskt.ui.navigation.posts

import androidx.compose.material.icons.Icons
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

}

val bottomAppBarItems = listOf(
  BottomAppBarItem.Relevants,
  BottomAppBarItem.News,
)