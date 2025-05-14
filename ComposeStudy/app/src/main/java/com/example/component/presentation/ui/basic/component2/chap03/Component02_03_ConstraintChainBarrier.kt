package com.example.component.presentation.ui.basic.component2.chap03

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ComposeConstraintChainBarrier() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        val (redBox, yellowBox, blueBox, text) = createRefs()
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    top.linkTo(parent.top, margin = 4.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    top.linkTo(parent.top, margin = 32.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Blue)
                .constrainAs(blueBox) {
                    top.linkTo(parent.top, margin = 16.dp)
                }
        )

        // createVerticalChain(redBox, yellowBox, blueBox) // 세로로 정렬
        // createHorizontalChain(redBox, yellowBox, blueBox) // 가로로 정렬
        createHorizontalChain(redBox, yellowBox, blueBox, chainStyle = ChainStyle.SpreadInside) // 기존 xml에서 사용 하던 것들을 거의 그대로 구현이 가능한 듯 (Spread, Packed 등등)

        val barrier = createBottomBarrier(redBox, yellowBox, blueBox) // Barrier 개념 또한 사용 가능하다.
        // createTopBarrier or createBottomBarrier 는 Horizontal 선상의 앵커 개념이라 top or bottom 으로 link 가능
        // createStartBarrier or createEndBarrier 는 Vertical 선상의 앵커 개념이라 start or end 로 link 가능
        Text(
            text = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(barrier)
            }
        )
    }
}

@Preview
@Composable
fun ComposeConstraintChainBarrierPreview() {
    ComposeConstraintChainBarrier()
}