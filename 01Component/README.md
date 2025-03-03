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

### Box
      // Box는 주로 어떤 상황에서 사용하나?
      // 1. UIContainer 자체를 하나 만들고 싶을 때
      // 2. xml의 FrameLayout 처럼 UI container 내에 UI 들을 중첩 시키기고 싶을 때

      // Text를 중첩 시켜보기
      // Modifier.align은 기본적으로 Box 안에서 사용가능, Box를 만들고 나면 람다에서 BoxScope를 주게 되는데, align 속성 자체가 BoxSceop의 함수이다.
     Box(
        modifier = Modifier.size(100.dp).background(Color.Red),
     ) {
        Text(
            text = "Hello World",
            modifier = Modifier.align(Alignment.BottomEnd)
        )

        Text(
            text = "Hello Compose",
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }

    //  3. Box에 modifier 설정을 제거하고, content size 만큼 보여주게 해보기
    // 이렇게 하게 되면 맨 마지막의 Box의 size인 70dp 만큼을 부모 Box의 사이즈가 잡히게된다.
    // 그래서 파란색 box가 초록색 box을 가려버림
    // 하지만 fillMaxSize를 한다면? 첫번째 Box의 사이즈가 화면 전체의 사이즈로 측정이 되어서 겹쳐서 배치 시킬 수 있음
    // fillMaxSize란? 화면 전체를 차지한다는 뜻
      Box {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Green).align(Alignment.CenterStart)
        )
        Box(
            modifier = Modifier.size(70.dp).background(Color.Blue).align(Alignment.Center)
        )
    }

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

### BoxWithConstraints
      BoxWithConstraints(
        modifier = Modifier
            .widthIn(min = 100.dp)
            .heightIn(min = 50.dp, max = 160.dp)
    ) {
        // BoxScope를 콜백 인자로 받아 참조할 수 있는 변수들은 아래와 같다.
        // "The constraints given by the parent layout in pixels." 이라 섪명되어 있고,
        // 해석하자면 "부모 레이아웃이 제공하는 픽셀 단위의 제약 조건."
        // BoxWithConstraints 가 가지고 있는 Modifier의 정보를 통해서
        // maxWidth, maxHeight, minWidth, minHeight 을 참조 가능하다.
        if (maxHeight > 150.dp) {
            Text(
                text = "좀 기네요",
                color = Color.Green
            )
        } else {
            Text(
                text = "짧네요.",
                color = Color.Green
            )
        }
        //Text(
        //    text = "maxWidth:$maxWidth, maxHeight:$maxHeight, minWidth:$minWidth, minHeight:$minHeight",
        //    color = Color.Green
        //)
    }

### Image
      Column {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // 프로젝트에 있는 리소스 참조
            contentDescription = "drawable 리소스로 이미지 참조하기"
        )

        Image(
            imageVector = Icons.Filled.Settings, // 머테리얼 아이콘의 리소스를 가져오기
            contentDescription = "머테리얼 아이콘의 리소스 참조하기"
        )

        // 비트맵도 가능
        // Image(
        //    bitmap = null,
        //    contentDescription = "비트맵으로 참조하기"
        //)
    }

### GlideImage
      @Composable
      fun ComposeNetworkImage() {
          Column(
              modifier = Modifier.fillMaxSize()
          ) {
              val imageResourceUrl = "https://www.nintendo.com/kr/character/kirby/assets/img/intro/kirby-star2.png"

              GlideImage(
                  model = imageResourceUrl,
                  contentDescription = "커비1",
              ) {
                  it.listener(object : RequestListener<Drawable> {
                      override fun onLoadFailed(
                          e: GlideException?,
                          model: Any?,
                          target: Target<Drawable>,
                          isFirstResource: Boolean
                      ): Boolean {
                          Log.d("phael", "Image Load Fail")
                          return false
                      }

                      override fun onResourceReady(
                          resource: Drawable,
                          model: Any,
                          target: Target<Drawable>?,
                          dataSource: DataSource,
                          isFirstResource: Boolean
                      ): Boolean {
                          Log.d("phael", "Image Load Success")
                          return true
                      }
                  })
            }
      }
    }

### Checkbox와 Recomposition 개념
      // ReComposition을 위한 상태 값 저장

      // isCheck를 일반 boolean 으로 해도 안되고
      // mutableStateOf도 안된다.
      // 왜냐하면 Checkbox의 checked 의 값을 변경 후 Checkbox가 그 변경된 값에 따라
      // 다시 UI가 그려져야하는데, 지금은 isCheck 값만 바꿔주고 있다.

      // -> 여기서 필요한 개념이 바로 Compose에서 말하는 Recomposition이다.
      // 즉 Recomposition이란
      // Compose의 UI가 다시 그려질 때, 이전에 기억한 상태값(isCheck)이 변경이 되면
      // 그때 컴포즈가 다시 그려진다.
      // remember { mutableStateOf } 를 통해서 상태를 저장한다.
      // remember -> Compose의 상태를 저장
      // mutableStateOf -> Compose에 필요한 상태의 자료형이 변경될 때 setValue 같은 역할(Compose에서 말하는 상태 그 자체)

      // Compose는 그려지다가 중단 될 수도 있고,
      // 그려지다가 다시 그려질 수도 있고,
      // 심지어 멀티 Thread에서 그릴수도 있다고한다.(MainThread에서만 그릴 수 있는게 아닌가 봄)
      // 이 때문에 Compose에서 상태를 저장하기 위해선 remember를 사용한다.

      // 기본형으로 사용해보기
      val isCheck = remember { mutableStateOf(false) }
      
      Checkbox(
            checked = isCheck.value,
            onCheckedChange = { isChecked ->
                isCheck.value = isChecked
            },
            onCheckedChange = setter
      )
      
      // delegated properties 위임시킨 패턴으로 사용해보기
      var isCheck by remember { mutableStateOf(false) }
      Checkbox(
            checked = isCheck,
            onCheckedChange = { isChecked ->
                isCheck = isChecked
            },
            onCheckedChange = setter
      )

      // val isCheck = remember {
      //      object : MutableState<Boolean> {
      //          override var value: Boolean
      //              get() = value
      //              set(paramValue) {
      //                  value = paramValue
      //              }

                // 비 구조화 -> val (a, b) = listOf(1, 2)
      //        override fun component1(): Boolean {
      //              // 비 구조화를 했을 때 그냥 getter
      //              return value
      //          }

      //          override fun component2(): (Boolean) -> Unit {
                    // 비 구조화를 했을 때 Setter
      //              val returnValue: (Boolean) -> Unit = {}
      //              return returnValue
      //          }
      //      }
      //  }
      
      // destruction 비구조화로부터 상태를 받아서 사용해보기
      val (getter, setter) = remember { mutableStateOf(false) }
        Checkbox(
            checked = getter,
            // onCheckedChange = { isChecked ->
                // setter(isChecked)
            // },
            onCheckedChange = setter // 이렇게도 가능
        )
        Text(
            text = "체크 박스",
            modifier = Modifier.clickable { //Text 클릭 시 Recomposition
                setter(!getter)
            }
        )
### TextField (EditText)
      @Composable
      fun ComposeTextField() {
          Column(
              modifier = Modifier.padding(16.dp)
          ) {
              // TextField : xml의 EditText같은 역할
              // 이렇게 하면 키보드를 입력해도 입력이 되지 않는다
              // 왜? -> 상태를 변경을 안했으니까~
              // TextField(
              //    value = "Tom",
              //    onValueChange = {}
              // )
        val userInput = remember { mutableStateOf("") }
        TextField(
            value = userInput.value,
            // label = {
            //    Text(text = "이름")
            // },
            onValueChange = { userInputValue ->
                userInput.value = userInputValue
            },
            placeholder = {
                Text("이름을 입력하세요") // xml의 EditText에서 hint 역할
            }
        )
        Spacer(
            modifier = Modifier.size(8.dp)
        )
        Text(
            text = "Hello ${userInput.value}!"
        )

        // 이런것도 있음
        // OutlinedTextField(
        //    value = userInput.value,
        //    label = {
        // Text(text = "이름")
        //    },
        //    onValueChange = { userInputValue ->
        //        userInput.value = userInputValue
        //    }
        // )
          }
      }

### TopAppBar
      @Composable
      fun ComposeTopAppBar() {
          Column() {
              TopAppBar(
                  title = {
                      Text("Top App Bar")
                  },
                  navigationIcon = {
                      IconButton(
                          onClick = {}
                      ) {
                          Icon(
                              imageVector = Icons.Filled.ArrowBack,
                              contentDescription = "뒤로가기"
                          )
                      }
                  },
                  actions = {
                      IconButton(
                          onClick = {}
                      ) {
                          Icon(
                              imageVector = Icons.Filled.Search,
                              contentDescription = "검색"
                          )
                      }

                      IconButton(
                          onClick = {}
                      ) {
                          Icon(
                              imageVector = Icons.Filled.Settings,
                              contentDescription = "설정"
                          )
                      }

                      IconButton(
                          onClick = {}
                      ) {
                          Icon(
                              imageVector = Icons.Filled.AccountBox,
                              contentDescription = "계정"
                          )
                      }
                  },
              )

              Text(text = "Top App Bar")
          }
      }

### TopAppBar
      @Composable
      fun ComposeSlot() {
          // SlotAPI란?
          // Composable 함수의 인자값으로 Composable 함수를 받는 것을 말한다.
          val checked1 = remember { mutableStateOf(false) }
          val checked2 = remember { mutableStateOf(false) }
      
          Column(
              modifier = Modifier.background(Color.White)
          ) {
              CheckboxWithSlot(
                  checked = checked1.value,
                  onCheckedChanged = {
                      checked1.value = !checked1.value
                  }
              ) {
                  Text(
                      text = "체크박스 1",
                      modifier = Modifier.align(Alignment.CenterVertically)
                  )
                  Log.d("phael", "${this@CheckboxWithSlot is RowScope}")
              }
      
              CheckboxWithSlot(
                  checked = checked2.value,
                  onCheckedChanged = {
                      checked2.value = !checked2.value
                  }
              ) {
                  Text(
                      "체크박스 2"
                  )
              }
          }
      }
      
      @Composable
      fun CheckboxWithSlot(
          checked: Boolean,
          onCheckedChanged: () -> Unit,
          content: @Composable RowScope.() -> Unit // 콜백 함수를 RowScope의 확장함수 형태로 쓸수가 있음
      ) {
          Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.clickable {
                  onCheckedChanged.invoke()
              }
          ){
              Checkbox(
                  checked = checked,
                  onCheckedChange = { _ ->
                      onCheckedChanged.invoke()
                  }
              )
              content()
          }
      }

### Scaffold
      @Composable
      fun ComposeScaffold() {
          // Scaffold를 사용하는 이유
          // 앱의 주요 UI 요소(예: 상단 바, 하단 바, FAB 등)를 쉽게 배치할 수 있음.
          // 레이아웃이 자동으로 정리되므로, 직접 배치할 때보다 더 깔끔하고 유지보수하기 쉬움.
      
          val checked = remember { mutableStateOf(false) }
          Scaffold(
              topBar = {
                  TopAppBar(
                      title = {
                          Text(text = "Scaffold")
                      },
                      navigationIcon = {
                          IconButton(
                              onClick = {}
                          ) {
                              Image(
                                  imageVector = Icons.Filled.ArrowBack,
                                  contentDescription = "뒤로가기"
                              )
                          }
                      },
                  )
              },
              floatingActionButton = {
                  FloatingActionButton(
                      onClick = {
      
                      }
                  ) {
      
                  }
              }
          ) { paddingValue ->
              Row (
                  modifier = Modifier.padding(top = paddingValue.calculateTopPadding())
              ) {
                  CheckboxWithSlot(
                      checked = checked.value,
                      onCheckedChanged = {
                          checked.value = !checked.value
                      }
                  ) {
                      Text(text = "컴포즈")
                  }
              }
          }
      }
