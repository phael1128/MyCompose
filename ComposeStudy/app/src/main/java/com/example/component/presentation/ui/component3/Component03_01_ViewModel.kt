package com.example.component.presentation.ui.component3

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.component.presentation.practice.ToDo
import com.example.component.presentation.practice.ToDoData
import com.example.component.presentation.practice.ToDoInput

class ToDoViewModel: ViewModel() {

    // remember를 하지 않은 이유
    // 기존 remember는 Compose의 생명주기에 따라간거고
    // ViewModel에서는 Compose 가 아닌 ViewModel의 생명주기에 따라가는 것이 맞다.
    val text = mutableStateOf("")

    val todoList = mutableStateListOf<ToDoData>()

    val onSubmit: (String) -> Unit = {
        val key = (todoList.lastOrNull()?.key ?: 0) + 1
        todoList.add(ToDoData(key, it))
        text.value = ""
    }

    val onEdit: (Int, String) -> Unit = { key, newText ->
        val i = todoList.indexOfFirst { it.key == key }
        todoList[i] = todoList[i].copy(text = newText)
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val i = todoList.indexOfFirst { it.key == key }
        todoList[i] = todoList[i].copy(done = checked)
    }

    val onDelete: (Int) -> Unit = { key ->
        val i = todoList.indexOfFirst { it.key == key }
        todoList.removeAt(i)
    }
}
@Composable
fun ComposeViewModel() {
    TopLevel()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopLevel(
    viewModel: ToDoViewModel = viewModel()
) {
        Scaffold {
            Column {
                ToDoInput(
                    text = viewModel.text.value,
                    onTextChange = {
                        viewModel.text.value = it
                    },
                    onSubmit = viewModel.onSubmit
                )
                LazyColumn {
                    items(
                        items = viewModel.todoList,
                        key = { it.key }
                    ) { toDoData ->
                        ToDo(
                            toDoData = toDoData,
                            onEdit = viewModel.onEdit,
                            onToggle = viewModel.onToggle,
                            onDelete = viewModel.onDelete
                        )
                    }
                }
            }
        }
}

@Composable
@Preview
fun ComposeViewModelTest() {
    ComposeViewModel()
}