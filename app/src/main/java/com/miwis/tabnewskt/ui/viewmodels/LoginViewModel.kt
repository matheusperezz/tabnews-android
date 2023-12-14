package com.miwis.tabnewskt.ui.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class LoginFormUiState(
  val email: String = "",
  val password: String = "",
  val onEmailChange: (String) -> Unit = {},
  val onPasswordChange: (String) -> Unit = {}
)

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

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

}