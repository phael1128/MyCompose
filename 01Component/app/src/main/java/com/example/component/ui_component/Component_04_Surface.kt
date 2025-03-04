package com.example.component.ui_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeSurface() {
    // Android Jetpack Compose의 Surface는 Compose UI의 Container로써 배경색, 그림자, 모양 등등을 설정하기 위해 사용
    // backgroundColor, Shape, evelvation(그림자 설정), 터치 피드백 등등 사용가능

    // 기존 Text에 margin을 넣고 싶다면 setPadding으로 해결이 가능했다.
    // 하지만 Compose는 margin이라는 개념은 별도로 존재하지 않고, padding 개념만 존재한다.
    // 그럼 만약 Compose UI를 활용하면서 margin을 사용하고 싶다면?
    // 아래 Surface에 padding을 넣는 형식으로 사용가능하다.
//    Surface(
//        color = MaterialTheme.colorScheme.surface,
//        modifier = Modifier.padding(5.dp)
//    ) {
//        Text(
//            text = "Hello World",
//            modifier = Modifier.padding(8.dp)
//        )
//    }

    // 1. elevation 써보기 ()
//    Surface(
//        modifier = Modifier.padding(5.dp),
//        shadowElevation = 50.dp
//    ) {
//        Text(
//            text = "Hello World",
//            modifier = Modifier.padding(8.dp)
//        )
//    }
    
    // 2. Border 쓰기
//    Surface(
//        modifier = Modifier.padding(5.dp),
//        shadowElevation = 5.dp,
//        border = BorderStroke(2.dp, Color.Red)
//    ) {
//        Text(
//            text = "Hello World",
//        )
//    }

    // 3. Shape 설정
//    Surface(
//        modifier = Modifier.padding(5.dp),
//        shadowElevation = 5.dp,
//        border = BorderStroke(2.dp, Color.Red),
//        shape = CircleShape
//    ) {
//        Text(
//            text = "Hello World",
//        )
//    }

    // 4. color 지정
    Surface(
        modifier = Modifier.padding(5.dp),
        shadowElevation = 5.dp,
        border = BorderStroke(2.dp, Color.Gray),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.error
    ) {
        Text(
            text = "Hello World",
        )
    }

}

@Preview
@Composable
fun ComposeSurfacePreview() {
    ComposeSurface()
}