# MyCompose
## Compose UI 종류

### CustomDialog
    @Composable
    fun ComposeCustomDialog() {
        var isShowingDialog by remember { mutableStateOf(false) }
        var counter by remember { mutableIntStateOf(0) }
    
        Column {
            Button(
                onClick = {
                    isShowingDialog = true
                }
            ) {
                Text(text = "다이얼로그 열기")
            }
            Text(text = "카운터 : $counter")
        }
    
        if (isShowingDialog) {
            Dialog(
                onDismissRequest = {
                    isShowingDialog = false
                }
            ) {
                // 오.. AlertDialog를 사용한 것보다 못생겼다.
                // AlertDialog는 표준으로 만들어 놓은 틀이 있는 것 같다.
                // 일부러 못생기게 보이도록 놔둬야지
                // 나중에 왜 그런지 생각할 수 있도록 하기 위해 냅두자
                Surface {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "click 해보세요"
                        )
                        Row {
                            Button(
                                onClick = {
                                    isShowingDialog = false
                                }
                            ) {
                                Text("취소")
                            }
    
                            Button(
                                onClick = {
                                    counter++
                                }
                            ) {
                                Text("+1")
                            }
    
                            Button(
                                onClick = {
                                    counter--
                                }
                            ) {
                                Text("-1")
                            }
    
                        }
                    }
                }
            }
        }
    }