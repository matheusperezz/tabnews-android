package com.miwis.tabnewskt.ui.screens.posts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.ui.components.NoConnectionFoundBox
import com.miwis.tabnewskt.ui.components.PostList
import com.miwis.tabnewskt.ui.viewmodels.RelevantPostViewModel
import com.miwis.tabnewskt.ui.viewmodels.RelevantUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RelevantPostScreen(
  viewModel: RelevantPostViewModel = hiltViewModel(),
  onPostClick: (Post) -> Unit = {}
) {

  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
      val scope = rememberCoroutineScope()
      LaunchedEffect(Unit){
        scope.launch(Dispatchers.IO) {
          viewModel.insertPost((uiState as RelevantUiState.Sucess).posts)
        }
      }
      PostList(
        onPostClick = onPostClick,
        posts = (uiState as RelevantUiState.Sucess).posts,
      )
    }
  }
}
