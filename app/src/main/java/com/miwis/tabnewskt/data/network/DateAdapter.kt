package com.miwis.tabnewskt.data.network

import android.annotation.SuppressLint
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date

class DateAdapter : JsonAdapter<Date>() {

  @SuppressLint("SimpleDateFormat")
  private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

  @FromJson
  override fun fromJson(reader: JsonReader): Date? {
    return dateFormat.parse(reader.nextString())
  }

  @ToJson
  override fun toJson(writer: JsonWriter, value: Date?) {
    writer.value(value?.let { dateFormat.format(it) })
  }

}