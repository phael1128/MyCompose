package com.example.component.ui.ui_component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeTextField() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // TextField : xml의 EditText같은 역할
        // 이렇게 하면 키보드를 입력해도 입력이 되지 않는다
        // 왜? -> 상태를 변경을 안했으니까~
//        TextField(
//            value = "Tom",
//            onValueChange = {}
//        )
        val userInput = remember { mutableStateOf("") }
        TextField(
            value = userInput.value,
//            label = {
//                Text(text = "이름")
//                },
            onValueChange = { userInputValue ->
                userInput.value = userInputValue
            },
            placeholder = {
                Text("이름을 입력하세요") // xml의 EditText에서 hint 역할
            }
        )
        Spacer(
            modifier = Modifier.size(8.dp)
        )
        Text(
            text = "Hello ${userInput.value}!"
        )

//        이런것도 있음
//        OutlinedTextField(
//            value = userInput.value,
//            label = {
//                Text(text = "이름")
//            },
//            onValueChange = { userInputValue ->
//                userInput.value = userInputValue
//            }
//        )
    }
}

@Preview
@Composable
fun ComposeTextFieldPreview() {
    ComposeTextField()
}