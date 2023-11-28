### JSTL

### 💡 URL 해석

URL - http://localhost:8080/Board/jsp/ex01.jsp?addr=서울

| Name | Description |
| --- | --- |
| http: | 인터넷 통신규약 |
| //localhost | 서버주소 |
| :8080 | port 번호 |
| /Board | - Context Path (서버에 등록된 프로젝트) <br /> - 접근하고 싶은 프로젝트명 |
| /jsp/ex01.jsp | 파일위치(request 요청을 처리할 servlet) |
| ?addr=서울 | query (key=value) |
<br />

URL - https://n.news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=102

| Name | Description |
| --- | --- |
| https: | 인터넷 통신규약 <br /> - https: -> port번호 : 443 <br /> - http: -> port번호 : 80 <br /> -> 서버가 허락된 port번호는 443 |
| //n.news.naver.com | 서버가 설치된 컴퓨터 주소 |
| /main/main.naver | url path 정의 |
| ?mode=LSD&mid=shm&sid1=102 | 쿼리 |
<br />

- Java 코드는 Java resources에서만 작성
    - ⭐ src폴더 내의 /main/java에서 수정하면 안됨!!!!!

### JSP 역할

1. 브라우저 Request 요청
2. JSP
    1. Request 요청 처리
        1. MySQL 데이터 조회
        2. HTML(화면) 생성
3. 브라우저 Response

### Code

- Board 프로젝트 확인

----
<aside>
💡 Reference

</aside>

- JSTL
    - https://mvnrepository.com/search?q=jstl