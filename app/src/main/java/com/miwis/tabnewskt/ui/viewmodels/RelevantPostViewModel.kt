package com.miwis.tabnewskt.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.data.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RelevantUiState {
  object Loading: RelevantUiState()
  object Empty: RelevantUiState()
  data class Sucess(
    val posts: List<Post> = emptyList()
  ): RelevantUiState()

}
@HiltViewModel
class RelevantPostViewModel @Inject constructor(
  private val repository: PostRepository
) : ViewModel() {

  private var currentUiStateJob: Job? = null
  private val _uiState = MutableStateFlow<RelevantUiState>(
    RelevantUiState.Loading
  )
  val uiState = _uiState.asStateFlow()

  init {
    loadUiState()
  }

  fun loadUiState(){
    currentUiStateJob?.cancel()
    currentUiStateJob = viewModelScope.launch {
      fetchTabs()
        .onStart {
          _uiState.update { RelevantUiState.Loading }
        }
        .collect { posts ->
          if (posts.isEmpty()) {
            _uiState.update { RelevantUiState.Empty }
          } else {
            _uiState.update { RelevantUiState.Sucess(posts) }
          }
        }
    }
  }

  private suspend fun fetchTabs(): Flow<List<Post>> {
    return try {
      repository.fetchFirstRelevants()
    } catch (e: Exception) {
      // Aqui você pode tratar o erro de falta de conectividade
      _uiState.update { RelevantUiState.Empty }
      flowOf(emptyList())
    }
  }

}