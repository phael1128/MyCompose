package com.example.component.ui.component2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId

@Composable
fun ComposeConstraintSet() {
    // ConstraintSet
    // ConstrinatLayout 내 createRefs를 통해서 Compose 별 id를 설정하여 UI를 배치하였는데,
    // ConstriantSet을 통해서도 UI를 배치 할 수 있다. 짱 신기했다.

    val constraintSet = ConstraintSet {
        // ConstraintSet 내 UI를 배치할 id 생성하기
        // 인자값이 Compose UI에 적용될 id 값이다.
        val redBox = createRefFor("redBox")
        val yellowBox = createRefFor("yellowBox")
        val blueBox = createRefFor("blueBox")
        val grayBox = createRefFor("grayBox")

        constrain(redBox) {
            bottom.linkTo(parent.bottom, margin = 8.dp)
            end.linkTo(parent.end, margin = 4.dp)
        }
        constrain(yellowBox) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(blueBox) {
            centerTo(parent)
        }
        constrain(grayBox) {
            start.linkTo(yellowBox.end)
            top.linkTo(yellowBox.bottom)
        }
    }

    ConstraintLayout(
        constraintSet = constraintSet, // 앞서 설정한 ConstraintSet을 전달하기
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .layoutId("redBox") // ConstraintSet 내부에서 createRefFor를 통해 생성된 id 할당
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .layoutId("yellowBox")
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Blue)
                .layoutId("blueBox")
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Gray)
                .layoutId("grayBox")
        )
    }
}

@Preview
@Composable
fun ComposeConstraintSetPreview() {
    ComposeConstraintSet()
}