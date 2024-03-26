package com.miwis.tabnewskt.ui.screens.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miwis.tabnewskt.ui.theme.Typography
import com.miwis.tabnewskt.ui.viewmodels.CreatePostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewPostScreen(
  viewModel: CreatePostViewModel = hiltViewModel()
) {

  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  Column(
    verticalArrangement = Arrangement.spacedBy(
      space = 16.dp,
      alignment = Alignment.CenterVertically
    ),
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
  ) {

    Text(
      text = "Publicar novo conteúdo",
      style = Typography.titleMedium,
      textAlign = TextAlign.Center,
      modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
      value = uiState.title,
      label = { Text(text = "Título") },
      onValueChange = uiState.onTitleChange,
      modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
      value = uiState.body,
      onValueChange = uiState.onBodyChange,
      label = { Text(text = "Conteúdo") },
      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
      modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
    )

    OutlinedTextField(
      value = uiState.bibliography,
      onValueChange = uiState.onBibliographyChange,
      label = { Text(text = "Bibliografia Opcional") },
      modifier = Modifier.fillMaxWidth()
    )

    Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
      Text(text = "Submeter")
    }
  }
}

@Preview(showBackground = true)
@Composable
fun NewPostScreenPreview() {
  CreateNewPostScreen()
}