package com.example.component.ui_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeRow() {
    // xml의 LinearLayout에서 orientation이 Horizontal 같은 역할
//    Row {
//        Text(text = "첫번째", color = Color.Green)
//        Text(text = "두번째", color = Color.Gray)
//        Text(text = "세번째", color = Color.Blue)
//    }

    // 1. 각 Text의 modifier에 align 설정해보기 (세로로 정렬 가능, row가 가로니까)
//    Row(
//        modifier = Modifier.height(40.dp)
//    ) {
//        Text(text = "첫번째", color = Color.Green, modifier = Modifier.align(Alignment.Top))
//        Text(text = "두번째", color = Color.Gray, modifier = Modifier.align(Alignment.CenterVertically))
//        Text(text = "세번째", color = Color.Blue, modifier = Modifier.align(Alignment.Bottom))
//    }

    // 2. Row에 verticalAlignment 설정
    // 하나만 Top으로 정렬해보기
//    Row(
//        modifier = Modifier.height(40.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(text = "첫번째", color = Color.Green, modifier = Modifier.align(Alignment.Top))
//        Text(text = "두번째", color = Color.Gray)
//        Text(text = "세번째", color = Color.Blue)
//    }
    
    // 3. horizontalArrangement 사용 (수평방향으로 정렬) (Center, Top, End, SpaceAround, SpaceBetween, SpaceEvenly 등등)
//    Row(
//        modifier = Modifier.height(40.dp).width(200.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceEvenly
//    ) {
//        Text(text = "첫번째", color = Color.Green, modifier = Modifier.align(Alignment.Top))
//        Text(text = "두번째", color = Color.Gray)
//        Text(text = "세번째", color = Color.Blue)
//    }

    // 4. weight : 해당 Compose가 부모 ComposeContainer에서 비율을 얼마나 차지 할 것인지 설정
    // xml의 weight와 비슷
//    Row(
//        modifier = Modifier.height(40.dp).width(200.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceEvenly
//    ) {
//        Text(
//            text = "첫번째",
//            color = Color.Green,
//            modifier = Modifier.align(Alignment.Top).weight(3f),
//        )
//        Text(
//            text = "두번째",
//            color = Color.Gray,
//            modifier = Modifier.weight(2f)
//        )
//        Text(
//            text = "세번째",
//            color = Color.Blue,
//            modifier = Modifier.weight(3f)
//        )
//    }

    // 4. Icon도 넣어보기
    Row(
        modifier = Modifier.height(40.dp).width(200.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "첫번째",
            color = Color.Green,
            modifier = Modifier.align(Alignment.Top).weight(3f).background(Color.Red),
        )
        Icon(
            imageVector = Icons.Filled.Call,
            contentDescription = "전화",
            modifier = Modifier.weight(13f).background(Color.DarkGray)
        )
        Text(
            text = "세번째",
            color = Color.Blue,
            modifier = Modifier.weight(3f).background(Color.Black)
        )
    }
}

@Preview
@Composable
fun ComposeRowPreview() {
    ComposeRow()
}