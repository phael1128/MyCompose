package com.example.component.ui_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * row -> Vertical 방향으로만 alignment 배치 가능
 * column -> Horizontal 방향으로만 alignment 배치 가능
 */
@Composable
fun ComposeColumn() {
    // 1. Column은 LinearLayout의 orientation이 Vertical 같은 역할
    // 내부 UI들을 세로 방향으로 배치
    
    // 1. horizontalAlignment 사용해보기
//    Column (
//        modifier = Modifier.size(100.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        Text(
//            text = "첫번째",
//            color = Color.Green,
//        )
//        Text(
//            text = "두번째",
//            color = Color.Blue
//        )
//        Text(
//            text = "세번째",
//            color = Color.Red
//        )
//    }

    // 2. vertialArrangement
//    Column (
//        modifier = Modifier.size(100.dp).background(color = Color.White),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ){
//        Text(
//            text = "첫번째",
//            color = Color.Green
//        )
//        Text(
//            text = "두번째",
//            color = Color.Blue
//        )
//        Text(
//            text = "세번째",
//            color = Color.Red
//        )
//    }

    // 3. Text에 align 사용
    Column (
        modifier = Modifier.size(100.dp).background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "첫번째",
            color = Color.Green,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "두번째",
            color = Color.Blue,
            modifier = Modifier.align(Alignment.End)
        )
        Text(
            text = "세번째",
            color = Color.Red,
            modifier = Modifier.align(Alignment.Start)
        )
    }
}

@Preview
@Composable
fun ComposeColumnPreview() {
    ComposeColumn()
}