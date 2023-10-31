package com.miwis.tabnewskt.data.utils

import com.miwis.tabnewskt.data.models.Post
import java.util.Date

fun Date.convertToPostDate(post: Post): String {
  val publishedAt = post.publishedAt
  val now = Date()

  val differenceInDays = now.time - publishedAt.time
  val differenceInHours = differenceInDays / (1000 * 60 * 60)
  val differenceInMinutes = differenceInDays / (1000 * 60)

  val formattedDate = when {
    differenceInDays > 0 -> {
      val days = differenceInDays.toInt()
      if (days == 1) {
        "Ontem"
      } else {
        "$days dias atrás"
      }
    }
    differenceInHours > 0 -> {
      val hours = differenceInHours.toInt()
      if (hours == 1) {
        "1 hora atrás"
      } else {
        "$hours horas atrás"
      }
    }
    differenceInMinutes > 0 -> {
      val minutes = differenceInMinutes.toInt()
      if (minutes == 1) {
        "1 minuto atrás"
      } else {
        "$minutes minutos atrás"
      }
    }
    else -> {
      "Agora"
    }
  }

  return formattedDate
}