package com.miwis.tabnewskt.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.miwis.tabnewskt.data.models.PostDetails

@Dao
interface PostDetailsDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertPostDetails(posts: PostDetails)

}