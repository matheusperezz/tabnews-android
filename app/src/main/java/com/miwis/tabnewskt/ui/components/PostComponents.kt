package com.miwis.tabnewskt.ui.components

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.data.utils.convertDateToString
import com.miwis.tabnewskt.ui.theme.Typography

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostList(
  modifier: Modifier = Modifier,
  posts: List<Post> = emptyList(),
  onPostClick: (Post) -> Unit = {},
) {
  LazyColumn(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    item {
      Spacer(modifier = modifier.height(4.dp))
    }

    items(posts) { post ->
      PostItem(post = post, onPostClick = onPostClick)
    }

    item {
      Spacer(modifier = modifier.height(4.dp))
    }
  }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostItem(
  modifier: Modifier = Modifier,
  post: Post,
  onPostClick: (Post) -> Unit = {}
) {

  val dateFormated = convertDateToString(post.created_at)

  Card(
    modifier = modifier
      .fillMaxWidth()
      .heightIn(min = 100.dp, max = 200.dp)
      .clickable { onPostClick(post) }
  ) {
    Column(
      modifier = Modifier.padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      post.title?.let {
        Text(
          text = it,
          style = Typography.titleMedium,
          modifier = Modifier
            .fillMaxWidth()
            .heightIn(30.dp)
        )
      }

      Text(
        text = "${post.tabcoins} tabcoins  •  ${post.children_deep_count} comentários  •  $dateFormated",
        style = Typography.bodySmall,
      )

      Text(
        text = post.owner_username,
        style = Typography.bodyMedium,
      )
    }

  }
}

@Composable
fun InternetConnection(
  content: @Composable () -> Unit
) {
  var isInternetAvailable by remember { mutableStateOf(false) }
  val context = LocalContext.current

  DisposableEffect(Unit) {
    isInternetAvailable = isInternetConnected(context)
    onDispose {  }
  }
  
  if (isInternetAvailable){
    content()
  } else {
    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = "Sem conexão")
    }
  }
}

fun isInternetConnected(context: Context): Boolean {
  val connectivityManager =
    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  val network = connectivityManager.activeNetwork
  val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
  return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}