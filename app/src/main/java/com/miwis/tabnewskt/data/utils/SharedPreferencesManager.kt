package com.miwis.tabnewskt.data.utils

import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar

class SharedPreferencesManager(context: Context) {
  private val sharedPreferences: SharedPreferences =
    context.getSharedPreferences("LoginCache", Context.MODE_PRIVATE)

  fun saveLogin(key: String, value: String){
    val editor = sharedPreferences.edit()
    editor.putString(key, value)
    editor.putLong(key + "_expiration", getExpireDate())
    editor.apply()
  }

  fun getLoginCache(key: String): String? {
    val expirationDate = sharedPreferences.getLong(key + "_expiration", 0)
    if (System.currentTimeMillis() > expirationDate){
      // Expired data, delete this
      removeData(key)
      return null
    }
    return sharedPreferences.getString(key, null)
  }

  private fun getExpireDate(): Long {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, 7)
    return calendar.timeInMillis
  }

  private fun removeData(key: String){
    val editor = sharedPreferences.edit()
    editor.remove(key)
    editor.remove(key + "_expiration")
    editor.apply()
  }
}