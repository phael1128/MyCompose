package com.example.component.presentation.ui.useful.component4.chap03.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.component.presentation.ui.useful.component4.chap02.HomeState
import com.example.component.presentation.ui.useful.model.Memo
import com.example.component.presentation.ui.useful.model.memos

@Composable
fun HomeScreen(homeState: HomeState) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val memoList = remember { memos }
        val onClickAction: (Int) -> Unit = {
            homeState.showContent(
                it
            )
        }

        Column {
            AddMemo(memoList)
            MemoList(onClickAction, memoList)
        }
    }
}

@Composable
fun AddMemo(
    memoList: SnapshotStateList<Memo> // list인데 내부에 값이 변경되면 자동으로 Recomposition 해주는 list
) {
    val inputValue = remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(all = 16.dp)
            .height(100.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextField(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            value = inputValue.value,
            onValueChange = { textFieldValue -> inputValue.value = textFieldValue }
        )
        Button(
            onClick = {
                memoList.add(
                    index = 0,
                    Memo(memoList.size, inputValue.value)
                )
                inputValue.value = ""
            },
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
        ) {
            Text("ADD")
        }
    }
}

@Composable
fun ColumnScope.MemoList(
    onClickAction: (Int) -> Unit,
    memoList: SnapshotStateList<Memo>
) {
    LazyColumn(
        modifier = Modifier
            .weight(1f)
    ) {
        items(
            count = memoList.size,
            key = { index ->
                // 키를 지정해줌으로써, 전체 Recomposition이 이루어지는 것이 아닌, 가장 맨 위의 Compose만 Recomposition이 된다.??
                memoList[index].id
            }
        ) { index ->
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .background(Color.White)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                    .fillMaxWidth()
                    .clickable {
                        onClickAction(
                            memoList[index].id
                        )
                    },
            ) {
                Text(
                    text = memoList[index].text,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}