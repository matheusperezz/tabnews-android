package com.miwis.tabnewskt.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miwis.tabnewskt.data.models.Post
import com.miwis.tabnewskt.data.utils.samplePosts
import com.miwis.tabnewskt.ui.uistates.RelevantUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RelevantTabsViewModel @Inject constructor() : ViewModel() {

  private var currentUiStateJob: Job? = null
  private val _uiState = MutableStateFlow<RelevantUiState>(
    RelevantUiState.Loading
  )
  val uiState = _uiState.asStateFlow()

  init {
    loadUiState()
  }

  private fun loadUiState(){
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

  fun fetchTabs(): Flow<List<Post>> {
    return flowOf(samplePosts)
  }

}