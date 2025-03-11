package com.example.component.ui.component2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeStateHoisting() {

    // 원래는 ViewModel에서 들고있는게 맞음 (UI의 상태를 들고있는게 맞음)
    var pyeong by rememberSaveable {
        mutableStateOf("23")
    }

    var squareMeter by rememberSaveable {
        mutableStateOf((23 * 3.306).toString())
    }

    PyeongToSquareMeterStateless(
        pyeong = pyeong,
        squareMeter = squareMeter
    ) {
        if (it.isBlank()) {
            pyeong = ""
            squareMeter = ""
            return@PyeongToSquareMeterStateless
        }
        val value = it.toFloatOrNull() ?: return@PyeongToSquareMeterStateless
        pyeong = it
        squareMeter = (value * 3.306).toString()
    }
}

/**
 * State Hoisting
 * 상태 호이스팅이란 UI관리와 상태 관리는 분리 시키는 것
 * 지금껏 Recomposition을 공부하였을 땐 Compose를 생성한 함수 내에서 State를 관리했다.
 * 하지만 이는 좋은 구조가 아니며, Recomposition에 필요한 상태는 ViewModel에서 관리되도록 하는것이 좋으며
 * 이는 곧 StateHoisting 이라고 할 수 있다.
 * PyeongToSquareMeterStateless 내부에서는 상태변경을 전혀하고 있지 않다.
 * PyeongToSquareMeterStateless처럼 UI Compose 함수에서는 Stateless 하게 작성하는 것이 좋아보인다.
 *
 * @param pyeong
 * @param squareMeter
 * @param onPyeongChange
 */
@Composable
fun PyeongToSquareMeterStateless(
    pyeong: String,
    squareMeter: String,
    onPyeongChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = onPyeongChange,
            label = {
                Text("평")
            }
        )

        OutlinedTextField(
            value = squareMeter,
            onValueChange = onPyeongChange,
            label = {
                Text("제곱미터")
            }
        )
    }
}

@Preview
@Composable
fun ComposeStateHoistingPreview() {
    ComposeStateHoisting()
}