package com.miwis.tabnewskt.domain.repositories

import com.miwis.tabnewskt.data.services.PostService
import com.miwis.tabnewskt.domain.models.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PostRepositoryImpl(
  private val service: PostService
): PostRepository {
  override suspend fun fetchFirstRelevants(page: Int, perPage: Int, strategy: String): Flow<List<Post>> {
    return flowOf(service.fetchFirstRelevants(page, perPage, strategy))
  }
}