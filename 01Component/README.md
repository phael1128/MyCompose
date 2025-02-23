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

### Surface
      // Surface : 머테리얼 디자인 원칙을 따르는 UI Container
      // 기본적으로 머테리얼에서 제공하는 배경색, 그림자, 모양 등등을 설정하기 위해 사용

      /**
      * padding의 개념
      * 기존 xml에서 TextView에 margin을 넣고 싶다면 setPadding으로 해결이 가능했다.
      * 하지만 Compose는 margin이라는 개념은 별도로 존재하지 않고, padding 개념만 존재한다.
      * 그럼 만약 Compose UI를 활용하면서 margin을 사용하고 싶다면?
      * container에 padding을 줌으로써 setMargin 과 비슷한 역할을 하는 것 같다. (Spacer랑 차이는 뭐지?)
      */
      
      Surface(
        modifier = Modifier.padding(5.dp),
        shadowElevation = 5.dp, // elevation 써보기(그림자 효과)
        border = BorderStroke(2.dp, Color.Gray), // border 설정
        shape = CircleShape, //shape 설정
        color = MaterialTheme.colorScheme.error // 머테리얼에서 제공하는 컬러 속성
    ) {
        Text(
            text = "Hello World",
        )
    }
    
