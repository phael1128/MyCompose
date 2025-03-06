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

### ConstraintSet
    @Composable
    fun ComposeConstraintSet() {
        // ConstraintSet
        // ConstrinatLayout 내 createRefs를 통해서 Compose 별 id를 설정하여 UI를 배치하였는데,
        // ConstriantSet을 통해서도 UI를 배치 할 수 있다. 짱 신기했다.
    
        val constraintSet = ConstraintSet {
            // ConstraintSet 내 UI를 배치할 id 생성하기
            // 인자값이 Compose UI에 적용될 id 값이다.
            val redBox = createRefFor("redBox")
            val yellowBox = createRefFor("yellowBox")
            val blueBox = createRefFor("blueBox")
            val grayBox = createRefFor("grayBox")
    
            constrain(redBox) {
                bottom.linkTo(parent.bottom, margin = 8.dp)
                end.linkTo(parent.end, margin = 4.dp)
            }
            constrain(yellowBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(blueBox) {
                centerTo(parent)
            }
            constrain(grayBox) {
                start.linkTo(yellowBox.end)
                top.linkTo(yellowBox.bottom)
            }
        }
    
        ConstraintLayout(
            constraintSet = constraintSet, // 앞서 설정한 ConstraintSet을 전달하기
            modifier = Modifier.fillMaxSize().background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Red)
                    .layoutId("redBox") // ConstraintSet 내부에서 createRefFor를 통해 생성된 id 할당
            )
    
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Yellow)
                    .layoutId("yellowBox")
            )
    
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Blue)
                    .layoutId("blueBox")
            )
    
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Gray)
                    .layoutId("grayBox")
            )
        }
    }

### ConstraintLayout Chain, Barrier
    @Composable
    fun ComposeConstraintChainBarrier() {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize().background(Color.White)
        ) {
            val (redBox, yellowBox, blueBox, text) = createRefs()
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Red)
                    .constrainAs(redBox) {
                        top.linkTo(parent.top, margin = 4.dp)
                    }
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Yellow)
                    .constrainAs(yellowBox) {
                        top.linkTo(parent.top, margin = 32.dp)
                    }
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Blue)
                    .constrainAs(blueBox) {
                        top.linkTo(parent.top, margin = 16.dp)
                    }
            )
    
            // createVerticalChain(redBox, yellowBox, blueBox) // 세로로 정렬
            // createHorizontalChain(redBox, yellowBox, blueBox) // 가로로 정렬
            createHorizontalChain(redBox, yellowBox, blueBox, chainStyle = ChainStyle.SpreadInside) // 기존 xml에서 사용 하던 것들을 거의 그대로 구현이 가능한 듯 (Spread, Packed 등등)
    
            val barrier = createBottomBarrier(redBox, yellowBox, blueBox) // Barrier 개념 또한 사용 가능하다.
            // createTopBarrier or createBottomBarrier 는 Horizontal 선상의 앵커 개념이라 top or bottom 으로 link 가능
            // createStartBarrier or createEndBarrier 는 Vertical 선상의 앵커 개념이라 start or end 로 link 가능
            Text(
                text = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(barrier)
                }
            )
        }
    }
