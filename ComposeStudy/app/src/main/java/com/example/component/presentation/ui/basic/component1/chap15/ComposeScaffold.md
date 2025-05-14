# MyCompose
## Compose UI 종류
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