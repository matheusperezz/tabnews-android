package com.miwis.tabnewskt.data.models

import com.squareup.moshi.Json
import java.util.Date

data class Post(
  val id: String,
  val ownerId: String?,
  val parentId: String?,
  val slug: String?,
  val title: String?,
  val status: String?,
  val sourceUrl: String?,
  @Json(name = "created_at")
  val createdAt: Date?,
  @Json(name = "updated_at")
  val updatedAt: Date?,
  @Json(name = "published_at")
  val publishedAt: Date?,
  @Json(name = "deleted_at")
  val deletedAt: Date?,
  val tabcoins: Int,
  val ownerUsername: String?,
  val childrenDeepCount: Int?
)
