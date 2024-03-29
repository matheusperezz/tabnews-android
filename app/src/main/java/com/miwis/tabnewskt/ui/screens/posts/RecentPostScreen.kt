package com.miwis.tabnewskt.ui.screens.posts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.ui.components.NoConnectionFoundBox
import com.miwis.tabnewskt.ui.components.PostList
import com.miwis.tabnewskt.ui.viewmodels.RecentPostViewModel
import com.miwis.tabnewskt.ui.viewmodels.NewsUiState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecentPostScreen(
  viewModel: RecentPostViewModel = hiltViewModel(),
  onPostClick: (Post) -> Unit = {}
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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