package com.miwis.tabnewskt.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miwis.tabnewskt.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
  onEnterClick: () -> Unit = {},
  onForgotPasswordClick: () -> Unit = {}
) {
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }

  Column(
    verticalArrangement = Arrangement.spacedBy(
      space = 12.dp,
      alignment = Alignment.CenterVertically
    ),
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
  ) {

    Text(
      text = "Tabnews KT",
      style = Typography.titleLarge,
    )

    OutlinedTextField(
      value = email,
      onValueChange = {
        email = it
      },
      label = {
        Text(text = "E-mail")
      },
      leadingIcon = {
        Icon(Icons.Outlined.Email, null)
      },
      modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
      value = password,
      onValueChange = {
        password = it
      },
      label = {
        Text(text = "Senha")
      },
      leadingIcon = {
        Icon(Icons.Outlined.Lock, null)
      },
      modifier = Modifier.fillMaxWidth()
    )

    Button(onClick = { onEnterClick() }, modifier = Modifier.fillMaxWidth()) {
      Text(text = "Entrar")
    }

    TextButton(onClick = { onForgotPasswordClick() }, modifier = Modifier.fillMaxWidth()) {
      Text(text = "Esqueci a minha senha")
    }

  }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreivew() {
  LoginScreen()
}