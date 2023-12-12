package com.miwis.tabnewskt.domain.repositories

import com.miwis.tabnewskt.domain.models.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
  suspend fun fetchFirstRelevants(
    page: Int = 1,
    perPage: Int = 10,
    strategy: String = "relevant"
  ): Flow<List<Post>>
}