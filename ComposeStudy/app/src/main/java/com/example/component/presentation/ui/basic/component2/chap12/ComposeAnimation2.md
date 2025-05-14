# MyCompose
## Compose UI 종류

### 애니메이션 2 : Transition
    @Composable
    fun ComposeAnimation2() {
        var isDarkMode by remember { mutableStateOf(false) }
    
        // Transition : 여러 Animation 프로퍼티들을 사용할 수 있음
        // 대부분 Transition의 확장함수로 존재하고있으며,
        // Transition의 targetState에 따라 애니메이션을 적용할 수 있다.
        // 따라서 Transition의 state 가 변경이 되면 이에 따라 Recomposition을 수행한다.
        val transition = updateTransition(
            targetState = isDarkMode,
            label = "다크 모드 트랜지션"
        )
    
        val backgroundColor by transition.animateColor(
            label = "다크 모드 배경색상 애니메이션"
        ) { state ->
            when (state) {
                true -> Color.Black
                false -> Color.White
            }
        }
    
        val color by transition.animateColor(
            label = "다크 모드 글자 색상 애니메이션"
        ) { state ->
            when (state) {
                true -> Color.White
                false -> Color.Black
            }
        }
    
        val alpha by transition.animateFloat(
            label = "다크 모드 알파 애니메이션"
        ) { state ->
            when (state) {
                false -> 0.7f
                true -> 1f
            }
        }
    
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor)
                .alpha(alpha)
        ) {
            // 단계 6: 라디오 버튼에 글자 색을 적용합시다.
            RadioButtonWithText(
                text = "일반 모드",
                selected = !isDarkMode,
                color = color
            ) {
                isDarkMode = false
            }
            RadioButtonWithText(
                text = "다크 모드",
                selected = isDarkMode,
                color = color
            ) {
                isDarkMode = true
            }
    
            Crossfade(
                targetState = isDarkMode,
                label = ""
            ) { state ->
                when (state) {
                    true -> {
                        Column {
                            Box(modifier = Modifier
                                .background(Color.Red)
                                .size(20.dp)) {
                                Text("A")
                            }
                            Box(modifier = Modifier
                                .background(Color.Magenta)
                                .size(20.dp)) {
                                Text("B")
                            }
                            Box(modifier = Modifier
                                .background(Color.Blue)
                                .size(20.dp)) {
                                Text("C")
                            }
                        }
                    }
                    false -> {
                        Row {
                            Box(modifier = Modifier
                                .background(Color.Red)
                                .size(20.dp)) {
                                Text("1")
                            }
                            Box(modifier = Modifier
                                .background(Color.Magenta)
                                .size(20.dp)) {
                                Text("2")
                            }
                            Box(modifier = Modifier
                                .background(Color.Blue)
                                .size(20.dp)) {
                                Text("3")
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Composable
    fun RadioButtonWithText(
        text: String,
        color: Color = Color.Black,
        selected: Boolean,
        onClick: () -> Unit
    ) {
        Row(
            modifier = Modifier.selectable(
                selected = selected,
                onClick = onClick
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = selected, onClick = onClick)
            Text(text = text, color = color)
        }
    }
