package com.miwis.tabnewskt.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miwis.tabnewskt.data.dao.PostDao
import com.miwis.tabnewskt.data.dao.PostDetailsDao
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.data.models.PostDetails

@Database(entities = [Post::class, PostDetails::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
  abstract fun postDao(): PostDao
  abstract fun postDetailsDao(): PostDetailsDao
}