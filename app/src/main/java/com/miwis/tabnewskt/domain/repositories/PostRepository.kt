package com.miwis.tabnewskt.domain.repositories

import com.miwis.tabnewskt.domain.models.Post
import com.miwis.tabnewskt.domain.models.PostDetails
import kotlinx.coroutines.flow.Flow

interface PostRepository {
  suspend fun fetchFirstRelevants(
    page: Int = 1,
    perPage: Int = 10,
    strategy: String = "relevant"
  ): Flow<List<Post>>

  suspend fun fetchNewPosts(
    page: Int = 1,
    perPage: Int = 10,
    strategy: String = "new"
  ): Flow<List<Post>>

  suspend fun fetchPostFromUser(
    user: String,
    slug: String
  ): PostDetails
}