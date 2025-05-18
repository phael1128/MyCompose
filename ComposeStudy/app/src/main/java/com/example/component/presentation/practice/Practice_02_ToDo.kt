package com.example.component.presentation.practice

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.component.presentation.theme.MyComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopLevel() {
    val (text, setText) = remember { mutableStateOf("") }
    val toDoList = remember { mutableStateListOf<ToDoData>() }

    val onSubmit: (String) -> Unit = { text ->
        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
        toDoList.add(ToDoData(key, text))
        setText("")
    }

    Scaffold {
        Column {
            ToDoInput(
                text = text,
                onTextChange = setText,
                onSubmit = onSubmit
            )
            LazyColumn {
                items(
                    toDoList,
                    key = { it.key } // 키가 같으면 재사용할꺼라고 생각을 한다고?
                ) { item ->
                    ToDo(
                        toDoData = item,
                        onEdit = { key, text ->
                            val findIndex = toDoList.indexOfFirst { it.key == key }
                            if (findIndex != -1) {
                                toDoList[findIndex] = toDoList[findIndex].copy(text = text)
                            }
                        },
                        onToggle = { key, checked ->
                            val findIndex = toDoList.indexOfFirst { it.key == key }
                            if (findIndex != -1) {
                                // ToDoList의 한 Index에 있는 프로퍼티의 값을 변경하게 되면, Recomposition이 되지 읺는다.
                                // 물론 Model안에 State를 하나 만들어서 update 해주면 되긴 함
                                // mutableStateListOf 는 리스트가 들고있는 모델의 추가, 삭제, index 참조 주소 변경 일 때만 Recomposition이 일어남
                                toDoList[findIndex] = toDoList[findIndex].copy(done = checked)
                            }
                        },
                        onDelete = { key ->
                            val findIndex = toDoList.indexOfFirst { it.key == key }
                            if (findIndex != -1) {
                                toDoList.removeAt(findIndex)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ToDoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text("입력")
        }
    }
}

@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        // 애니메이션 효과를 통해 전환
        Crossfade(
            targetState = isEditing,
            label = ""
        ) { targetState ->
            when (targetState) {
                true -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        var (newText, setNewText) = remember { mutableStateOf(toDoData.text) }

                        OutlinedTextField(
                            value = newText,
                            onValueChange = setNewText,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(
                            onClick = {
                                onEdit.invoke(toDoData.key, newText)
                                isEditing = false
                            }
                        ) {
                            Text(text = "완료")
                        }
                    }
                }
                false -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = toDoData.text,
                            modifier = Modifier.weight(1f)
                        )

                        Text(text = "완료")

                        Checkbox(
                            checked = toDoData.done,
                            onCheckedChange = { isChecked ->
                                onToggle(toDoData.key, isChecked)
                            }
                        )

                        Button(
                            onClick = {
                                isEditing = true
                            }
                        ) {
                            Text("수정")
                        }

                        Spacer(Modifier.size(4.dp))

                        Button(
                            onClick = {
                                onDelete.invoke(toDoData.key)
                            }
                        ) {
                            Text("삭제")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    MyComposeTheme {
        ToDo(ToDoData(1, "nice", true))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeTheme {
        TopLevel()
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    MyComposeTheme {
        ToDoInput("테스트", {}, {})
    }
}

data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)