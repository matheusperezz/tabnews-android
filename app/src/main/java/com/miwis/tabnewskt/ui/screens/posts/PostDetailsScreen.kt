package com.miwis.tabnewskt.ui.screens.posts

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.miwis.tabnewskt.R
import com.miwis.tabnewskt.data.models.PostDetails
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

  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  DisposableEffect(Unit) {
    viewModel.loadPostDetails(postSlug.orEmpty(), postOwner.orEmpty())

    onDispose {
      viewModel.cancelPostDetailsRequest()
    }
  }

  when (uiState) {
    is PostDetailsState.Loading -> {
      Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
          Modifier.align(Alignment.Center)
        )
      }
    }

    is PostDetailsState.Success -> {
      val postDetails = (uiState as PostDetailsState.Success).postDetails
      val childrens = (uiState as PostDetailsState.Success).childrens
      PostDetailsAndChildrenList(postDetails = postDetails, childrens = childrens)
    }

    is PostDetailsState.Error -> {
      navController.popBackStack()
    }
  }
}

@Composable
private fun PostDetailsAndChildrenList(postDetails: PostDetails, childrens: List<PostDetails>) {
  LazyColumn {
    item {
      PostBody(postDetails)
    }
    items(childrens) {
      Spacer(modifier = Modifier.height(8.dp))
      PostChildren(postDetails = it)
    }
    item {
      Spacer(modifier = Modifier.height(8.dp))
    }
  }
}

@Composable
private fun PostBody(postDetails: PostDetails) {
  Column {
    Row(
      verticalAlignment = Alignment.Top,
    ) {
      UpAndDownVoteWithTabCoins(tabcoins = postDetails.tabcoins)

      Column {
        Text(text = "Título: ${postDetails.title}", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        MarkdownText(
          markdown = postDetails.body,
          fontResource = R.font.roboto_mono,
          color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
      }
    }
  }
}

@Composable
private fun PostChildren(postDetails: PostDetails) {
  Card {
    Row(
      Modifier
        .padding(vertical = 8.dp)
        .padding(end = 8.dp),
    ) {

      UpAndDownVoteWithTabCoins(postDetails.tabcoins)

      Column {
        Text(
          text = postDetails.owner_username, fontWeight = FontWeight.Bold, style = TextStyle(
            color = MaterialTheme.colorScheme.primary
          )
        )
        Spacer(modifier = Modifier.height(8.dp))
        MarkdownText(
          markdown = postDetails.body,
          fontResource = R.font.roboto_mono,
          color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
      }
    }
  }
}

@Composable
private fun UpAndDownVoteWithTabCoins(
  tabcoins: Int,
  onUpVoteClick: () -> Unit = {},
  onDownVoteClick: () -> Unit = {}
) {
  Column(modifier = Modifier.width(48.dp), horizontalAlignment = Alignment.CenterHorizontally) {
    IconButton(onClick = { onUpVoteClick() }) {
      Icon(Icons.Filled.KeyboardArrowUp, null)
    }

    Text(
      text = tabcoins.toString(), style = TextStyle(
        color = MaterialTheme.colorScheme.primary
      )
    )

    IconButton(onClick = { onDownVoteClick() }) {
      Icon(Icons.Filled.KeyboardArrowDown, null)
    }
  }
}