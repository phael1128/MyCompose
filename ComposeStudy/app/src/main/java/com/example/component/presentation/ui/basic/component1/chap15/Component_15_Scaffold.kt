package com.example.component.presentation.ui.basic.component1.chap15

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.component.presentation.ui.basic.component1.chap14.CheckboxWithSlot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeScaffold() {
    // Scaffold를 사용하는 이유
    // 앱의 주요 UI 요소(예: 상단 바, 하단 바, FAB 등)를 쉽게 배치할 수 있음.
    // 레이아웃이 자동으로 정리되므로, 직접 배치할 때보다 더 깔끔하고 유지보수하기 쉬움.

    val checked = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Scaffold")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Image(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "뒤로가기"
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                }
            ) {

            }
        }
    ) { paddingValue ->
        Row (
            modifier = Modifier.padding(top = paddingValue.calculateTopPadding())
        ) {
            CheckboxWithSlot(
                checked = checked.value,
                onCheckedChanged = {
                    checked.value = !checked.value
                }
            ) {
                Text(text = "컴포즈")
            }
        }
    }
}

@Preview
@Composable
fun ComposeScaffoldPreview() {
    ComposeScaffold()
}