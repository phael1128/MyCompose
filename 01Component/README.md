# MyCompose
## Compose UI 종류
### Text
      Text(
        modifier = Modifier.size(300.dp),
        color = Color(0xffff9944), //(color 지정하기, 해시 컬러값 지정하기)
        text = "Hello $name! My $name! Your $name, His $name Her $name",
        fontSize = 30.sp, //(fontSize 사용하기)
        fontWeight = FontWeight.Bold, //(fontWeight 사용하기 (xml의 Text에서 fontStyle 같은거 같다.))
        fontFamily = FontFamily.Monospace,
        letterSpacing = 2.sp, //(자간)
        maxLines = 2, //(xml 처럼 문자열 최대 라인 지정 가능 (최대 2줄까지))
        textDecoration = TextDecoration.Underline, //(밑줄 사용해보기)
        textAlign = TextAlign.Center (textAlign을 TextAlign.Center로 지정 //(xml에서 gravity 같은거),)
    )

### Button
      // 버튼은 xml의 Button 과는 차이가 있는 것 같다.
      // xml에서는 Button이 TextView를 상속받아서 text를 다이렉트로 변경이 가능했는데,
      // Compose Button은 Text UI를 별도로 포함해서 사용한다.
      // button의 내부 orientation 방향은 Row(가로)를 사용하는 것으로 확인
      Button(
        onClick = { // Button click Event
            showToast(context)
        },
        enabled = true, // Button 클릭 가능 유무, false 설정 시 Background Color가 아예 바뀌어버림
        border = BorderStroke(10.dp, Color.Red), // border 부분에 BorderStroke 넣어보기 (BorderSize 만큼 안쪽으로 사이즈가 잡히는 것 같다.)
        shape = RoundedCornerShape(8.dp), // Shape 입혀보기
        contentPadding = PaddingValues(20.dp) // 버튼 레이아웃과, Button 안에 UI Component들 사이의 여백 설정 (상, 하, 좌, 우 모두 별도로 설저 가능, border 의 안쪽 끝자락 부터 여백이 측정)
    ) {
        Icon( // Button 내 이미지 아이콘을 넣고 싶다면 Icon Composable 사용
            imageVector = Icons.Filled.Send, // Icon의 drawable 설정 가능, 프로젝트의 이미지 리소스를 사용할 꺼면 아래 처럼 사용 가능
            // imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
            contentDescription = null // 이 아이콘이 어떤 걸 의미 하는지 나타냄
        )
        Spacer(modifier = Modifier.size(8.dp)) // Icon과 Text 사이의 여백
        Text("Send")
    }
