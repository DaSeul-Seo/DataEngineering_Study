### Servlet

- 브라우저에서 요청받음
- 요청 → Servlet → Model → (DBMS → Model) → View (HTML) → 응답(Rendering)
    - Java : Servlet → Model → (DBMS → Model)
    - HTM: : View (HTML)

### MVC (Model-View-Controller)

- Model
    - DB CRUD 역할
    - DTO, DAO
- View
    - 화면
    - 사용자에게 응답
- Controller
    - 브라우저 요청 처리
    - Model, View 요청 및 응답 처리
    - Servlet 역할

### web.xml 환경설정

- Entry Point Controller 설정 및 빌드 정보

### 흐름도

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/983a6d32-6218-4ead-9d87-6ffc302cca32/45330eaa-2ad4-4ad2-9d98-a8e27cafe06c/Untitled.png)

1. 브라우저 요청
2. Controller가 받아서 요청 처리
    1. 요청에 따라서 Handler (DTO, DAO) 에게 전달
    2. Model, View 요청 및 응답 처리
3. Model이 데이터 처리
    1. 비즈니스 로직 (DB CRUD 등)
4. Model 응답을 Handler를 통해 Controller에 전달
5. Controller는 Model의 결과를 View에 전달
6. View는 사용자에게 응답 화면을 전달

### properties

- 설정 관련 파일
- web.xml에 properties에 관한 내용 기입 필요