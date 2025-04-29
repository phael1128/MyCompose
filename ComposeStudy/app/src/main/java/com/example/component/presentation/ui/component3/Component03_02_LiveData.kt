package com.example.component.presentation.ui.component3

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.component.presentation.ui.practice.ToDo
import com.example.component.presentation.ui.practice.ToDoData
import com.example.component.presentation.ui.practice.ToDoInput

class LiveDataViewModel: ViewModel() {
    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    val setText: (String) -> Unit = {
        _text.value = it
    }

//    val todoList = mutableStateListOf<ToDoData>()
    private val _rawTodoList = mutableListOf<ToDoData>()
    private val _todoList = MutableLiveData<List<ToDoData>>(_rawTodoList)
    val todoList: LiveData<List<ToDoData>> = _todoList

    // mutableStateListOf - 추가, 삭제, 대입 할 때 Recomposition이 된다. 각 항목의 필드가(인덱스별 model의 프로퍼티) 바뀌었을 때는 Recomposition이 되지 않는다.
    // LiveData<List<T>>.observeAsState() -> List가 통채로 다른 List로 바뀌었을 때만 Recomposition이 된다.
    // 상당히 비효율적이나, 일단 한번은 써보기

    val onSubmit: (String) -> Unit = {
        val key = (_rawTodoList.lastOrNull()?.key ?: 0) + 1
        _rawTodoList.add(ToDoData(key, it))
        _todoList.value = mutableListOf<ToDoData>().also { list ->
            list.addAll(_rawTodoList)
        }
        _text.value = ""
    }

    val onEdit: (Int, String) -> Unit = { key, newText ->
        val i = _rawTodoList.indexOfFirst { it.key == key }
        _rawTodoList[i] = _rawTodoList[i].copy(text = newText)
        _todoList.value = _rawTodoList.toMutableList()
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val i = _rawTodoList.indexOfFirst { it.key == key }
        _rawTodoList[i] = _rawTodoList[i].copy(done = checked)
        _todoList.value = mutableListOf<ToDoData>().also {
            it.addAll(_rawTodoList)
        }
    }

    val onDelete: (Int) -> Unit = { key ->
        val i = _rawTodoList.indexOfFirst { it.key == key }
        _rawTodoList.removeAt(i)
        _todoList.value = mutableListOf<ToDoData>().also {
            it.addAll(_rawTodoList)
        }
    }
}

@Composable
fun ComposeLiveData() {
    LiveDataTopLevel()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LiveDataTopLevel(
    viewModel: LiveDataViewModel = viewModel()
) {
    Scaffold {
        Column {
            ToDoInput(
                text = viewModel.text.observeAsState("").value,
                onTextChange = viewModel.setText,
                onSubmit = viewModel.onSubmit
            )
            val items = viewModel.todoList.observeAsState(emptyList<ToDoData>()).value
            LazyColumn {
                items(
                    items = items,
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

@Preview
@Composable
fun ComposeLiveDataTest() {
    ComposeLiveData()
}