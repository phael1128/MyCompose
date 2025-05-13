Compose Rendering
Compose => 데이터를 UI 로 표시

Data -> Composition -> Layout -> Drawing -> UI

Composition : 화면에 무엇을 보여지게 할 것 이냐?
<pre><code>
    Row {
        Image()
        Column {
            Text()
            Text()
        }
    }
  </code></pre>
위와 같은 컴포즈는 아래와 같은 구조를 가지게 되며, UI Tree 구조라고도 불린다.
<img width="1120" alt="스크린샷 2025-05-13 오후 10 42 13" src="https://github.com/user-attachments/assets/bdc18858-86eb-4f10-98ff-9060608ea16f" />

Layout : 각 요소가 화면상 어디에 보여지게 할 것이냐?
1. 자식 측정
2. 본인 크기 측정
3. 자식 배치
순으로 컴포즈 UI를 배치시킨다.
모든 Compose 노드를 단 한번만 방문하며 이는 곧 Compose 가 기존 xml 방식보다 소요시간이 훨 짧음을 의미한다.

Drawing : 각 요소가 화면에 어떻게 그려지게 할 것이냐?
