package com.miwis.tabnewskt.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.miwis.tabnewskt.R
import com.miwis.tabnewskt.ui.navigation.posts.bottomAppBarItems
import com.miwis.tabnewskt.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabnewsKtTopBar(
  onProfileClick: () -> Unit = {},
  onSettingsClick: () -> Unit = {},
  onBackButtonClick: () -> Unit = {}
) {
  TopAppBar(
    title = {
      Text(
        text = "TabnewsKt", style = TextStyle(
          fontSize = 24.sp
        )
      )
    },
    colors = TopAppBarDefaults.smallTopAppBarColors(
      containerColor = MaterialTheme.colorScheme.primary,
      titleContentColor = Color.White
    ),
    navigationIcon = {
      IconButton(onClick = { onBackButtonClick() }) {
        Icon(
          imageVector = Icons.Filled.ArrowBack,
          contentDescription = null,
          tint = Color.White
        )
      }
    },
    actions = {
      TopBarDropDownMenu(
        onProfileClick = onProfileClick,
        onSettingsClick = onSettingsClick
      )
    }
  )
}

@Composable
fun TopBarDropDownMenu(
  onProfileClick: () -> Unit = {},
  onSettingsClick: () -> Unit = {}
) {
  var expanded by remember { mutableStateOf(false) }

  Box(
    modifier = Modifier
      .wrapContentSize(Alignment.TopEnd)
  ) {
    IconButton(onClick = { expanded = !expanded }) {
      Icon(
        painter = painterResource(id = R.drawable.ic_more_vert),
        contentDescription = "Localized description",
        tint = Color.White
      )
    }

    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

      DropdownMenuItem(
        text = { Text(text = "Perfil") },
        onClick = {
          onProfileClick()
          expanded = false
        }
      )

      DropdownMenuItem(
        text = { Text(text = "Ajustes") },
        onClick = {
          onSettingsClick()
          expanded = false
        }
      )

    }
  }
}

@Composable
fun NoConnectionFoundBox(
  onReloadClick: () -> Unit = {}
) {
  Box(
    Modifier.fillMaxSize()
  ) {
    Column(
      Modifier.align(Alignment.Center),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = "Não foi possível carregar os posts",
        style = Typography.titleMedium
      )
      TextButton(onClick = {
        onReloadClick()
      }) {
        Text(text = "Recarregar posts")
      }
    }
  }
}

@Composable
fun TabnewsKtFab(
  text: String,
  icon: ImageVector,
  onClick: () -> Unit = {}
) {
  ExtendedFloatingActionButton(onClick = { onClick() }) {
    Icon(imageVector = icon, contentDescription = null)
    Spacer(modifier = Modifier.width(8.dp))
    Text(text = text)
  }
}

@Composable
fun BottomBarNavigation(
  currentDestination: NavDestination?,
  navController: NavHostController
) {
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
}


@Preview
@Composable
private fun TabnewsKtTopBarPreview() {
  TabnewsKtTopBar()
}