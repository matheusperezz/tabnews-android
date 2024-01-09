package com.miwis.tabnewskt.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.miwis.tabnewskt.data.models.Post

@Dao
interface PostDao {

  @Query("SELECT * FROM cache_posts")
  fun getAll(): List<Post>

  @Insert
  fun insertPost(post: Post)

  @Insert
  fun insertTenPosts(posts: List<Post>)

  @Query("DELETE FROM cache_posts")
  fun deleteAllPosts()
}