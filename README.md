# Continuation Passing Style 예제

- Linked Task
- Serial Task
- Continuation Task

## CPS 란?

- 협력적 스케줄링을 구현하기 위한 방법 중 하나이다.
    - 협력적 스케줄링 (Cooperative Scheduling)
        - 하나의 Task 가 끝날때까지 다른 Task 로 넘어가지 않는 방식
        - 작은 단위의 많은 Task 를 실행하는데 적합하다.
- `Continuation` -> `Callback` 이라는 개념으로 생각해볼 수 있다.
- 즉, `Callback` 함수를 전달하는 방식의 스케줄링 기법이다.
- 결과 값을 리턴하지 않는다. 결과 다음으로 수행될 `코드 조각` 인 `Continuation` 을 전달한다.
- `Node.js` 에서는 `CPS` 기법을 통해 많은 요청을 동시에 처리할 수 있는 이유이다.
