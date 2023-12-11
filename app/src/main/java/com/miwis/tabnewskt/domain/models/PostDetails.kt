package com.miwis.tabnewskt.domain.models

data class PostDetails(
  val id: String,
  val owner_id: String?,
  val parend_id: String?,
  val slug: String,
  val title: String?,
  val status: String?,
  val body: String,
  val source_url: String?,
  val created_at: String,
  val updated_at: String?,
  val published_at: String?,
  val deleted_at: String?,
  val tabcoins: Int,
  val owner_username: String,
  val children_deep_count: Int?
)