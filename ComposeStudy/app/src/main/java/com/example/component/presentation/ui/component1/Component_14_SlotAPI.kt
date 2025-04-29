package com.example.component.ui.ui_component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ComposeSlot() {
    // SlotAPI란?
    // Composable 함수의 인자값으로 Composable 함수를 받는 것을 말한다.
    val checked1 = remember { mutableStateOf(false) }
    val checked2 = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.background(Color.White)
    ) {
        CheckboxWithSlot(
            checked = checked1.value,
            onCheckedChanged = {
                checked1.value = !checked1.value
            }
        ) {
            Text(
                text = "체크박스 1",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Log.d("phael", "${this@CheckboxWithSlot is RowScope}")
        }

        CheckboxWithSlot(
            checked = checked2.value,
            onCheckedChanged = {
                checked2.value = !checked2.value
            }
        ) {
            Text(
                "체크박스 2"
            )
        }
    }
}

@Composable
fun CheckboxWithSlot(
    checked: Boolean,
    onCheckedChanged: () -> Unit,
    content: @Composable RowScope.() -> Unit // 콜백 함수를 RowScope의 확장함수 형태로 쓸수가 있음
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onCheckedChanged.invoke()
        }
    ){
        Checkbox(
            checked = checked,
            onCheckedChange = { _ ->
                onCheckedChanged.invoke()
            }
        )
        content()
    }
}

@Preview
@Composable
fun ComposeSlotPreview() {
    ComposeSlot()
}