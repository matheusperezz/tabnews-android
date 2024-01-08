package com.miwis.tabnewskt.data.models

data class LoginAuthenticationResponse(
  val id: String = "",
  val token: String = "",
  val expires_at: String = "",
  val created_at: String = "",
  // In case of error
  val message: String = ""
)

data class LoginAuthenticationModel(
  val email: String = "",
  val password: String = ""
)
