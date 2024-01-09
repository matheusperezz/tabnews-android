package com.miwis.tabnewskt.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miwis.tabnewskt.data.dao.PostDao
import com.miwis.tabnewskt.data.models.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
  abstract fun postDao(): PostDao
}