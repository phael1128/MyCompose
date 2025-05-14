# MyCompose
## Compose UI 종류
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