# MyCompose
## Compose UI 종류

### ConstraintLayout 기초 정리
    @Composable
    fun ComposeConstraintLayout() {
      // xml에서 사용했던 ConstraintLayout과 크게 다르지 않아 보인다.
      ConstraintLayout(
          modifier = Modifier.fillMaxSize().background(Color.White)
      ) {
          
          // Child Compose의 id 생성 (createRefs 호출 한번 당 최대 16개까지 생성가능한 것 같다..? 17개 부터는 두번 호출 해야할 듯)
          val (redBox, yellowBox, blueBox, grayBox) = createRefs()
  
          Box(
              modifier = Modifier
                  .size(40.dp)
                  .background(Color.Red)
                  // 자기 자신의 id 할당
                  .constrainAs(redBox) {
                      // Compose 배치하기
                      bottom.linkTo(parent.bottom, margin = 8.dp) // redBox의 bottom을 부모의 bottom으로 배치 후 margin 설정
                      end.linkTo(parent.end, margin = 4.dp) // redBox의 end를 부모의 end로 배치 후 margin 설정
                  }
          )
  
          Box(
              modifier = Modifier
                  .size(40.dp)
                  .background(Color.Yellow)
                  .constrainAs(yellowBox) {
                      // ConstraintLayout에서 center 정렬
                      start.linkTo(parent.start) // yellowBox의 bottom을 부모의 start로 배치
                      end.linkTo(parent.end) // yellowBox의 end를 부모의 end로 배치
                  }
          )
  
          Box(
              modifier = Modifier
                  .size(40.dp)
                  .background(Color.Blue)
                  .constrainAs(blueBox) {
                      // 화면의 Vertical, Horizontal 정렬
                      // start.linkTo(parent.start)
                      // end.linkTo(parent.end)
                      // top.linkTo(parent.top)
                      // bottom.linkTo(parent.bottom)
  
                      centerTo(parent) // 위 4줄 코드와 동일
                      centerVerticallyTo(parent)
                      centerHorizontallyTo(parent) 이런 것 들도 있음
                  }
          )
  
          Box(
              modifier = Modifier
                  .size(40.dp)
                  .background(Color.Gray)
                  .constrainAs(grayBox) {
                      start.linkTo(yellowBox.end) // grayBox의 start를 yellowBox의 end로 배치
                      top.linkTo(yellowBox.bottom) // grayBox의 top을 yellowBox의 bottom 으로 배치
                  }
          )
      }
    }
