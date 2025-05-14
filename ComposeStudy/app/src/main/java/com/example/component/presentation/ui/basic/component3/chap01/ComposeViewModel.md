# MyCompose
## Compose 종류

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
