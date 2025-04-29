package com.example.component.ui.ui_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeBox() {
    // Box는 언제 주로 사용하나?
    // 1. UI Container 자체를 하나 만들고 싶을떄
    // 2. FrameLayout 처럼 UI Container 내에 UI를 중첩 시키기 위해서 주로 사용
//    Box(
//        modifier = Modifier.size(100.dp).background(Color.Red),
//    ) {
//        Text(
//            text = "Hello World",
//            modifier = Modifier.align(Alignment.BottomEnd)
//        )
//    }

    // 1. Box내 Text 두개 배치 해보기
    // (Modifier.align은 기본적으로 Box 안에서 사용가능, Box를 만들고 나면 람다에서 BoxScope를 주게 되는데, align 속성 자체가 BoxSceop의 함수이다.)
//    Box(
//        modifier = Modifier.size(100.dp).background(Color.Red),
//    ) {
//        Text(
//            text = "Hello World",
//            modifier = Modifier.align(Alignment.BottomEnd)
//        )
//
//        Text(
//            text = "Hello Compose",
//            modifier = Modifier.align(Alignment.CenterEnd)
//        )
//    }

    // 2. 두개의 Box를 Box안에 배치해보기
//    Box(
//        modifier = Modifier.size(100.dp).background(Color.Red),
//    ) {
//        Box(
//            modifier = Modifier.size(70.dp).background(Color.Green).align(Alignment.CenterStart)
//        )
//
//        Box(
//            modifier = Modifier.size(70.dp).background(Color.Blue).align(Alignment.BottomEnd)
//        )
//    }

    // 3. Box에 modifier 설정을 제거하고, content size 만큼 보여주게 해보기
    // 이렇게 하게 되면 맨 마지막의 Box의 size인 70dp 만큼을 부모 Box의 사이즈가 잡히게된다.
    // 그래서 파란색 box가 초록색 box을 가려버림
    // 하지만 fillMaxSize를 한다면? 첫번째 Box의 사이즈가 화면 전체의 사이즈로 측정이 되어서 겹쳐서 배치 시킬 수 있음
    // fillMaxSize란? 화면 전체를 차지한다는 뜻
    Box {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Green).align(Alignment.CenterStart)
        )

        Box(
            modifier = Modifier.size(70.dp).background(Color.Blue).align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun ComposeBoxPreview() {
    ComposeBox()
}