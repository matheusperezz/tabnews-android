package com.miwis.tabnewskt.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miwis.tabnewskt.domain.models.Post
import com.miwis.tabnewskt.data.services.PostService
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

sealed class NewsUiState {

  object Loading : NewsUiState()

  object Empty : NewsUiState()

  data class Sucess(
    val posts: List<Post> = emptyList()
  ) : NewsUiState()

}

@HiltViewModel
class NewTabsViewModel @Inject constructor(
  private val service: PostService
) : ViewModel() {
  private var currentUiState: Job? = null
  private val _uiState = MutableStateFlow<NewsUiState>(
    NewsUiState.Loading
  )
  val uiState = _uiState.asStateFlow()

  init {
    loadUiState()
  }

  fun loadUiState(){
    currentUiState?.cancel()
    currentUiState = viewModelScope.launch {
      fetchNews()
        .onStart {
          _uiState.update { NewsUiState.Loading }
        }
        .collect { posts->
          if (posts.isEmpty()){
            _uiState.update { NewsUiState.Empty }
          } else {
            _uiState.update { NewsUiState.Sucess(posts) }
          }
        }
    }
  }

  private suspend fun fetchNews(): Flow<List<Post>> {
    return try {
      flowOf(service.fetchFirstRelevants())
    } catch (e: Exception) {
      _uiState.update { NewsUiState.Empty }
      flowOf(emptyList())
    }
  }
}