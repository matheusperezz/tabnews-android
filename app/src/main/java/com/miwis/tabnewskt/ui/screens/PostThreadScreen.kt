package com.miwis.tabnewskt.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.miwis.tabnewskt.ui.viewmodels.PostDetailsState
import com.miwis.tabnewskt.ui.viewmodels.PostDetailsViewModel

@Composable
fun PostDetailsScreen(
  viewModel: PostDetailsViewModel,
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
      .padding(8.dp),
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
        Log.i("TabnewsKTKT", "PostDetailsScreen: ${postDetails.body}")
        Text(text = """
          ${postDetails.body}
        """.trimIndent())
      }
      is PostDetailsState.Error -> {
        navController.popBackStack()
      }
    }
  }
}