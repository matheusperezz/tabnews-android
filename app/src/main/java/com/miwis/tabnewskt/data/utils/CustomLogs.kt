package com.miwis.tabnewskt.data.utils

import android.content.Context
import android.util.Log

private const val TAG = "TABNEWS-LOG"

fun Context.showMessage(message: String){
  Log.i(TAG, message)
}