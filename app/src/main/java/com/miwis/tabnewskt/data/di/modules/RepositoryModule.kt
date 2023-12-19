package com.miwis.tabnewskt.data.di.modules

import com.miwis.tabnewskt.data.services.AuthService
import com.miwis.tabnewskt.data.services.PostService
import com.miwis.tabnewskt.domain.repositories.AuthRepository
import com.miwis.tabnewskt.domain.repositories.AuthRepositoryImpl
import com.miwis.tabnewskt.domain.repositories.PostRepository
import com.miwis.tabnewskt.domain.repositories.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  fun providePostRepository(service: PostService): PostRepository {
    return PostRepositoryImpl(service)
  }

  @Provides
  fun provideAuthRepository(service: AuthService): AuthRepository {
    return AuthRepositoryImpl(service)
  }

}