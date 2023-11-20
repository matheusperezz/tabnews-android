package com.miwis.tabnewskt.data.services

import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.data.models.PostDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Requisições são feitas assim:
 * https://www.tabnews.com.br/api/v1/contents/{{user}}/{{slug}}/
 *
 * **/

interface PostService {

  @GET("contents")
  suspend fun fetchFirstRelevants(
    @Query("page") page: Int = 1,
    @Query("per_page") perPage: Int = 10,
    @Query("strategy") strategy: String = "relevant"
  ): List<Post>

  @GET("contents")
  suspend fun fetchNewPosts(
    @Query("page") page: Int = 1,
    @Query("per_page") perPage: Int = 10,
    @Query("strategy") strategy: String = "new"
  ): List<Post>

  @GET("contents/{user}/{slug}")
  suspend fun fetchPostFromUser(
    @Path("user") user: String,
    @Path("slug") slug: String
  ): PostDetails

}