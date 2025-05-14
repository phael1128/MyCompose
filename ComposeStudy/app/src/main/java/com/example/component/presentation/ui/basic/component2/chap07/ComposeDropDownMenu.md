# MyCompose
## Compose UI 종류

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