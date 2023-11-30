### API (Application Programming Interface)

- 사용설명서
- 어떤 Input을 주면 해당 Output 전달
- 레스토랑에서 점원의 역할
    - 손님에게 메뉴를 알려준다.
    - 주문받은 요리를 요청한다.
    - 완성된 요리를 손님에게 가져다 준다.
- 역할
    - 서버와 DB에 대한 출입구
        - 허용된 사람들에게만 접근성 부여
    - Application과 기기의 원활한 통신
        - 데이터를  원활히 주고 받을 수 있도록 돕는 역할
    - 모든 접속을 표준화
        - 기계/운영체제 상관없이 누구나 동일한 액세스

### REST (Respressentational State Transfer)

- HTTP Method를 활용해 통신
    - GET(Select) - DB 조회
    - POST(Insert, Update, Delete) - DB 조작
    - PUT - 한국에서 잘 사용하지 않음
    - DELETE - 한국에서 잘 사용하지 않음
- 특징
    - Server-Client 구조
        - Server와 Client가 독립적으로 분리
    - Stateless
        - 요청 간에 Client 정보가 Server 저장되지 않음
        - Server는 각 요청을 완전히 별개로 인식하고 처리
    - 인터페이스 일관성 (Uniform Interface)
        - HTTP 프로토콜을 따르는 모든 플랫폼에서 사용 가능

### RESTful API

------------------
<aside>
💡 References

</aside>

- [https://blog.wishket.com/api란-쉽게-설명-그린클라이언트/](https://blog.wishket.com/api%EB%9E%80-%EC%89%BD%EA%B2%8C-%EC%84%A4%EB%AA%85-%EA%B7%B8%EB%A6%B0%ED%81%B4%EB%9D%BC%EC%9D%B4%EC%96%B8%ED%8A%B8/)