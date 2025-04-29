package com.example.component.ui.ui_component

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeBoxWithConstraints() {
    BoxWithConstraints(
        modifier = Modifier
            .widthIn(min = 100.dp)
            .heightIn(min = 50.dp, max = 160.dp)
    ) {
        // BoxScope를 콜백 인자로 받아 참조할 수 있는 변수들은 아래와 같다.
        // "The constraints given by the parent layout in pixels." 이라 섪명되어 있고,
        // 해석하자면 "부모 레이아웃이 제공하는 픽셀 단위의 제약 조건."
        // BoxWithConstraints 가 가지고 있는 Modifier의 정보를 통해서
        // maxWidth, maxHeight, minWidth, minHeight 을 참조 가능하다.
        if (maxHeight > 150.dp) {
            Text(
                text = "좀 기네요",
                color = Color.Green
            )
        } else {
            Text(
                text = "짧네요.",
                color = Color.Green
            )
        }
//        Text(
//            text = "maxWidth:$maxWidth, maxHeight:$maxHeight, minWidth:$minWidth, minHeight:$minHeight",
//            color = Color.Green
//        )
    }
}

@Preview
@Composable
fun ComposeBoxWithConstraintsPreview() {
    ComposeBoxWithConstraints()
}