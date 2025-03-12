package com.example.component.ui.component2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ComposeDropDownMenu() {
    var expandDropDownMenu by remember { mutableStateOf(false) }
    var counter by remember { mutableIntStateOf(0) }

    Column {
        Button(
            onClick = {
                expandDropDownMenu = true
            }
        ) {
            Text("드롭다운 메뉴 열기")
        }
        Text("카운터: $counter")
    }
    DropdownMenu(
        expanded = expandDropDownMenu,
        onDismissRequest = {
            expandDropDownMenu = false
        }
    ) {
        DropdownMenuItem(
            onClick = {
                counter++
            },
            text = {
                Text("증가")
            }
        )

        DropdownMenuItem(
            onClick = {
                counter--
            },
            text = {
                Text("감소")
            }
        )
    }
}

@Preview
@Composable
fun ComposeDropDownMenuPreview() {
    ComposeDropDownMenu()
}