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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

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
  onItemChange: (BottomAppBarItem) -> Unit = {}
) {
  NavigationBar(modifier) {
    items.forEach {
      val label = it.label
      val icon = it.icon
      NavigationBarItem(
        selected = item.label == label,
        label = { Text(label) },
        onClick = { onItemChange(it) },
        icon = { Icon(icon, contentDescription = null) })
    }
  }
}