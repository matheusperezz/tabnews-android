package com.miwis.tabnewskt.data.repositories

import com.miwis.tabnewskt.data.dao.PostDao
import com.miwis.tabnewskt.data.dao.PostDetailsDao
import com.miwis.tabnewskt.data.services.PostService
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.data.models.PostDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

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

  suspend fun fetchChildrensFromPost(
    user: String,
    slug: String
  ): Flow<List<PostDetails>>

  suspend fun insertRecentPosts(
    posts: List<Post>
  )

  suspend fun getLocalRelevantPosts(): List<Post>
}

class PostRepositoryImpl(
  private val service: PostService,
  private val postDao: PostDao,
  private val detailsDao: PostDetailsDao
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

  override suspend fun fetchChildrensFromPost(user: String, slug: String): Flow<List<PostDetails>> {
    return flowOf(service.fetchChildrensFromPost(user, slug))
  }

  override suspend fun insertRecentPosts(posts: List<Post>) {
    postDao.insertAllPosts(posts)
  }

  override suspend fun getLocalRelevantPosts(): List<Post> {
    return postDao.getAll()
  }
}