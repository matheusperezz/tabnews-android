package com.miwis.tabnewskt.data.services

import com.miwis.tabnewskt.domain.models.LoginAuthenticationModel
import com.miwis.tabnewskt.domain.models.LoginAuthenticationResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

  @POST("sessions")
  suspend fun loginTabnews(@Body requestBody: LoginAuthenticationModel): LoginAuthenticationResponse

}