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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.data.utils.convertToPostDate
import com.miwis.tabnewskt.data.utils.samplePosts
import com.miwis.tabnewskt.ui.theme.Typography

@Composable
fun PostList(
  posts: List<Post> = emptyList(),
  onPostClick: (Post) -> Unit = {},
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(posts) { post ->
      PostItem(post, onPostClick = onPostClick)
    }
  }
}

@Composable
fun PostItem(
  post: Post,
  onPostClick: (Post) -> Unit = {},
  modifier: Modifier = Modifier
) {
  val postedAt = post.publishedAt.convertToPostDate(post)

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
      Text(
        text = post.title,
        style = Typography.titleMedium,
        modifier = Modifier
          .fillMaxWidth()
          .heightIn(30.dp)
      )

      Text(
        text = "${post.tabcoins} tabcoins  •  ${post.childrenDeepCount} comentários  •  $postedAt",
        style = Typography.bodySmall,
      )

      Text(
        text = post.ownerUsername,
        style = Typography.bodyMedium,
      )
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
    Text(
      text = post.slug,
      style = Typography.titleMedium,
      modifier = Modifier.padding(vertical = 8.dp)
    )
    Row(
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      Text(text = post.ownerUsername, style = Typography.labelSmall)
      Text(text = post.publishedAt.convertToPostDate(post), style = Typography.labelSmall)
    }

  }
}

@Preview(showBackground = true)
@Composable
private fun PostItemPreview() {
  PostItem(post = samplePosts.first())
}

@Preview(showBackground = true)
@Composable
private fun PostListPreview() {
  PostList()
}

@Preview(showBackground = true)
@Composable
fun PostDetailsPreview() {
  PostDetails(post = samplePosts.first())
}