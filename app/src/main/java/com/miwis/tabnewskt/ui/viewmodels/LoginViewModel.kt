package com.miwis.tabnewskt.ui.viewmodels

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miwis.tabnewskt.data.utils.isOnline
import com.miwis.tabnewskt.domain.models.LoginAuthenticationModel
import com.miwis.tabnewskt.domain.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
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