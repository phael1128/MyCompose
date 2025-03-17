package com.example.component.ui.component2

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ComposeSnackBar() {
    var counter by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    // rememberScaffoldState() : 머테리얼3 이전까지는 Scaffold에서 스낵바를 띄우기 위해 사용했던것으로 파악된다.
    // 하지만 이제는 사용하지 않고, 아래를 사용하면 되는 것 같다
    // SnackBarHostState : 스낵바를 호출 및 컨트롤하는 상태 객체
    // SnackBarHost : 스낵바를 띄우는 Composable
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        Button(
            onClick = {
                counter++
                coroutineScope.launch {
                    // showSnackbar는 왜 suspend function인가?
                    // -> snackbar가 노출되고 난 후 일정 시간이 지나면 dismiss 시켜야하기 때문
                    val result = snackbarHostState.showSnackbar(
                        message = "count : $counter",
                        actionLabel = "닫기",
                        duration = SnackbarDuration.Short
                    )

                    when (result) {
                        SnackbarResult.Dismissed -> {
                            // Snackbar that is shown has been dismissed either by timeout of by user
                        }
                        SnackbarResult.ActionPerformed -> {
                            // Action on the Snackbar has been clicked before the time out passed
                        }

                    }
                }
            }
        ) {
            Text("더하기")
        }
    }
}

@Preview
@Composable
fun ComposeSnackBarPreview() {
    ComposeSnackBar()
}