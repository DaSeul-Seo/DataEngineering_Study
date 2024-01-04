### HTTP

- Client (Request)와 Server (Response)간의 통신 규칙
- 서버는 클라이언트가 과거에 했던 행위를 기억하지 못한다. (서버는 기본적으로 Stateless)
- Stateless이 아니면 Client는 하나의 Server로만 통신을 해야하기 때문에 요청이 몰리는 경우 존재. ⇒ 분산을 위해 Stateless
- Server 장애시, 다른 Server와 통신을 할 수 있다.

### 동기 & 비동기

- 서버와 통신할 떄 비동기 통신

### promise

- 네트워크 통신에 대한 결과만 반환
- 코드를 동기적으로 실행할 수 있게 한다. (promise-then을 통해)