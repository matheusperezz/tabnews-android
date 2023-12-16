package com.miwis.tabnewskt.data.services

import com.miwis.tabnewskt.domain.models.LoginAuthenticationModel
import com.miwis.tabnewskt.domain.models.LoginAuthenticationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

  @POST("sessions")
  fun loginTabnews(@Body requestBody: LoginAuthenticationModel): LoginAuthenticationResponse

}