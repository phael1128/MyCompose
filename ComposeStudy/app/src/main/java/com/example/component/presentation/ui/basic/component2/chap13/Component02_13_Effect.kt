package com.example.component.presentation.ui.basic.component2.chap13

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * 부수효과? - SideEffect
 *
 * LaunchedEffect : Compose 함수에서 Suspend 함수 실행
 * rememberCoroutineScope : Compose 함수 외부에서 Coroutine 실행
 * rememberUpdatedState : 값이 변경되는 경우 다시 시작되지 않아야하는 효과
 * DisposableEffect : 짝이 필요한 효과 (열기 - 닫기) (늘리기 - 줄이기)
 * SideEffect : Compose 상태를 비 Compose로 변경
 * produceState : 비 Compose를 Compose 상태로 변경
 * derivedStateOf : 하나 이상의 상태 객체를 다른 상태로 변환
 * snapshowFlow : Compose의 상태를 flow로 변환
 */
@Composable
fun ComposeEffect(lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackBarHostState) {
        snackBarHostState.showSnackbar(
            message = "Hello Compose",
            actionLabel = "취소",
            duration = SnackbarDuration.Short
        )
    }

    SnackbarHost(snackBarHostState)

    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver { source: LifecycleOwner, event: Lifecycle.Event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> Log.d("phael", "OnCreate")
                Lifecycle.Event.ON_START -> Log.d("phael", "OnStart")
                Lifecycle.Event.ON_RESUME -> Log.d("phael", "OnResume")
                Lifecycle.Event.ON_PAUSE -> Log.d("phael", "OnPause")
                Lifecycle.Event.ON_STOP -> Log.d("phael", "OnStop")
                Lifecycle.Event.ON_DESTROY -> Log.d("phael", "OnDestroy")
                else -> Log.d("phael", "그 외")
            }
        }

        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            // onDispose
            // Composable이 화면에서 사라질 때 (화면에서 사라질 때) 실행
            // 인자로 받는 값이 변경이 될 때, onDispose 가 호출이 되고 DisposableEffect 자체가 리컴포지션이 일어남 -> 이게 LanchedEffect와 가장 큰 차이
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Preview
@Composable
fun ComposeEffectPreview() {
    ComposeEffect()
}