package com.miwis.tabnewskt.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import java.util.Date

@Composable
fun NewTabsScreen() {
  Column {
    Text(text = "Recentes")
  }
}

@Composable
fun PostList(
  posts: List<Post> = samplePosts
) {
  LazyColumn {
    items(posts) { post ->
      PostItem(post)
    }
  }
}

@Composable
fun PostItem(
  post: Post,
  modifier: Modifier = Modifier
) {
  val postedAt = post.publishedAt.convertToPostDate(post)

  Card(
    modifier = modifier
      .padding(16.dp)
      .fillMaxWidth()
      .heightIn(min = 100.dp, max = 200.dp)
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

@Preview(showBackground = true)
@Composable
private fun PostItemPreview() {
  PostItem(post = samplePosts.first())
}