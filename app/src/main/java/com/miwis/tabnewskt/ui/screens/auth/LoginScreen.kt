package com.miwis.tabnewskt.ui.screens.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miwis.tabnewskt.ui.components.NoConnectionFoundBox
import com.miwis.tabnewskt.ui.theme.Typography
import com.miwis.tabnewskt.ui.viewmodels.KEY_EMAIL
import com.miwis.tabnewskt.ui.viewmodels.KEY_PASSWORD
import com.miwis.tabnewskt.ui.viewmodels.LoginFormUiState
import com.miwis.tabnewskt.ui.viewmodels.LoginStatus
import com.miwis.tabnewskt.ui.viewmodels.LoginViewModel
import com.miwis.tabnewskt.ui.viewmodels.PREF_NAME
import java.util.prefs.Preferences

@Composable
fun LoginScreen(
  viewModel: LoginViewModel = hiltViewModel(),
  onForgotPasswordClick: () -> Unit = {},
  onSucessNavigate: () -> Unit = {}
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val context = LocalContext.current

  LaunchedEffect(key1 = uiState){
    viewModel.tryCachedLogin(uiState, context, viewModel)
  }

  when (uiState.loginStatus) {
    LoginStatus.SUCCESS -> onSucessNavigate()
    LoginStatus.LOADING -> LoadingScreen()
    LoginStatus.NO_INTERNET -> Text(text = "no internet")
    else -> LoginForm(uiState, viewModel, onForgotPasswordClick)
  }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoginForm(
  uiState: LoginFormUiState,
  viewModel: LoginViewModel,
  onForgotPasswordClick: () -> Unit
) {
  val context = LocalContext.current
  Column(
    verticalArrangement = Arrangement.spacedBy(12.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    Text(
      text = "Tabnews KT",
      style = Typography.titleLarge,
    )

    OutlinedTextField(
      value = uiState.email,
      onValueChange = { uiState.onEmailChange(it) },
      label = { Text(text = "E-mail") },
      leadingIcon = { Icon(Icons.Outlined.Email, null) },
      isError = uiState.emailError.isNotEmpty(),
      singleLine = true,
      modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
      value = uiState.password,
      onValueChange = { uiState.onPasswordChange(it) },
      label = { Text(text = "Senha") },
      leadingIcon = { Icon(Icons.Outlined.Lock, null) },
      visualTransformation = PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      modifier = Modifier.fillMaxWidth()
    )

    ValidationMessage(uiState)

    Button(
      onClick = {
        viewModel.tryLogin(uiState.email, uiState.password, context)
        viewModel.resetCredentials()
      },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = "Entrar")
    }

    TextButton(
      onClick = { onForgotPasswordClick() },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = "Esqueci a minha senha")
    }
  }
}


@Composable
private fun ValidationMessage(uiState: LoginFormUiState) {
  if (uiState.loginStatus == LoginStatus.ERROR) {
    val context = LocalContext.current
    Toast.makeText(context, "Credenciais inválidas", Toast.LENGTH_LONG).show()
  }
}

@Composable
private fun LoadingScreen() {
  Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    CircularProgressIndicator()
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreivew() {
  LoginScreen()
}