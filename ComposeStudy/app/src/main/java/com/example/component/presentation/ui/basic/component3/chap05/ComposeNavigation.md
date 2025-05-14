# MyCompose
## Compose 종류

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