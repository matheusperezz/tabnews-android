package com.miwis.tabnewskt.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miwis.tabnewskt.domain.models.LoginAuthenticationModel
import com.miwis.tabnewskt.domain.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginFormUiState(
  val email: String = "",
  val password: String = "",
  val onEmailChange: (String) -> Unit = {},
  val onPasswordChange: (String) -> Unit = {}
)

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val repository: AuthRepository
) : ViewModel() {

  private val _uiState: MutableStateFlow<LoginFormUiState> = MutableStateFlow(
    LoginFormUiState()
  )

  val uiState = _uiState.asStateFlow()

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
    password: String
  ) {
    viewModelScope.launch {
      val user = LoginAuthenticationModel(email, password)
      val response = repository.tryLogin(user)
      response.collect {
        Log.i("Trying Login", "tryLogin: ${it.message} ${it.id}")
      }
    }
  }

}