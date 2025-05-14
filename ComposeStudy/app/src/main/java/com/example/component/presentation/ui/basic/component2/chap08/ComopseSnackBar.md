# MyCompose
## Compose UI 종류

### SnackBar
    @Composable
    fun ComposeSnackBar() {
        var counter by remember { mutableIntStateOf(0) }
        val coroutineScope = rememberCoroutineScope()
    
        // rememberScaffoldState() : 머테리얼3 이전까지는 Scaffold에서 스낵바를 띄우기 위해 사용했던것으로 파악된다.
        // 하지만 이제는 사용하지 않고, 아래를 사용하면 되는 것 같다
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) {
            Button(
                onClick = {
                    counter++
                    coroutineScope.launch {
                        // showSnackbar는 왜 suspend function인가?
                        // -> snackbar가 노출되고 난 후 일정 시간이 지나면 dismiss 시켜야하기 때문
                        val result = snackbarHostState.showSnackbar(
                            message = "count : $counter",
                            actionLabel = "닫기",
                            duration = SnackbarDuration.Short
                        )
    
                        when (result) {
                            SnackbarResult.Dismissed -> {
                                // Snackbar that is shown has been dismissed either by timeout of by user
                            }
                            SnackbarResult.ActionPerformed -> {
                                // Action on the Snackbar has been clicked before the time out passed
                            }
    
                        }
                    }
                }
            ) {
                Text("더하기")
            }
        }
    }