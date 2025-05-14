# MyCompose
## Compose UI 종류

### 애니메이션 1 : visibility 애니메이션, color애니메이션 + 알파값 적용되는 애니메이션
    @Composable
    fun ComposeAnimation1() {
        var helloWorldVisible by remember { mutableStateOf(true) }
        var isRed by remember { mutableStateOf(false) }
    
        val backgroundColor by animateColorAsState(
            targetValue = if (isRed) Color.Red else Color.Yellow,
            label = ""
        )
        
        val alpha by animateFloatAsState(
            targetValue = if (isRed) 1.0f else 0.5f,
            label = ""
        )
    
        Column(
            modifier = Modifier.padding(16.dp)
                .background(backgroundColor)
                .alpha(alpha)
        ) {
    
            // Visibility의 isVisible를 애니메이션과 함께 설정할 수 있는 Compose
            AnimatedVisibility(
                visible = helloWorldVisible,
                // enter = scaleIn()
                // enter = expandHorizontally()
                enter = fadeIn() + expandHorizontally(), // 이런것도 됨
                exit = slideOutHorizontally()
            ) {
                Text(text = "Hello World!")
            }
    
            Row(
                Modifier.selectable(
                    selected = helloWorldVisible,
                    onClick = {
                        helloWorldVisible = true
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = helloWorldVisible,
                    onClick = { helloWorldVisible = true }
                )
                Text(
                    text = "Hello World 보이기"
                )
            }
    
            Row(
                Modifier.selectable(
                    selected = !helloWorldVisible,
                    onClick = {
                        helloWorldVisible = false
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = !helloWorldVisible,
                    onClick = { helloWorldVisible = false }
                )
                Text(
                    text = "Hello World 감추기"
                )
            }
    
            Text(text = "배경 색을 바꾸어봅시다.")
    
            Row(
                Modifier.selectable(
                    selected = !isRed,
                    onClick = {
                        isRed = false
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = !isRed,
                    onClick = { isRed = false }
                )
                Text(
                    text = "노란색"
                )
            }
    
            Row(
                Modifier.selectable(
                    selected = isRed,
                    onClick = {
                        isRed = true
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isRed,
                    onClick = { isRed = true }
                )
                Text(
                    text = "빨간색"
                )
            }
        }
    }