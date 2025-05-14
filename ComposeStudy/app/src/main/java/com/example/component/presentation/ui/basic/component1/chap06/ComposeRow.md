# MyCompose
## Compose UI 종류
### Row
      // Row
      // xml의 LinearLayout에서 orientation이 Horizontal 같은 역할
      Row(
        modifier = Modifier
                    .height(40.dp)
                    .width(200.dp),
        verticalAlignment = Alignment.CenterVertically, // 수직 방향으로 UI 정렬
        horizontalArrangement = Arrangement.SpaceEvenly // 수평방향으로 UI 배치 (Center, Top, End, SpaceAround, SpaceBetween, SpaceEvenly 등등)
    ) {
        Text(
            text = "첫번째",
            color = Color.Green,
            modifier = Modifier
                        .align(Alignment.Top) // align 설정 가능 (방향으로 설정 가능, Row가 가로니까)
                        .weight(3f) // 해당 Compose가 부모 ComposeContainer에서 비율을 얼마나 차지 할 것인지 설정, xml의 weight이랑 비슷한 개념
                        .background(Color.Red),
        )
        Icon(
            imageVector = Icons.Filled.Call,
            contentDescription = "전화",
            modifier = Modifier
                        .weight(13f)
                        .background(Color.DarkGray)
        )
        Text(
            text = "세번째",
            color = Color.Blue,
            modifier = Modifier
                        .weight(3f)
                        .background(Color.Black)
        )
    }
