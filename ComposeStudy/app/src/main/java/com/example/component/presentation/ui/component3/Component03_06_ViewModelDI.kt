package com.example.component.presentation.ui.component3

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.component.presentation.ui.component3.viewmodel.ComposeViewModel

@Composable
fun ComposeViewModelComponent(
    viewModel: ComposeViewModel = hiltViewModel()
) {
    LazyColumn {
        item {
            Button(
                onClick = {
                    viewModel.getRepos()
                }
            ) {
                Text("get from Repository")
            }
        }
        items(viewModel.repos) {
            Text(it.name)
        }
    }
}

@Preview
@Composable
fun ComposeViewModelComponentPreview() {
    ComposeViewModelComponent()
}