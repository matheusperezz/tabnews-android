package com.miwis.tabnewskt.ui.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class NewPostFormUiState(
  val title: String = "",
  val body: String = "",
  val bibliography: String = "",
  val onTitleChange: (String) -> Unit = {},
  val onBodyChange: (String) -> Unit = {},
  val onBibliographyChange: (String) -> Unit = {}
)

@HiltViewModel
class NewPostViewModel @Inject constructor() : ViewModel(){
  private val _uiState: MutableStateFlow<NewPostFormUiState> = MutableStateFlow(
    NewPostFormUiState()
  )

  val uiState = _uiState.asStateFlow()

  init {
    _uiState.update { currentState ->
      currentState.copy(
        onTitleChange = {
          _uiState.value = _uiState.value.copy(
            title = it
          )
        },
        onBodyChange = {
          _uiState.value = _uiState.value.copy(
            body = it
          )
        },
        onBibliographyChange = {
          _uiState.value = _uiState.value.copy(
            bibliography = it
          )
        }
      )
    }
  }
}