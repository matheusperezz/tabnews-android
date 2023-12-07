package com.miwis.tabnewskt.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.ui.components.NoConnectionFoundBox
import com.miwis.tabnewskt.ui.components.PostList
import com.miwis.tabnewskt.ui.theme.Typography
import com.miwis.tabnewskt.ui.viewmodels.NewTabsViewModel
import com.miwis.tabnewskt.ui.viewmodels.NewsUiState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewTabsScreen(
  viewModel: NewTabsViewModel = hiltViewModel(),
  onPostClick: (Post) -> Unit = {}
) {
  val uiState by viewModel.uiState.collectAsState()

  when (uiState) {
    is NewsUiState.Loading -> {
      Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
          Modifier.align(Alignment.Center)
        )
      }
    }

    is NewsUiState.Empty -> {
      NoConnectionFoundBox {
        viewModel.loadUiState()
      }
    }

    is NewsUiState.Sucess -> {
      PostList(
        onPostClick = onPostClick,
        posts = (uiState as NewsUiState.Sucess).posts,
      )
    }

  }
}