# MyCompose
## Compose UI 종류

### Canvas
    @Composable
    fun ComposeCanvas() {
        // 근본
        // 예전에 Canvas 공부한 거랑 많이 다르지는 않아 보인다.
        Canvas(
            modifier = Modifier.size(20.dp).background(Color.White)
        ) {
            // 줄
            drawLine(
                color = Color.Red,
                start = Offset(x = 30f, y = 10f),
                end = Offset(x = 50f, y = 40f)
            )
    
            // 원
            drawCircle(
                color = Color.Yellow,
                radius = 10f,
                center = Offset(15f, 40f)
            )
    
            // 사각형
            drawRect(
                color = Color.Blue,
                topLeft = Offset(x = 30f, y = 30f),
                size = Size(10f, 10f)
            )
    
            drawLine(
                color = Color.Gray,
                start = Offset(x = 2.01f , y = 21.0f),
                end = Offset(x = 23.0f, y = 12.0f)
            )
            drawLine(
                color = Color.Gray,
                start = Offset(x = 23.0f, y = 12.0f),
                end = Offset(x = 2.01f, y = 3.0f)
            )
    
            drawLine(
                color = Color.Gray,
                start = Offset(x = 2.01f, y = 3.0f),
                end = Offset(x = 2.0f, y = 10.0f)
            )
    
            drawLine(
                color = Color.Gray,
                start = Offset(x = 2.0f, y = 10.0f),
                end = Offset(x = 17.0f, y = 12.0f)
            )
    
            drawLine(
                color = Color.Gray,
                start = Offset(x = 17.0f, y = 12.0f),
                end = Offset(x = 2.0f, y = 14.0f)
            )
    
            drawLine(
                color = Color.Gray,
                start = Offset(x = 2.0f, y = 14.0f),
                end = Offset(x = 2.01f , y = 21.0f)
            )
    
        }
    }