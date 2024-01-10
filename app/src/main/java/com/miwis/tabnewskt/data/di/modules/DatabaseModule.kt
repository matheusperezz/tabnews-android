package com.miwis.tabnewskt.data.di.modules

import android.content.Context
import androidx.room.Room
import com.miwis.tabnewskt.data.dao.PostDao
import com.miwis.tabnewskt.data.dao.PostDetailsDao
import com.miwis.tabnewskt.data.database.AppDatabase
import com.miwis.tabnewskt.data.database.MIGRATION_1_2
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
    ) .addMigrations(MIGRATION_1_2)
      .build()
  }

  @Provides
  fun providePostDao(database: AppDatabase): PostDao {
    return database.postDao()
  }

  @Provides
  fun providePostDetailsDao(database: AppDatabase): PostDetailsDao {
    return database.postDetailsDao()
  }
}