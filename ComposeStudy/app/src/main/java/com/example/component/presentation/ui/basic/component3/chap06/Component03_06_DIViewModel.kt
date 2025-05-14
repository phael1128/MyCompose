package com.example.component.presentation.ui.basic.component3.chap06

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.component.presentation.ui.basic.component3.viewmodel.ComposeViewModel

@Composable
fun ComposeDIViewModel(
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
    ComposeDIViewModel()
}