package com.example.component.presentation.ui.component2

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeAnimation2() {
    var isDarkMode by remember { mutableStateOf(false) }

    // Transition : 여러 Animation 프로퍼티들을 사용할 수 있음
    // 대부분 Transition의 확장함수로 존재하고있으며,
    // Transition의 targetState에 따라 애니메이션을 적용할 수 있다.
    // 따라서 Transition의 state 가 변경이 되면 이에 따라 Recomposition을 수행한다.
    val transition = updateTransition(
        targetState = isDarkMode,
        label = "다크 모드 트랜지션"
    )

    val backgroundColor by transition.animateColor(
        label = "다크 모드 배경색상 애니메이션"
    ) { state ->
        when (state) {
            true -> Color.Black
            false -> Color.White
        }
    }

    val color by transition.animateColor(
        label = "다크 모드 글자 색상 애니메이션"
    ) { state ->
        when (state) {
            true -> Color.White
            false -> Color.Black
        }
    }

    val alpha by transition.animateFloat(
        label = "다크 모드 알파 애니메이션"
    ) { state ->
        when (state) {
            false -> 0.7f
            true -> 1f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .alpha(alpha)
    ) {
        // 단계 6: 라디오 버튼에 글자 색을 적용합시다.
        RadioButtonWithText(
            text = "일반 모드",
            selected = !isDarkMode,
            color = color
        ) {
            isDarkMode = false
        }
        RadioButtonWithText(
            text = "다크 모드",
            selected = isDarkMode,
            color = color
        ) {
            isDarkMode = true
        }

        Crossfade(
            targetState = isDarkMode,
            label = ""
        ) { state ->
            when (state) {
                true -> {
                    Column {
                        Box(modifier = Modifier
                            .background(Color.Red)
                            .size(20.dp)) {
                            Text("A")
                        }
                        Box(modifier = Modifier
                            .background(Color.Magenta)
                            .size(20.dp)) {
                            Text("B")
                        }
                        Box(modifier = Modifier
                            .background(Color.Blue)
                            .size(20.dp)) {
                            Text("C")
                        }
                    }
                }
                false -> {
                    Row {
                        Box(modifier = Modifier
                            .background(Color.Red)
                            .size(20.dp)) {
                            Text("1")
                        }
                        Box(modifier = Modifier
                            .background(Color.Magenta)
                            .size(20.dp)) {
                            Text("2")
                        }
                        Box(modifier = Modifier
                            .background(Color.Blue)
                            .size(20.dp)) {
                            Text("3")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RadioButtonWithText(
    text: String,
    color: Color = Color.Black,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.selectable(
            selected = selected,
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(text = text, color = color)
    }
}

@Preview
@Composable
fun ComposeAnimation2Preview() {
    ComposeAnimation2()
}