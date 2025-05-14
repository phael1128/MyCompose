# MyCompose
## Compose 종류

### DI&ViewModel
<pre><code>
@HiltViewModel
class ComposeViewModel @Inject constructor(
    private val composeStudyService: ComposeStudyService
) : ViewModel() {
    val repos = mutableStateListOf<ResRepo>()

    fun getRepos() {
        repos.clear()
        viewModelScope.launch {
            val result = composeStudyService.listRepos("phael1128")
            Log.d("phael", "result : ${result.joinToString()}")
            repos.addAll(result)
        }
    }
}
      
@Composable
fun ComposeViewModelComponent(
    viewModel: ComposeViewModel = hiltViewModel()
) {
    LazyColumn {
        item {
            Button(
                onClick = {
                    viewModel.getRepos()
                }
            ) {
                Text("get from Repository")
            }
        }
        items(viewModel.repos) {
            Text(it.name)
        }
    }
}
</code></pre>
