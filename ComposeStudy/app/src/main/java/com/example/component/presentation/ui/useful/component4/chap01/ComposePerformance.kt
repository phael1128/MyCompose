package com.example.component.presentation.ui.useful.component4.chap01

/**
 * Lifecycle : 생명주기
 * Rendering Phase : 렌더링 단계
 * Stability : 안정성
 *
 */

/**
 * Lifecycle : 수명주기
 *
 *
 */

/**
 * Rendering Phase : 렌더링 단계
 * Compose => 데이터를 UI 로 표시
 *
 * Data -> Composition -> Layout -> Drawing -> UI
 *
 * Composition : 화면에 무엇을 보여지게 할 것 이냐
 *     Row {
 *         Image()
 *         Column {
 *             Text()
 *             Text()
 *         }
 *     }
 *
 * UI Tree - 위와 같은 컴포즈는 아래와 같은 구조를 가지게 된다.(사진 첨부)
 *
 * Layout : 각 요소가 화면상 어디에 보여지게 할 것 이냐
 * 1. 자식 측정
 * 2. 본인 크기 측정
 * 3. 자식 배치
 * 순으로 컴포즈 UI 를 배치시킨다.
 * 모든 Compose 노드를 단 한번만 방문하며 이는 곧 Compose가 기존 xml 방식보다 소요시간이 훨 짧음을 의미한다.
 *
 * Drawing : 각 요소를 화면에 어떻게 그릴 것 이냐
 *
 */

/**
 * Stability : 안정성
 * Smart Recomposition -> "성능 향상"을 위해 변경된 부분"만" 다시 그리기
 *
 * 코드 첨부
 * @Composable
 * fun LoginScreen(showError: Boolean) {
 *     if (showError) {
 *         LoginError()
 *     }
 *     LoginInput()
 * }
 *
 * 사진 첨부
 * LoginInput은 LoginError가 있던 말던 항상 같은 인스턴스를 유지한다.
 * 그리고 리컴포지션이 일어나게 되면 새로운 컴포즈 인스턴스를 생성한다.
 * 그렇다면 Compose는 리컴포지션이 필요한지 아닌지 어떻게 알 수 있을까?
 * 즉 컴포즈는 LoginInput을 어떻게 관리하길래 가만히 냅두고, LoginError는 리컴포지션 시켰을까?
 * 이에 대한 해답이 Stability(안정성) System
 *
 * 컴포즈는 파라미터(매개변수)를 통해 판단할 수 있다.
 *
 * 우선 컴포저블 함수 매개변수의 클래스 타입이다.
 * Unstable -> 데이터 변경 가능 / 변경시 컴포지션에서 추적 불가 => 리컴포지션이 무조건 발생
 * Stable -> 데이터 변경 가능 / 변경시 컴포지션에서 추적 가능 => 즉 화면 변경
 * Immutable -> 데이터 변경 불가 / 안정적인 데이터로 취급, 원시타입(String, Int, Float)
 *
 * data class Contact(
 *     val name: String,
 *     val number: String
 * )
 *
 * @Composable
 * fun ContactRow(
 *     contact: Contact,
 *     modifier: Modifier = Modifier
 * ) {
 *     var selected by remember { mutableStateOf(false) }
 *     Row (modifier) {
 *         ContactDetails(contact) // Contact는 immutable 하기 때문에 Smart Recomposition -> Recomposition이 되지 않는다.
 *         ToggleButton(
 *             selected, // Stable한 값이기 때문에 Recomposition의 대상이 된다.
 *             onToggled = { selected = !selected }
 *         )
 *     }
 * }
 *
 * 만약
 *  data class Contact(
 *      var name: String,
 *      var number: String
 *  )
 *  이라면?
 *  ContactDetails(contact) 이 Recomposition이 된다.
 *  mutable하며, unStable하기 때문이다.
 *
 *
 */

