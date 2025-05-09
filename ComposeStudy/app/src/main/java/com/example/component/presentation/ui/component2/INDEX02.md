# MyCompose
## Compose UI 종류

### ConstraintLayout 기초 정리
    @Composable
    fun ComposeConstraintLayout() {
      // xml에서 사용했던 ConstraintLayout과 크게 다르지 않아 보인다.
      ConstraintLayout(
          modifier = Modifier.fillMaxSize().background(Color.White)
      ) {
          
          // Child Compose의 id 생성 (createRefs 호출 한번 당 최대 16개까지 생성가능한 것 같다..? 17개 부터는 두번 호출 해야할 듯)
          val (redBox, yellowBox, blueBox, grayBox) = createRefs()
  
          Box(
              modifier = Modifier
                  .size(40.dp)
                  .background(Color.Red)
                  // 자기 자신의 id 할당
                  .constrainAs(redBox) {
                      // Compose 배치하기
                      bottom.linkTo(parent.bottom, margin = 8.dp) // redBox의 bottom을 부모의 bottom으로 배치 후 margin 설정
                      end.linkTo(parent.end, margin = 4.dp) // redBox의 end를 부모의 end로 배치 후 margin 설정
                  }
          )
  
          Box(
              modifier = Modifier
                  .size(40.dp)
                  .background(Color.Yellow)
                  .constrainAs(yellowBox) {
                      // ConstraintLayout에서 center 정렬
                      start.linkTo(parent.start) // yellowBox의 bottom을 부모의 start로 배치
                      end.linkTo(parent.end) // yellowBox의 end를 부모의 end로 배치
                  }
          )
  
          Box(
              modifier = Modifier
                  .size(40.dp)
                  .background(Color.Blue)
                  .constrainAs(blueBox) {
                      // 화면의 Vertical, Horizontal 정렬
                      // start.linkTo(parent.start)
                      // end.linkTo(parent.end)
                      // top.linkTo(parent.top)
                      // bottom.linkTo(parent.bottom)
  
                      centerTo(parent) // 위 4줄 코드와 동일
                      centerVerticallyTo(parent)
                      centerHorizontallyTo(parent) 이런 것 들도 있음
                  }
          )
  
          Box(
              modifier = Modifier
                  .size(40.dp)
                  .background(Color.Gray)
                  .constrainAs(grayBox) {
                      start.linkTo(yellowBox.end) // grayBox의 start를 yellowBox의 end로 배치
                      top.linkTo(yellowBox.bottom) // grayBox의 top을 yellowBox의 bottom 으로 배치
                  }
          )
      }
    }

### ConstraintSet
    @Composable
    fun ComposeConstraintSet() {
        // ConstraintSet
        // ConstrinatLayout 내 createRefs를 통해서 Compose 별 id를 설정하여 UI를 배치하였는데,
        // ConstriantSet을 통해서도 UI를 배치 할 수 있다. 짱 신기했다.
    
        val constraintSet = ConstraintSet {
            // ConstraintSet 내 UI를 배치할 id 생성하기
            // 인자값이 Compose UI에 적용될 id 값이다.
            val redBox = createRefFor("redBox")
            val yellowBox = createRefFor("yellowBox")
            val blueBox = createRefFor("blueBox")
            val grayBox = createRefFor("grayBox")
    
            constrain(redBox) {
                bottom.linkTo(parent.bottom, margin = 8.dp)
                end.linkTo(parent.end, margin = 4.dp)
            }
            constrain(yellowBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(blueBox) {
                centerTo(parent)
            }
            constrain(grayBox) {
                start.linkTo(yellowBox.end)
                top.linkTo(yellowBox.bottom)
            }
        }
    
        ConstraintLayout(
            constraintSet = constraintSet, // 앞서 설정한 ConstraintSet을 전달하기
            modifier = Modifier.fillMaxSize().background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Red)
                    .layoutId("redBox") // ConstraintSet 내부에서 createRefFor를 통해 생성된 id 할당
            )
    
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Yellow)
                    .layoutId("yellowBox")
            )
    
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Blue)
                    .layoutId("blueBox")
            )
    
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Gray)
                    .layoutId("grayBox")
            )
        }
    }

### ConstraintLayout Chain, Barrier
    @Composable
    fun ComposeConstraintChainBarrier() {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize().background(Color.White)
        ) {
            val (redBox, yellowBox, blueBox, text) = createRefs()
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Red)
                    .constrainAs(redBox) {
                        top.linkTo(parent.top, margin = 4.dp)
                    }
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Yellow)
                    .constrainAs(yellowBox) {
                        top.linkTo(parent.top, margin = 32.dp)
                    }
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Blue)
                    .constrainAs(blueBox) {
                        top.linkTo(parent.top, margin = 16.dp)
                    }
            )
    
            // createVerticalChain(redBox, yellowBox, blueBox) // 세로로 정렬
            // createHorizontalChain(redBox, yellowBox, blueBox) // 가로로 정렬
            createHorizontalChain(redBox, yellowBox, blueBox, chainStyle = ChainStyle.SpreadInside) // 기존 xml에서 사용 하던 것들을 거의 그대로 구현이 가능한 듯 (Spread, Packed 등등)
    
            val barrier = createBottomBarrier(redBox, yellowBox, blueBox) // Barrier 개념 또한 사용 가능하다.
            // createTopBarrier or createBottomBarrier 는 Horizontal 선상의 앵커 개념이라 top or bottom 으로 link 가능
            // createStartBarrier or createEndBarrier 는 Vertical 선상의 앵커 개념이라 start or end 로 link 가능
            Text(
                text = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(barrier)
                }
            )
        }
    }

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

### Dialog
    @Composable
    fun ComposeDialog() {
        // 팝업 show 하는 것 또한 Recomposition이 필요하다.
        // 그럼 팝업을 종료할때는? -> 역시 Recomposition이 필요하다.
        // 공부하면 할수록 느끼는거지만, UI에 변화가(상태의 변화가?) 또는 변경이 필요할 땐
        // Recomposition 이 필요한 것 같다.
        var isShowingDialog by remember { mutableStateOf(false) }
        var counter by remember { mutableIntStateOf(0) }
    
        Column {
            Button(
                onClick = {
                    isShowingDialog = true
                }
            ) {
                Text(text = "다이얼로그 열기")
            }
            Text(text = "카운터 : $counter")
        }
    
        if (isShowingDialog) {
            AlertDialog(
                onDismissRequest = {
                    // Dialog가 dismiss 하기 전 (팝업 외부 영역 클릭 했을 때 혹은 백버튼 클릭 일 떄나)
                    isShowingDialog = false
                },
                confirmButton = {
                    Button(
                        onClick = {
                            isShowingDialog = false
                            counter++
                        }
                    ) {
                        Text(text = "카운터 증가")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            isShowingDialog = false
                        }
                    ) {
                        Text(text = "취소")
                    }
                },
                title = {
                    Text(text = "더하기")
                },
                text = {
                    Text(text = "카운터가 증가됩니다.\n버튼 클릭해보세요.")
                }
            )
        }
    
    }

### CustomDialog
    @Composable
    fun ComposeCustomDialog() {
        var isShowingDialog by remember { mutableStateOf(false) }
        var counter by remember { mutableIntStateOf(0) }
    
        Column {
            Button(
                onClick = {
                    isShowingDialog = true
                }
            ) {
                Text(text = "다이얼로그 열기")
            }
            Text(text = "카운터 : $counter")
        }
    
        if (isShowingDialog) {
            Dialog(
                onDismissRequest = {
                    isShowingDialog = false
                }
            ) {
                // 오.. AlertDialog를 사용한 것보다 못생겼다.
                // AlertDialog는 표준으로 만들어 놓은 틀이 있는 것 같다.
                // 일부러 못생기게 보이도록 놔둬야지
                // 나중에 왜 그런지 생각할 수 있도록 하기 위해 냅두자
                Surface {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "click 해보세요"
                        )
                        Row {
                            Button(
                                onClick = {
                                    isShowingDialog = false
                                }
                            ) {
                                Text("취소")
                            }
    
                            Button(
                                onClick = {
                                    counter++
                                }
                            ) {
                                Text("+1")
                            }
    
                            Button(
                                onClick = {
                                    counter--
                                }
                            ) {
                                Text("-1")
                            }
    
                        }
                    }
                }
            }
        }
    }

### DropDownMenu
    @Composable
    fun ComposeDropDownMenu() {
        var expandDropDownMenu by remember { mutableStateOf(false) }
        var counter by remember { mutableIntStateOf(0) }
    
        Column {
            Button(
                onClick = {
                    expandDropDownMenu = true
                }
            ) {
                Text("드롭다운 메뉴 열기")
            }
            Text("카운터: $counter")
        }
        DropdownMenu(
            expanded = expandDropDownMenu,
            onDismissRequest = {
                expandDropDownMenu = false
            }
        ) {
            DropdownMenuItem(
                onClick = {
                    counter++
                },
                text = {
                    Text("증가")
                }
            )
    
            DropdownMenuItem(
                onClick = {
                    counter--
                },
                text = {
                    Text("감소")
                }
            )
        }
    }

### SnackBar
    @Composable
    fun ComposeSnackBar() {
        var counter by remember { mutableIntStateOf(0) }
        val coroutineScope = rememberCoroutineScope()
    
        // rememberScaffoldState() : 머테리얼3 이전까지는 Scaffold에서 스낵바를 띄우기 위해 사용했던것으로 파악된다.
        // 하지만 이제는 사용하지 않고, 아래를 사용하면 되는 것 같다
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) {
            Button(
                onClick = {
                    counter++
                    coroutineScope.launch {
                        // showSnackbar는 왜 suspend function인가?
                        // -> snackbar가 노출되고 난 후 일정 시간이 지나면 dismiss 시켜야하기 때문
                        val result = snackbarHostState.showSnackbar(
                            message = "count : $counter",
                            actionLabel = "닫기",
                            duration = SnackbarDuration.Short
                        )
    
                        when (result) {
                            SnackbarResult.Dismissed -> {
                                // Snackbar that is shown has been dismissed either by timeout of by user
                            }
                            SnackbarResult.ActionPerformed -> {
                                // Action on the Snackbar has been clicked before the time out passed
                            }
    
                        }
                    }
                }
            ) {
                Text("더하기")
            }
        }
    }

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

### 상태와 상태 호이스팅이란
    @Composable
    fun ComposeStateHoisting() {
    
        // 원래는 ViewModel에서 들고있는게 맞음 (UI의 상태를 들고있는게 맞음)
        var pyeong by rememberSaveable {
            mutableStateOf("23")
        }
    
        var squareMeter by rememberSaveable {
            mutableStateOf((23 * 3.306).toString())
        }
    
        PyeongToSquareMeterStateless(
            pyeong = pyeong,
            squareMeter = squareMeter
        ) {
            if (it.isBlank()) {
                pyeong = ""
                squareMeter = ""
                return@PyeongToSquareMeterStateless
            }
            val value = it.toFloatOrNull() ?: return@PyeongToSquareMeterStateless
            pyeong = it
            squareMeter = (value * 3.306).toString()
        }
    }
    
    /**
     * State Hoisting
     * 상태 호이스팅이란 UI관리와 상태 관리는 분리 시키는 것
     * 지금껏 Recomposition을 공부하였을 땐 Compose를 생성한 함수 내에서 State를 관리했다.
     * 하지만 이는 좋은 구조가 아니며, Recomposition에 필요한 상태는 ViewModel에서 관리되도록 하는것이 좋으며
     * 이는 곧 StateHoisting 이라고 할 수 있다.
     * PyeongToSquareMeterStateless 내부에서는 상태변경을 전혀하고 있지 않다.
     * PyeongToSquareMeterStateless처럼 UI Compose 함수에서는 Stateless 하게 작성하는 것이 좋아보인다.
     *
     * @param pyeong
     * @param squareMeter
     * @param onPyeongChange
     */
    @Composable
    fun PyeongToSquareMeterStateless(
        pyeong: String,
        squareMeter: String,
        onPyeongChange: (String) -> Unit,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = pyeong,
                onValueChange = onPyeongChange,
                label = {
                    Text("평")
                }
            )
    
            OutlinedTextField(
                value = squareMeter,
                onValueChange = onPyeongChange,
                label = {
                    Text("제곱미터")
                }
            )
        }
    }

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

### Compose Effect 정리
    /**
     * 부수효과? - SideEffect
     *
     * LaunchedEffect : Compose 함수에서 Suspend 함수 실행
     * rememberCoroutineScope : Compose 함수 외부에서 Coroutine 실행
     * rememberUpdatedState : 값이 변경되는 경우 다시 시작되지 않아야하는 효과
     * DisposableEffect : 짝이 필요한 효과 (열기 - 닫기) (늘리기 - 줄이기)
     * SideEffect : Compose 상태를 비 Compose로 변경
     * produceState : 비 Compose를 Compose 상태로 변경
     * derivedStateOf : 하나 이상의 상태 객체를 다른 상태로 변환
     * snapshowFlow : Compose의 상태를 flow로 변환
     */
    @Composable
    fun ComposeEffect(lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {
        val snackBarHostState = remember { SnackbarHostState() }
    
        LaunchedEffect(snackBarHostState) {
            snackBarHostState.showSnackbar(
                message = "Hello Compose",
                actionLabel = "취소",
                duration = SnackbarDuration.Short
            )
        }
    
        SnackbarHost(snackBarHostState)
    
        DisposableEffect(lifeCycleOwner) {
            val observer = LifecycleEventObserver { source: LifecycleOwner, event: Lifecycle.Event ->
                when (event) {
                    Lifecycle.Event.ON_CREATE -> Log.d("phael", "OnCreate")
                    Lifecycle.Event.ON_START -> Log.d("phael", "OnStart")
                    Lifecycle.Event.ON_RESUME -> Log.d("phael", "OnResume")
                    Lifecycle.Event.ON_PAUSE -> Log.d("phael", "OnPause")
                    Lifecycle.Event.ON_STOP -> Log.d("phael", "OnStop")
                    Lifecycle.Event.ON_DESTROY -> Log.d("phael", "OnDestroy")
                    else -> Log.d("phael", "그 외")
                }
            }
    
            lifeCycleOwner.lifecycle.addObserver(observer)
            onDispose {
                // onDispose
                // Composable이 화면에서 사라질 때 (화면에서 사라질 때) 실행
                // 인자로 받는 값이 변경이 될 때, onDispose 가 호출이 되고 DisposableEffect 자체가 리컴포지션이 일어남 -> 이게 LanchedEffect와 가장 큰 차이
                lifeCycleOwner.lifecycle.removeObserver(observer)
            }
        }
    }
