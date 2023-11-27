package com.miwis.tabnewskt.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabnewsKtTopBar(
  onMenuClick: () -> Unit = {},
  onBackButtonClick: () -> Unit = {}
) {
  TopAppBar(
    title = { Text(text = "TabnewsKt", style = TextStyle(
      fontSize = 24.sp
    )) },
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
      IconButton(onClick = { onMenuClick() }) {
        Icon(
          imageVector = Icons.Filled.Menu,
          contentDescription = "Localized description",
          tint = Color.White
        )
      }
    }
  )
}

@Preview
@Composable
private fun TabnewsKtTopBarPreview() {
  TabnewsKtTopBar()
}