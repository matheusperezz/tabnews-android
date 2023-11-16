package com.miwis.tabnewskt.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miwis.tabnewskt.data.models.PostDetails
import com.miwis.tabnewskt.data.services.PostService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

data class PostDetails(val owner: String, val slug: String)

sealed class PostDetailsState {
  object Loading : PostDetailsState()
  data class Success(val postDetails: PostDetails) : PostDetailsState()
  data class Error(val errorMessage: String): PostDetailsState()
}

@HiltViewModel
class PostDetailsViewModel@Inject constructor(
  private val service: PostService
) : ViewModel() {

  private val _state = MutableStateFlow<PostDetailsState>(PostDetailsState.Loading)
  val state: StateFlow<PostDetailsState> get() = _state

  fun loadPostDetails(postSlug: String, postOwner: String){
    viewModelScope.launch {
      _state.value = PostDetailsState.Loading
      try {
        val details = service.fetchPostFromUser(postOwner, postSlug)
        _state.value = PostDetailsState.Success(details)
      } catch (e: Exception) {
        _state.value = PostDetailsState.Error("Erro ao carregar post")
      }
    }
  }

  fun cancelPostDetailsRequest() {
    Log.i("Tabnews", "cancelPostDetailsRequest: caneling")
  }

}


