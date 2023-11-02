package com.miwis.tabnewskt.ui.screens

import androidx.compose.runtime.Composable
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.ui.components.PostList

@Composable
fun RelevantTabsScreen(
  onPostClick: (Post) -> Unit = {}
) {
  PostList(onPostClick = onPostClick)
}