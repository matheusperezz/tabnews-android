package com.miwis.tabnewskt.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.miwis.tabnewskt.data.utils.showMessage
import com.miwis.tabnewskt.ui.theme.Typography
import com.miwis.tabnewskt.ui.viewmodels.LoginFormUiState
import com.miwis.tabnewskt.ui.viewmodels.LoginStatus
import com.miwis.tabnewskt.ui.viewmodels.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
  viewModel: LoginViewModel = hiltViewModel(),
  onForgotPasswordClick: () -> Unit = {},
  onSucessNavigate: () -> Unit = {}
) {

  // TODO: Melhorar a estrutura
  val uiState by viewModel.uiState.collectAsState()

  when (uiState.loginStatus) {

    LoginStatus.SUCCESS -> {
      onSucessNavigate()
    }

    LoginStatus.LOADING -> {
      Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
          Modifier.align(Alignment.Center)
        )
      }
    }

    else -> {
      LoginForm(
        uiState = uiState,
        viewModel = viewModel,
        onForgotPasswordClick = onForgotPasswordClick
      )
    }

  }

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoginForm(
  uiState: LoginFormUiState,
  viewModel: LoginViewModel,
  scope: CoroutineScope = rememberCoroutineScope(),
  onForgotPasswordClick: () -> Unit
) {
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
      value = uiState.email,
      onValueChange = {
        uiState.onEmailChange(it)
      },
      label = {
        Text(text = "E-mail")
      },
      leadingIcon = {
        Icon(Icons.Outlined.Email, null)
      },
      isError = uiState.emailError.isNotEmpty(),
      singleLine = true,
      modifier = Modifier.fillMaxWidth()
    )



    OutlinedTextField(
      value = uiState.password,
      onValueChange = {
        uiState.onPasswordChange(it)
      },
      label = {
        Text(text = "Senha")
      },
      leadingIcon = {
        Icon(Icons.Outlined.Lock, null)
      },
      visualTransformation = PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      modifier = Modifier.fillMaxWidth()
    )

    if (uiState.passwordError.isNotEmpty() or uiState.emailError.isNotEmpty()) {
      val context = LocalContext.current
      Toast.makeText(context, "Credenciais inválidas", Toast.LENGTH_LONG).show()
    }

    Button(onClick = {
      viewModel.tryLogin(uiState.email, uiState.password)
      viewModel.resetCredentials()
    }, modifier = Modifier.fillMaxWidth()) {
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