package com.miwis.tabnewskt.data.di.modules

import android.text.util.Rfc822Token
import com.miwis.tabnewskt.data.network.DateAdapter
import com.miwis.tabnewskt.data.services.PostService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date
import javax.inject.Singleton

const val TABNEWS_BASE_URL = "https://www.tabnews.com.br/api/v1/"

@Module
@InstallIn(SingletonComponent::class)
object TabnewsApiModule {

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit {
    val moshi = Moshi.Builder()
      .add(DateAdapter())
      .build()

    return Retrofit.Builder()
      .baseUrl(TABNEWS_BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

  @Provides
  @Singleton
  fun providePostService(retrofit: Retrofit): PostService {
    return retrofit.create(PostService::class.java)
  }

}