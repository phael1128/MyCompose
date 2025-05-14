# MyCompose
## Compose UI 종류
### Image
      Column {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // 프로젝트에 있는 리소스 참조
            contentDescription = "drawable 리소스로 이미지 참조하기"
        )

        Image(
            imageVector = Icons.Filled.Settings, // 머테리얼 아이콘의 리소스를 가져오기
            contentDescription = "머테리얼 아이콘의 리소스 참조하기"
        )

        // 비트맵도 가능
        // Image(
        //    bitmap = null,
        //    contentDescription = "비트맵으로 참조하기"
        //)
    }