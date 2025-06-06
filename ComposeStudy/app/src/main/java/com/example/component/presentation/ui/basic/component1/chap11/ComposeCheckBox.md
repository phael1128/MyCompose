# MyCompose
## Compose UI 종류
### Checkbox와 Recomposition 개념
      // ReComposition을 위한 상태 값 저장

      // isCheck를 일반 boolean 으로 해도 안되고
      // mutableStateOf도 안된다.
      // 왜냐하면 Checkbox의 checked 의 값을 변경 후 Checkbox가 그 변경된 값에 따라
      // 다시 UI가 그려져야하는데, 지금은 isCheck 값만 바꿔주고 있다.

      // -> 여기서 필요한 개념이 바로 Compose에서 말하는 Recomposition이다.
      // 즉 Recomposition이란
      // Compose의 UI가 다시 그려질 때, 이전에 기억한 상태값(isCheck)이 변경이 되면
      // 그때 컴포즈가 다시 그려진다.
      // remember { mutableStateOf } 를 통해서 상태를 저장한다.
      // remember -> Compose의 상태를 저장
      // mutableStateOf -> Compose에 필요한 상태의 자료형이 변경될 때 setValue 같은 역할(Compose에서 말하는 상태 그 자체)

      // Compose는 그려지다가 중단 될 수도 있고,
      // 그려지다가 다시 그려질 수도 있고,
      // 심지어 멀티 Thread에서 그릴수도 있다고한다.(MainThread에서만 그릴 수 있는게 아닌가 봄)
      // 이 때문에 Compose에서 상태를 저장하기 위해선 remember를 사용한다.

      // 기본형으로 사용해보기
      val isCheck = remember { mutableStateOf(false) }
      
      Checkbox(
            checked = isCheck.value,
            onCheckedChange = { isChecked ->
                isCheck.value = isChecked
            },
            onCheckedChange = setter
      )
      
      // delegated properties 위임시킨 패턴으로 사용해보기
      var isCheck by remember { mutableStateOf(false) }
      Checkbox(
            checked = isCheck,
            onCheckedChange = { isChecked ->
                isCheck = isChecked
            },
            onCheckedChange = setter
      )

      // val isCheck = remember {
      //      object : MutableState<Boolean> {
      //          override var value: Boolean
      //              get() = value
      //              set(paramValue) {
      //                  value = paramValue
      //              }

                // 비 구조화 -> val (a, b) = listOf(1, 2)
      //        override fun component1(): Boolean {
      //              // 비 구조화를 했을 때 그냥 getter
      //              return value
      //          }

      //          override fun component2(): (Boolean) -> Unit {
                    // 비 구조화를 했을 때 Setter
      //              val returnValue: (Boolean) -> Unit = {}
      //              return returnValue
      //          }
      //      }
      //  }
      
      // destruction 비구조화로부터 상태를 받아서 사용해보기
      val (getter, setter) = remember { mutableStateOf(false) }
        Checkbox(
            checked = getter,
            // onCheckedChange = { isChecked ->
                // setter(isChecked)
            // },
            onCheckedChange = setter // 이렇게도 가능
        )
        Text(
            text = "체크 박스",
            modifier = Modifier.clickable { //Text 클릭 시 Recomposition
                setter(!getter)
            }
        )