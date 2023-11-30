package com.miwis.tabnewskt.ui.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miwis.tabnewskt.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPostScreen() {
  Column(
    verticalArrangement = Arrangement.spacedBy(
      space = 16.dp,
      alignment = Alignment.CenterVertically
    ),
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
  ) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var bibliography by remember { mutableStateOf("") }

    Text(text = "Publicar novo conteúdo", style = Typography.titleMedium)

    OutlinedTextField(
      value = title,
      onValueChange = { title = it },
      modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
      value = body,
      onValueChange = { body = it },
      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
      modifier = Modifier.fillMaxWidth().height(120.dp)
    )

    OutlinedTextField(
      value = bibliography,
      onValueChange = { bibliography = it },
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
  NewPostScreen()
}