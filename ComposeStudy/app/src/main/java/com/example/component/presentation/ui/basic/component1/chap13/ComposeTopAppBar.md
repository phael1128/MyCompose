# MyCompose
## Compose UI 종류
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