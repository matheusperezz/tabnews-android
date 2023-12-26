package com.miwis.tabnewskt.ui.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miwis.tabnewskt.domain.models.LoginAuthenticationModel
import com.miwis.tabnewskt.domain.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

data class LoginFormUiState(
  val email: String = "",
  val password: String = "",
  val onEmailChange: (String) -> Unit = {},
  val onPasswordChange: (String) -> Unit = {},
  val loginStatus: LoginStatus = LoginStatus.IDLE,
  val emailError: String = "",
  val passwordError: String = ""
)

enum class LoginStatus {
  IDLE,
  LOADING,
  SUCCESS,
  ERROR,
  NO_INTERNET
}

const val PREF_NAME = "LoginPrefs"
const val KEY_EMAIL = "email"
const val KEY_PASSWORD = "password"

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val repository: AuthRepository
) : ViewModel(), DefaultLifecycleObserver {

  private val _uiState: MutableStateFlow<LoginFormUiState> = MutableStateFlow(
    LoginFormUiState()
  )

  val uiState = _uiState.stateIn(
    viewModelScope,
    SharingStarted.Eagerly,
    initialValue = _uiState.value
  )

  init {
    _uiState.update { currentState ->
      currentState.copy(
        onEmailChange = {
          _uiState.value = _uiState.value.copy(
            email = it
          )
        },
        onPasswordChange = {
          _uiState.value = _uiState.value.copy(
            password = it
          )
        }
      )
    }
  }

  fun tryLogin(
    email: String,
    password: String,
    context: Context
  ) {
    viewModelScope.launch {
      try {
        val user = LoginAuthenticationModel(email, password)
        _uiState.update { it.copy(loginStatus = LoginStatus.LOADING) }
        repository.tryLogin(user)
          .collect {
            _uiState.update { it.copy(loginStatus = LoginStatus.SUCCESS) }
          }
      } catch (e: HttpException) {
        // Trate o erro aqui, por exemplo:
        if (e.code() == 400) {
          // Handle HTTP 400 Bad Request
          _uiState.update {
            it.copy(
              loginStatus = LoginStatus.ERROR,
              emailError = "E-mail inválido",
              passwordError = "Senha inválida"
            )
          }
        } else {
          // Handle outros códigos de erro HTTP, se necessário
          _uiState.update { it.copy(loginStatus = LoginStatus.NO_INTERNET) }
        }
      }
    }

  }

  // TODO: The user cant return from loginscreen, but on open app again, the cache expires
  fun tryCachedLogin(
    uiState: LoginFormUiState,
    context: Context,
    viewModel: LoginViewModel
  ) {
    if (uiState.loginStatus == LoginStatus.IDLE) {
      val savedEmail = getSavedEmail(context)
      val savedPassword = getSavedPassword(context)

      if (savedEmail.isNotEmpty() && savedPassword.isNotEmpty()) {
        viewModel.tryLogin(savedEmail, savedPassword, context)
      }
    }
  }

  fun resetCredentials() {
    _uiState.update {
      it.copy(
        passwordError = "",
        emailError = "",
        email = "",
        password = "",
        loginStatus = LoginStatus.LOADING
      )
    }
  }
}

private fun getSharedPreferences(context: Context): SharedPreferences {
  return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
}

private fun getSavedEmail(context: Context): String {
  return getSharedPreferences(context).getString(KEY_EMAIL, "") ?: ""
}

private fun getSavedPassword(context: Context): String {
  return getSharedPreferences(context).getString(KEY_PASSWORD, "") ?: ""
}

private fun saveCredentials(context: Context, email: String, password: String){
  val editor = getSharedPreferences(context).edit()
  editor.putString(KEY_EMAIL, email)
  editor.putString(KEY_PASSWORD, password)
  editor.apply()
}


