package com.example.component.presentation.ui.basic.component1.chap02

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeButton() {
    val context = LocalContext.current
    // 1. Button 생성하기
    // xml에서 Button을 사용하면 setText로 문자열을 바로 넣어줄 수 있었는데, Compose는 그런 방식은 아닌 것 같다.
    // Button 안에 별도의 Text를 넣어줌으로써 사용할 문자열을 넣어주는 방식 (물론 Text에 onClick을 할 수 있을 진 좀 더 찾아보자)
//    Button(
//        onClick = {
//            showToast(context)
//        }
//    ) {
//        Text("Send")
//    }

    // 2. icon을 넣어보기
//    Button(
//        onClick = {
//            showToast(context)
//        }
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Text("Send")
//    }

    // 3. 아이콘과 텍스트 사이에 Spacer 넣어보기
    // 마진 같은 역할을 해주는 것 같다.
//    Button(
//        onClick = {
//            showToast(context)
//        }
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
////        Spacer(modifier = Modifier.size(30.dp))
//        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
//        Text("Send")
//    }

    // 4. enabled 설정하기 (false 로 설정 시 아예 color가 변경되어 버림)
//    Button(
//        onClick = {
//            showToast(context)
//        },
//        enabled = false
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Spacer(modifier = Modifier.size(8.dp))
//        Text("Send")
//    }

    // 5. border 부분에 BorderStroke 넣어보기 (BorderSize 만큼 안쪽으로 사이즈가 잡히는 것 같다.)
//    Button(
//        onClick = {
//            showToast(context)
//        },
//        enabled = true,
//        border = BorderStroke(10.dp, Color.Red)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Spacer(modifier = Modifier.size(8.dp))
//        Text("Send")
//    }

    // 6. shape 변경해보기
//    Button(
//        onClick = {
//            showToast(context)
//        },
//        enabled = true,
//        border = BorderStroke(10.dp, Color.Red),
//        shape = RoundedCornerShape(8.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Spacer(modifier = Modifier.size(8.dp))
//        Text("Send")
//    }

    // 7. padding (상, 하, 좌, 우 별도로 세팅 가능, border로 부터 여백)
    Button(
        onClick = {
            showToast(context)
        },
        enabled = true,
        border = BorderStroke(10.dp, Color.Red),
        contentPadding = PaddingValues(20.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text("Send")
    }

}



@Preview
@Composable
fun ComposeButtonPreview() {
    ComposeButton()
}

fun showToast(context: Context) {
    Toast.makeText(
        context,
        "버튼 클릭!",
        Toast.LENGTH_SHORT
    ).show()
}