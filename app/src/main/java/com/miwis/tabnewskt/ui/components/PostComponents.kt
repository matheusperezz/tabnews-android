package com.miwis.tabnewskt.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.ui.theme.Typography

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
    items(posts) { post ->
      PostItem(post = post, onPostClick = onPostClick)
    }
  }
}

@Composable
fun PostItem(
  modifier: Modifier = Modifier,
  post: Post,
  onPostClick: (Post) -> Unit = {}
) {

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
        text = "${post.tabcoins} tabcoins  •  ${post.children_deep_count} comentários  •  ${post.published_at}",
        style = Typography.bodySmall,
      )

      post.owner_username?.let {
        Text(
          text = it,
          style = Typography.bodyMedium,
        )
      }
    }

  }
}

@Composable
fun PostDetails(
  post: Post,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(8.dp)
  )
  {
    post.slug?.let {
      Text(
        text = it,
        style = Typography.titleMedium,
        modifier = Modifier.padding(vertical = 8.dp)
      )
    }
    Row(
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      post.owner_username?.let { Text(text = it, style = Typography.labelSmall) }
      post.published_at?.let { Text(text = it, style = Typography.labelSmall) }
    }

  }
}
