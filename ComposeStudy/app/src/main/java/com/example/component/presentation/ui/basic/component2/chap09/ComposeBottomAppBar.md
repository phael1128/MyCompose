# MyCompose
## Compose UI 종류

### BottomAppBar
    @Composable
    fun ComposeBottomAppBar() {
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()
        var counter by remember { mutableIntStateOf(0) }
    
        Scaffold(
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
            bottomBar = {
                BottomAppBar {
                    // RowScope 임
                    Text("헬로")
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "안녕하세요"
                                )
                            }
                        }
                    ) {
                        Text(text = "인사하기")
                    }
                    Button(
                        onClick = {
                            counter++
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "${counter}입니다."
                                )
                            }
                        }
                    ) {
                        Text(text = "더하기")
                    }
                    Button(
                        onClick = {
                            counter--
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "${counter}입니다."
                                )
                            }
                        }
                    ) {
                        Text(text = "빼기")
                    }
                }
            },
            content = {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "카운터는 ${counter}회입니다.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        )