package com.miwis.tabnewskt.data.di.modules

import android.content.Context
import androidx.room.Room
import com.miwis.tabnewskt.data.dao.PostDao
import com.miwis.tabnewskt.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "cache_db"

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      DATABASE_NAME
    ) .build()
  }

  @Provides
  fun providePostDao(database: AppDatabase): PostDao {
    return database.postDao()
  }
}