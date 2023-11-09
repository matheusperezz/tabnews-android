package com.miwis.tabnewskt.ui.uistates

import com.miwis.tabnewskt.data.models.Post

sealed class RelevantUiState {

  object Loading: RelevantUiState()

  object Empty: RelevantUiState()

  data class Sucess(
    val posts: List<Post> = emptyList()
  ): RelevantUiState()

}