# MyCompose
## Compose UI 종류
### Modifier
      // modifier = Modifier.fillMaxSize() : 화면 전체가 버튼으로 가득 참
      // modifier = Modifier.height(100.dp) : height 설정하기
      // modifier = Modifier.height(100.dp).width(200.dp) : Modifier에 height과 width 둘다 설정하기 (체이닝)
      // modifier = Modifier.size(200.dp, 100.dp) : Modifier에 height과 width를 같이 설정하기
      // modifier = Modifier.size(200.dp, 100.dp).background(Color.Red) : 변화가 없다. Button에서는 Background Color 변경 불가능, Button에 Background Color를 주고 싶다면 아래의 ButtonDefaults.buttonColors 으로 설정
      
      Button(
        onClick = {},
        modifier = Modifier
            .wrapContentSize()
            .padding(50.dp), // Modifier에 padding의 개념 -> 해당 UI의 측정된 사이즈로 부터 인자값 만큼 안쪽으로 밀어낸다. 때문에 사이즈가 측정이 되어있고, paddin값을 추가 하게 되면 뭉개져 버린다
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black, // 버튼의 Background
            contentColor = Color.Green // 버튼 내부 UI의 color
        )
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null
        )
        Spacer(
            modifier = Modifier.size(ButtonDefaults.IconSpacing)
        )
        Text(
            text = "Search",
            modifier = Modifier.clickable { // xml에서의 UI 최상위 클래스인 View의 onClick 같은 역할인걸까?, 단독으로 click event 사용 가능
                showToast(context = context)
            }. offset(x = 10.dp, y = 3.dp) // Modifier의 offset 속성 사용 : x축으로 10 이동, y축으로 3 이동
        )
    }