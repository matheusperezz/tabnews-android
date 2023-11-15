package com.miwis.tabnewskt.data.services

import com.miwis.tabnewskt.data.models.Post
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Requisições são feitas assim:
 * https://www.tabnews.com.br/api/v1/contents/{{usuario}}/{{titulo_do_post}}/
 *
 * **/

interface PostService {

  @GET("contents")
  suspend fun fetchFirstRelevants(
    @Query("page") page: Int = 1,
    @Query("per_page") perPage: Int = 10,
    @Query("strategy") strategy: String = "relevant"
  ): List<Post>

}