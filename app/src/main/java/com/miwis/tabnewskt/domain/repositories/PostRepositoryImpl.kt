package com.miwis.tabnewskt.domain.repositories

import com.miwis.tabnewskt.data.services.PostService
import com.miwis.tabnewskt.domain.models.Post
import com.miwis.tabnewskt.domain.models.PostDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PostRepositoryImpl(
  private val service: PostService
): PostRepository {
  override suspend fun fetchFirstRelevants(page: Int, perPage: Int, strategy: String): Flow<List<Post>> {
    return flowOf(service.fetchFirstRelevants(page, perPage, strategy))
  }

  override suspend fun fetchNewPosts(page: Int, perPage: Int, strategy: String): Flow<List<Post>> {
    return flowOf(service.fetchNewPosts(page, perPage, strategy))
  }

  override suspend fun fetchPostFromUser(user: String, slug: String): PostDetails {
    return service.fetchPostFromUser(user, slug)
  }
}