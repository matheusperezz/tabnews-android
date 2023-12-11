package com.miwis.tabnewskt.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.miwis.tabnewskt.domain.models.Post
import com.miwis.tabnewskt.ui.components.NoConnectionFoundBox
import com.miwis.tabnewskt.ui.components.PostList
import com.miwis.tabnewskt.ui.viewmodels.RelevantTabsViewModel
import com.miwis.tabnewskt.ui.viewmodels.RelevantUiState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RelevantTabsScreen(
  viewModel: RelevantTabsViewModel = hiltViewModel(),
  onPostClick: (Post) -> Unit = {}
) {

  val uiState by viewModel.uiState.collectAsState()

  when (uiState) {
    is RelevantUiState.Loading -> {
      Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
          Modifier.align(Alignment.Center)
        )
      }
    }

    is RelevantUiState.Empty -> {
      NoConnectionFoundBox {
        viewModel.loadUiState()
      }
    }

    is RelevantUiState.Sucess -> {
      PostList(
        onPostClick = onPostClick,
        posts = (uiState as RelevantUiState.Sucess).posts,
      )
    }
  }
}
