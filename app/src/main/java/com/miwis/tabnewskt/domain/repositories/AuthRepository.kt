package com.miwis.tabnewskt.domain.repositories

import com.miwis.tabnewskt.data.services.AuthService
import com.miwis.tabnewskt.domain.models.LoginAuthenticationModel
import com.miwis.tabnewskt.domain.models.LoginAuthenticationResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface AuthRepository {
  suspend fun tryLogin(requestBody: LoginAuthenticationModel): Flow<LoginAuthenticationResponse>
}

class AuthRepositoryImpl(
  private val service: AuthService
): AuthRepository {
  override suspend fun tryLogin(requestBody: LoginAuthenticationModel): Flow<LoginAuthenticationResponse> {
    return flowOf(service.loginTabnews(requestBody))
  }
}