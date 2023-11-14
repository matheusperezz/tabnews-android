package com.miwis.tabnewskt.data.di.modules

import com.miwis.tabnewskt.data.services.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

const val TABNEWS_BASE_URL = "https://www.tabnews.com.br/api/v1/"

@Module
@InstallIn(SingletonComponent::class)
object TabnewsApiModule {

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl(TABNEWS_BASE_URL)
      .build()
  }

  @Provides
  @Singleton
  fun providePostService(retrofit: Retrofit): PostService {
    return retrofit.create(PostService::class.java)
  }

}