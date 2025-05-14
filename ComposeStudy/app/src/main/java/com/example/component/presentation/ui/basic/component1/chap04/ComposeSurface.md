# MyCompose
## Compose UI 종류
### Surface
      // Surface : Compose UI의 Container로써 배경색, 그림자, 모양 등등을 설정하기 위해 사용

      /**
      * padding의 개념
      * 기존 xml에서 TextView에 margin을 넣고 싶다면 setPadding으로 해결이 가능했다.
      * 하지만 Compose는 margin이라는 개념은 별도로 존재하지 않고, padding 개념만 존재한다.
      * 그럼 만약 Compose UI를 활용하면서 margin을 사용하고 싶다면?
      * container에 padding을 줌으로써 setMargin 과 비슷한 역할을 하는 것 같다. (Spacer랑 차이는 뭐지?)
      */
      
      Surface(
        modifier = Modifier.padding(5.dp),
        shadowElevation = 5.dp, // elevation 써보기(그림자 효과)
        border = BorderStroke(2.dp, Color.Gray), // border 설정
        shape = CircleShape, //shape 설정
        color = MaterialTheme.colorScheme.error // 머테리얼에서 제공하는 컬러 속성
    ) {
        Text(
            text = "Hello World",
        )
    }