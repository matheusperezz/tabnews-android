package com.miwis.tabnewskt.data.models

import java.util.Date

data class Post(
  val id: String,
  val ownerId: String,
  val parentId: String?,
  val slug: String,
  val title: String,
  val status: String,
  val sourceUrl: String?,
  val createdAt: Date,
  val updatedAt: Date,
  val publishedAt: Date,
  val deletedAt: Date?,
  val tabcoins: Int,
  val ownerUsername: String,
  val childrenDeepCount: Int
)
