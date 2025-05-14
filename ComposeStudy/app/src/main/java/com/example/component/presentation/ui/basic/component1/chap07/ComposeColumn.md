# MyCompose
## Compose UI 종류
### Column
      /**
      * row -> Vertical 방향으로만 alignment 배치 가능
      * column -> Horizontal 방향으로만 alignment 배치 가능
      */

      // Column은 LinearLayout의 orientation이 Vertical 같은 역할
      // 내부 UI들을 세로 방향으로 배치
      Column (
        modifier = Modifier.size(100.dp).background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally, // horizontalAlignment 사용해보기
        verticalArrangement = Arrangement.Center // vertialArrangement
    ){
        Text(
            text = "첫번째",
            color = Color.Green,
            modifier = Modifier.align(Alignment.CenterHorizontally) 
        )
        Text(
            text = "두번째",
            color = Color.Blue,
            modifier = Modifier.align(Alignment.End) // Text에 align 사용
        )
        Text(
            text = "세번째",
            color = Color.Red,
            modifier = Modifier.align(Alignment.Start)
        )
    }
<img width="356" alt="스크린샷 2025-02-25 오후 9 35 04" src="https://github.com/user-attachments/assets/48725f08-101c-4475-9dab-fd265cff68bf" />