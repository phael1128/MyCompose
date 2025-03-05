package com.example.component.ui.ui_component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeModifier() {
    val context = LocalContext.current

    // 1. Modifier.fillMaxSize() : 화면 전체가 버튼으로 가득 참
//    Button(
//        onClick = {},
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 2. Modifier.height 설정하기 : 인자로 받는 값 만큼 사이즈가 측정
//    Button(
//        onClick = {},
//        modifier = Modifier.height(100.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 3. Modifier에 height과 width 둘다 설정하기 (체이닝)
//    Button(
//        onClick = {},
//        modifier = Modifier.height(100.dp).width(200.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 4. Modifier에 height과 width를 같이 설정하기
//    Button(
//        onClick = {},
//        modifier = Modifier.size(200.dp, 100.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 5. background 설정
//    Button(
//        onClick = {},
//        modifier = Modifier.size(200.dp, 100.dp).background(Color.Red) // 변화가 없다. Button에서는 Background Color 변경 불가능
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 6. background 설정
//    Button(
//        onClick = {},
//        modifier = Modifier.size(200.dp, 100.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Black, // 버튼의 Background
//            contentColor = Color.Green // 버튼 내부 UI의 color
//        ),
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 7. padding 써보기 : Modifier에 padding의 개념 -> 해당 UI의 측정된 사이즈로 부터 인자값 만큼 안쪽으로 밀어낸다.
    // 때문에 사이즈가 측정이 되어있고, paddin값을 추가 하게 되면 뭉개져 버린다
//    Button(
//        onClick = {},
//        modifier = Modifier
//            .size(200.dp)
//            .padding(50.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Black, // 버튼의 Background
//            contentColor = Color.Green // 버튼 내부 UI의 color
//        ),
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 8. Modifier의 click event 설정하기 : button의 click event는 받지 않고, 내부 UI의 click event를 받는 방법
//    Button(
//        onClick = {},
//        modifier = Modifier
//            .wrapContentSize(),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Black, // 버튼의 Background
//            contentColor = Color.Green // 버튼 내부 UI의 color
//        ),
//        enabled = false
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text(
//            text = "Search",
//            modifier = Modifier.clickable {
//                showToast(context = context)
//            }
//        )
//    }

    // 9. Text의 modifier에 offset 설정
    Button(
        onClick = {},
        modifier = Modifier
            .wrapContentSize(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black, // 버튼의 Background
            contentColor = Color.Green // 버튼 내부 UI의 color
        )
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null
        )
        Spacer(
            modifier = Modifier.size(ButtonDefaults.IconSpacing)
        )
        Text(
            text = "Search",
            modifier = Modifier.offset(x = 10.dp, y = 3.dp) // x축으로 10 이동, y축으로 3 이동
        )
    }
}

@Preview
@Composable
fun ComposeModifierPreview() {
    ComposeModifier()
}