# MyCompose
## Compose 활용

### ViewModel
  <pre><code>
class ToDoViewModel: ViewModel() {      
  // remember를 하지 않은 이유
  // 기존 remember는 Compose의 생명주기에 따라간거고    
  // ViewModel에서는 Compose 가 아닌 ViewModel의 생명주기에 따라가는 것이 맞다.
  val text = mutableStateOf("")
    
  val todoList = mutableStateListOf<ToDoData>()
    
  val onSubmit: (String) -> Unit = {
      val key = (todoList.lastOrNull()?.key ?: 0) + 1
      todoList.add(ToDoData(key, it))
      text.value = ""
  }
    
  val onEdit: (Int, String) -> Unit = { key, newText ->
    val i = todoList.indexOfFirst { it.key == key }
    todoList[i] = todoList[i].copy(text = newText)
  }
    
  val onToggle: (Int, Boolean) -> Unit = { key, checked ->
     val i = todoList.indexOfFirst { it.key == key }
    todoList[i] = todoList[i].copy(done = checked)
  }
    
  val onDelete: (Int) -> Unit = { key ->
      val i = todoList.indexOfFirst { it.key == key }
      todoList.removeAt(i)
    }
}
    
@Composable
fun TopLevel(
    viewModel: ToDoViewModel = viewModel()
) {
        Scaffold {
            Column {
                ToDoInput(
                    text = viewModel.text.value,
                    onTextChange = {
                        viewModel.text.value = it
                    },
                    onSubmit = viewModel.onSubmit
                )
                LazyColumn {
                    items(
                        items = viewModel.todoList,
                        key = { it.key }
                    ) { toDoData ->
                        ToDo(
                            toDoData = toDoData,
                            onEdit = viewModel.onEdit,
                            onToggle = viewModel.onToggle,
                            onDelete = viewModel.onDelete
                        )
                    }
                }
            }
        }
}  
  </code></pre> 

### LiveData
<pre><code>
  class LiveDataViewModel: ViewModel() {
    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    val setText: (String) -> Unit = {
        _text.value = it
    }

    private val _rawTodoList = mutableListOf<ToDoData>()
    private val _todoList = MutableLiveData<List<ToDoData>>(_rawTodoList)
    val todoList: LiveData<List<ToDoData>> = _todoList

    // mutableStateListOf - 추가, 삭제, 대입 할 때 Recomposition이 된다. 각 항목의 필드가(인덱스별 model의 프로퍼티) 바뀌었을 때는 Recomposition이 되지 않는다.
    // LiveData<List<T>>.observeAsState() -> List가 통채로 다른 List로 바뀌었을 때만 Recomposition이 된다.
    // 상당히 비효율적이나, 일단 한번은 써보기

    val onSubmit: (String) -> Unit = {
        val key = (_rawTodoList.lastOrNull()?.key ?: 0) + 1
        _rawTodoList.add(ToDoData(key, it))
        _todoList.value = mutableListOf<ToDoData>().also { list ->
            list.addAll(_rawTodoList)
        }
        _text.value = ""
    }

    val onEdit: (Int, String) -> Unit = { key, newText ->
        val i = _rawTodoList.indexOfFirst { it.key == key }
        _rawTodoList[i] = _rawTodoList[i].copy(text = newText)
        _todoList.value = _rawTodoList.toMutableList()
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val i = _rawTodoList.indexOfFirst { it.key == key }
        _rawTodoList[i] = _rawTodoList[i].copy(done = checked)
        _todoList.value = mutableListOf<ToDoData>().also {
            it.addAll(_rawTodoList)
        }
    }

    val onDelete: (Int) -> Unit = { key ->
        val i = _rawTodoList.indexOfFirst { it.key == key }
        _rawTodoList.removeAt(i)
        _todoList.value = mutableListOf<ToDoData>().also {
            it.addAll(_rawTodoList)
        }
    }
}

@Composable
fun LiveDataTopLevel(
    viewModel: LiveDataViewModel = viewModel()
) {
    Scaffold {
        Column {
            ToDoInput(
                text = viewModel.text.observeAsState("").value,
                onTextChange = viewModel.setText,
                onSubmit = viewModel.onSubmit
            )
            val items = viewModel.todoList.observeAsState(emptyList<ToDoData>()).value
            LazyColumn {
                items(
                    items = items,
                    key = { it.key }
                ) { toDoData ->
                    ToDo(
                        toDoData = toDoData,
                        onEdit = viewModel.onEdit,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete
                    )
                }
            }
        }
    }
}
</code></pre> 

### Navigation
<pre><code>
@Composable
fun MyNav(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
) {
    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.d("phael", "currentBackStack : ${controller.currentBackStack.value.joinToString(",")}")
        }
    }

    NavHost(
        navController,
        startDestination = "Home",
        modifier = modifier
    ) {
        // Navigation이 도착할 라우터이름
        composable("Home") {
            Column {
                Text("Home")
                Button(
                    onClick = {
                        // Navigation 이동 시작
                        navController.navigate("Office") {
                            // Office로 이동하게 되면 Home 위에 Office가 Stack에 있을텐데
                            // 이 Office에 도착하게 되면 NavHostController가 관리하는 스택에서, Home라우터를 찾아 Home을 지워버림
                            // 그 후 바로 Office 가 온다.
                            // splash -> login -> profile -> home 이 구조에서 popUpTo("Home")을 하게 된다면
                            // splash -> login -> profile -> office 이렇게 됨
                            popUpTo("Home") {
                                // isclusive를 쓰게 되면 맨 바닥부터 Home까지 clear 되고 Office만 남게됨
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text("Office로 이동")
                }
                Button(
                    onClick = {
                        // Navigation 이동 시작
                        navController.navigate("Playground")
                    }
                ) {
                    Text("Playground로 이동")
                }
                Button(
                    onClick = {
                        // Navigation 이동 시작
                        navController.navigate("Home") {
                            // Stack 구조상 최상단에 있으면 새로 띄우지 않음
                            // 즉 Home -> Home 으로 이동했으니 Home을 새로 띄우지 않음
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text("Home으로 이동")
                }
                Button(
                    onClick = {
                        navController.navigate("Argument/Phael") {}
                    }
                ) {
                    Text("Argument 화면으로 이동")
                }
            }
        }
        composable("Office") {
            Column {
                Text("Office")
                Button(
                    onClick = {
                        // Navigation 이동 시작
                        navController.navigate("Home") {
                            popUpTo("Home") {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text("Home으로 이동")
                }
                Button(
                    onClick = {
                        // Navigation 이동 시작
                        navController.navigate("Playground")
                    }
                ) {
                    Text("Playground로 이동")
                }
            }
        }
        composable("PlayGround") {
            Column {
                Text("PlayGround")
                Button(
                    onClick = {
                        // Navigation 이동 시작
                        navController.navigate("Home")
                    }
                ) {
                    Text("Home으로 이동")
                }
                Button(
                    onClick = {
                        // Navigation 이동 시작
                        navController.navigate("Office")
                    }
                ) {
                    Text("Office으로 이동")
                }
            }
        }
        composable("Argument/{userId}") {
            val userId = it.arguments?.get("userId")
            Text("userId : $userId")
        }
    }
}
</code></pre>
