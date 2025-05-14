# MyCompose
## Compose 종류

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