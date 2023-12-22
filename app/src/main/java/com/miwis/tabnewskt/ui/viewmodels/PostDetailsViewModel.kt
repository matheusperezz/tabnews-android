package com.miwis.tabnewskt.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miwis.tabnewskt.domain.models.PostDetails
import com.miwis.tabnewskt.data.services.PostService
import com.miwis.tabnewskt.domain.models.Post
import com.miwis.tabnewskt.domain.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

data class PostDetails(val owner: String, val slug: String)

sealed class PostDetailsState {
  object Loading : PostDetailsState()
  data class Success(val postDetails: PostDetails, val childrens: List<PostDetails>) : PostDetailsState()
  data class Error(val errorMessage: String) : PostDetailsState()

}

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
  private val repository: PostRepository
) : ViewModel() {

  private val _uiState = MutableStateFlow<PostDetailsState>(PostDetailsState.Loading)
  val uiState = _uiState.asStateFlow()

  fun loadPostDetails(postSlug: String, postOwner: String) {
    viewModelScope.launch {
      _uiState.value = PostDetailsState.Loading
      try {
        val details = repository.fetchPostFromUser(postOwner, postSlug)
        repository.fetchChildrensFromPost(postOwner, postSlug)
          .collect { childrens ->
            if (childrens.isEmpty()){
              _uiState.update { PostDetailsState.Success(details, emptyList()) }
            } else {
              _uiState.value = PostDetailsState.Success(details, childrens)
            }
          }
      } catch (e: Exception) {
        _uiState.value = PostDetailsState.Error("Erro ao carregar post")
      }
    }
  }

  fun cancelPostDetailsRequest() {
    Log.i("Tabnews", "cancelPostDetailsRequest: caneling")
  }

  private suspend fun fetchChildrens(user: String, slug: String): Flow<List<PostDetails>> {
    return try {
      repository.fetchChildrensFromPost(user, slug)
    } catch (e: Exception) {
      flowOf(emptyList())
    }
  }
}


