# MyCompose
## Compose UI 종류

### Dialog
    @Composable
    fun ComposeDialog() {
        // 팝업 show 하는 것 또한 Recomposition이 필요하다.
        // 그럼 팝업을 종료할때는? -> 역시 Recomposition이 필요하다.
        // 공부하면 할수록 느끼는거지만, UI에 변화가(상태의 변화가?) 또는 변경이 필요할 땐
        // Recomposition 이 필요한 것 같다.
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
            AlertDialog(
                onDismissRequest = {
                    // Dialog가 dismiss 하기 전 (팝업 외부 영역 클릭 했을 때 혹은 백버튼 클릭 일 떄나)
                    isShowingDialog = false
                },
                confirmButton = {
                    Button(
                        onClick = {
                            isShowingDialog = false
                            counter++
                        }
                    ) {
                        Text(text = "카운터 증가")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            isShowingDialog = false
                        }
                    ) {
                        Text(text = "취소")
                    }
                },
                title = {
                    Text(text = "더하기")
                },
                text = {
                    Text(text = "카운터가 증가됩니다.\n버튼 클릭해보세요.")
                }
            )
        }
    
    }