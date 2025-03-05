package com.example.component.ui.ui_component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComposeText(name: String) {
//    Text(text = "Hello $name!")

    // 1. color 지정하기
//    Text(color = Color.Red, text = "Hello $name!")

    // 2. 해시 컬러값 지정하기
//    Text(color = Color(0xffff9944), text = "Hello $name!")

    // 3. fontSize 사용하기
//    Text(color = Color(0xffff9944), text = "Hello $name!", fontSize = 30.sp)

    // 4. fontWeight 사용하기 (xml의 Text에서 fontStyle 같은거 같다.)
//    Text(color = Color(0xffff9944), text = "Hello $name!", fontSize = 30.sp, fontWeight = FontWeight.Bold)

    // 5. fontFamily
//    Text(color = Color(0xffff9944), text = "Hello $name!", fontSize = 30.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)

    // 6. letterSpacing
//    Text(
//        color = Color(0xffff9944),
//        text = "Hello $name!",
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Monospace,
//        letterSpacing = 2.sp
//    )

    // 7. maxLines 지정해보기
//    Text(
//        color = Color(0xffff9944),
//        text = "Hello $name! My $name! Your $name, His $name Her $name",
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Monospace,
//        letterSpacing = 2.sp,
//        maxLines = 2
//    )

    // 8. TextDecoration에 UnderLine
//    Text(
//        color = Color(0xffff9944),
//        text = "Hello $name! My $name! Your $name, His $name Her $name",
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Monospace,
//        letterSpacing = 2.sp,
//        maxLines = 2,
//        textDecoration = TextDecoration.Underline
//    )

    // 9. textAlign을 TextAlign.Center로 지정 (xml에서 gravity 같은거), Modifier도 같이 써보기
//    Text(
//        modifier = Modifier.width(300.dp),
//        color = Color(0xffff9944),
//        text = "Hello $name! My $name! Your $name, His $name Her $name",
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Monospace,
//        letterSpacing = 2.sp,
//        maxLines = 2,
//        textDecoration = TextDecoration.Underline,
//        textAlign = TextAlign.Center
//    )

    // 10. Justify 이건 뭐지(띄워쓰기를 기준으로 양옆 끝으로 밀어냄)
    Text(
        modifier = Modifier.size(300.dp),
        color = Color(0xffff9944),
        text = "Hello $name! My $name! Your $name, His $name Her $name",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        letterSpacing = 2.sp,
        maxLines = 2,
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.Justify
    )
}

@Preview
@Composable
fun TextPreview() {
    ComposeText("YoungWoo")
}