package com.miwis.tabnewskt.data.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
fun convertDateToString(dateString: String): String {
  val dateTimeFormatter = DateTimeFormatter.ISO_INSTANT
  val dateConverted = Instant.from(dateTimeFormatter.parse(dateString))
  val zonedDateTime = ZonedDateTime.ofInstant(dateConverted, ZoneId.of("America/Sao_Paulo"))

  val time = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))

  val hourDiff = ChronoUnit.HOURS.between(zonedDateTime, time)
  val dayDiff = ChronoUnit.DAYS.between(zonedDateTime, time)

  return when {
    hourDiff < 1 -> "$hourDiff horas atrás"
    dayDiff == 1L -> "1 dia atrás"
    dayDiff > 1 -> "$dayDiff dias atrás"
    else -> "Recentemente"
  }
}