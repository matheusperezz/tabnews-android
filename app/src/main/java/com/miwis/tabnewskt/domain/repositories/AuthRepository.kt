package com.miwis.tabnewskt.domain.repositories

import com.miwis.tabnewskt.domain.models.LoginAuthenticationModel
import com.miwis.tabnewskt.domain.models.LoginAuthenticationResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
  suspend fun tryLogin(requestBody: LoginAuthenticationModel): Flow<LoginAuthenticationResponse>
}