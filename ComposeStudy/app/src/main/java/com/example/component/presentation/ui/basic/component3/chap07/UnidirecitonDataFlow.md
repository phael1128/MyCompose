Unidirectional Data Flow : 단방향 데이터 흐름 - 줄여서 UDF

<img width="414" alt="스크린샷 2025-05-14 오전 11 22 50" src="https://github.com/user-attachments/assets/11d4a9ee-e469-47ac-9f04-c351509dc544" />

UDF의 전체적인 흐름
Compose는 ViewModel에게 이벤트를 전달.
ViewModel은 전달받은 이벤트에 따라 로직 및 상태를 관리
ViewModel은 관리하고있는 상태를 Compose에게 전달
Compose는 이 전달 받은 상태에 따라 Recomposition 수행

이 UDF의 전체적인 흐름을 구체화 시킨 것이 MVI 패턴이다.
