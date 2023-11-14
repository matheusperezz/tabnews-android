package com.miwis.tabnewskt.data.utils

import com.miwis.tabnewskt.data.models.Post
import java.util.Date



val samplePosts = listOf(
  Post(
    id = "1",
    ownerId = "2",
    parentId = null,
    slug = "post-1",
    title = "Este é o primeiro post",
    status = "published",
    sourceUrl = null,
    createdAt = Date(System.currentTimeMillis()),
    updatedAt = Date(System.currentTimeMillis()),
    publishedAt = Date(System.currentTimeMillis()),
    deletedAt = null,
    tabcoins = 10,
    ownerUsername = "João Doe",
    childrenDeepCount = 0
  ),
  Post(
    id = "2",
    ownerId = "3",
    parentId = null,
    slug = "post-2",
    title = "Este é o segundo post",
    status = "draft",
    sourceUrl = null,
    createdAt = Date(System.currentTimeMillis()),
    updatedAt = Date(System.currentTimeMillis()),
    publishedAt = Date(System.currentTimeMillis()),
    deletedAt = null,
    tabcoins = 5,
    ownerUsername = "Maria Silva",
    childrenDeepCount = 0
  ),
  Post(
    id = "3",
    ownerId = "4",
    parentId = "2",
    slug = "post-3",
    title = "Este é um comentário do segundo post",
    status = "published",
    sourceUrl = null,
    createdAt = Date(System.currentTimeMillis()),
    updatedAt = Date(System.currentTimeMillis()),
    publishedAt = Date(System.currentTimeMillis()),
    deletedAt = null,
    tabcoins = 3,
    ownerUsername = "Pedro Santos",
    childrenDeepCount = 1
  ),
  Post(
    id = "4",
    ownerId = "5",
    parentId = null,
    slug = "post-4",
    title = "Este é o terceiro post",
    status = "published",
    sourceUrl = null,
    createdAt = Date(System.currentTimeMillis()),
    updatedAt = Date(System.currentTimeMillis()),
    publishedAt = Date(System.currentTimeMillis()),
    deletedAt = null,
    tabcoins = 7,
    ownerUsername = "Ana Paula",
    childrenDeepCount = 0
  ),
  Post(
    id = "5",
    ownerId = "6",
    parentId = "4",
    slug = "post-5",
    title = "Este é um comentário do terceiro post",
    status = "published",
    sourceUrl = null,
    createdAt = Date(System.currentTimeMillis()),
    updatedAt = Date(System.currentTimeMillis()),
    publishedAt = Date(System.currentTimeMillis()),
    deletedAt = null,
    tabcoins = 2,
    ownerUsername = "Carlos Souza",
    childrenDeepCount = 1
  ),
  Post(
    id = "6",
    ownerId = "7",
    parentId = null,
    slug = "post-6",
    title = "Este é o quarto post",
    status = "published",
    sourceUrl = null,
    createdAt = Date(System.currentTimeMillis()),
    updatedAt = Date(System.currentTimeMillis()),
    publishedAt = Date(System.currentTimeMillis()),
    deletedAt = null,
    tabcoins = 1,
    ownerUsername = "Luana Oliveira",
    childrenDeepCount = 0
  ),
  Post(
    id = "7",
    ownerId = "8",
    parentId = null,
    slug = "post-7",
    title = "Este é o quinto post",
    status = "published",
    sourceUrl = null,
    createdAt = Date(System.currentTimeMillis()),
    updatedAt = Date(System.currentTimeMillis()),
    publishedAt = Date(System.currentTimeMillis()),
    deletedAt = null,
    tabcoins = 9,
    ownerUsername = "Eduardo Pereira",
    childrenDeepCount = 0
  )
)