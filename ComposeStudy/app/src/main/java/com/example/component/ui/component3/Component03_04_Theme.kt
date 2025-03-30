package com.example.component.ui.component3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.component.ui.theme.ComponentTheme

@Composable
fun ComposeTheme() {
    ComponentTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            Column {
                Text("안녕하세요. 패스트캠퍼스")
                Text("스안녕하세요. 패스트캠퍼")
                Text("퍼스안녕하세요. 패스트캠")
                Text("캠퍼스안녕하세요. 패스트")
                Text("트캠퍼스안녕하세요. 패스")
                Text("스트캠퍼스안녕하세요. 패")
                Text("패스트캠퍼스안녕하세요.")
                Button(onClick = {}) {
                    Text("버튼")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeThemePreview() {
    ComposeTheme()
}