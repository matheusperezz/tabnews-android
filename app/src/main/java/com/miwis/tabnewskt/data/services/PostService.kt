package com.miwis.tabnewskt.data.services

import com.miwis.tabnewskt.data.models.Post
import retrofit2.http.GET

/**
 * Requisições são feitas assim:
 * https://www.tabnews.com.br/api/v1/contents/{{usuario}}/{{titulo_do_post}}/
 *
 * **/

interface PostService {

  @GET("contents")
  suspend fun fetchFirstRelevants(): List<Post>

}