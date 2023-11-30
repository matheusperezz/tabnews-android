package com.miwis.tabnewskt.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.miwis.tabnewskt.R
import com.miwis.tabnewskt.ui.viewmodels.PostDetailsState
import com.miwis.tabnewskt.ui.viewmodels.PostDetailsViewModel
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun PostDetailsScreen(
  viewModel: PostDetailsViewModel = hiltViewModel(),
  postOwner: String,
  postSlug: String,
  navController: NavHostController
) {

  val uiState by viewModel.state.collectAsState()

  DisposableEffect(Unit) {
    viewModel.loadPostDetails(postSlug.orEmpty(), postOwner.orEmpty())

    onDispose {
      viewModel.cancelPostDetailsRequest()
    }
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 8.dp)
      .padding(vertical = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    when (uiState) {
      is PostDetailsState.Loading -> {
        CircularProgressIndicator()
      }

      is PostDetailsState.Success -> {
        val postDetails = (uiState as PostDetailsState.Success).postDetails
        Text(text = "Título: ${postDetails.title}", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        MarkdownText(
          markdown = postDetails.body,
          fontResource = R.font.roboto_mono,
          color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )

      }

      is PostDetailsState.Error -> {
        navController.popBackStack()
      }
    }
  }
}