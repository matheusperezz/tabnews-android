package com.miwis.tabnewskt.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.ui.components.PostList
import com.miwis.tabnewskt.ui.theme.Typography
import com.miwis.tabnewskt.ui.uistates.RelevantUiState

@Composable
fun RelevantTabsScreen(
  uiState: RelevantUiState,
  onPostClick: (Post) -> Unit = {}
) {
  when(uiState){
    is RelevantUiState.Loading -> {
      Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
          Modifier.align(Alignment.Center)
        )
      }
    }

    is RelevantUiState.Empty -> {
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
            style = Typography.titleLarge
          )
          TextButton(onClick = { } ) {
            Text(text = "Recarregar posts")
          }
        }
      }
    }

    is RelevantUiState.Sucess -> {
      PostList(
        onPostClick = onPostClick,
        posts = uiState.posts,
      )
    }
  }

}