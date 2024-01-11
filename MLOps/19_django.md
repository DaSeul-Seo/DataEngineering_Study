- AWS
    - 인스턴스 : t2.medium이어야 hadoop이 돌아간다.

---

### Django

- 파이썬 웹 프레임워크
- MVT 패턴
    - Model : 데이터
    - View(Controller) : 데이터를 처리하는 로직
    - Template(View) : 사용자 인터페이스
- settings.py
    - 정적인 이미지 등 환경설정파일
- Web Server VS Was Server
    - 효율성 때문에 나누었다.
    - Web Server : 정적 (html, image, css 등)
    - Was Server : 동적 (통신기능 등)
        - Django는 구니콘
        - Java는 Tomcat
        - NGINX, Apache, GWS
- ORM 기능 제공
- 관리자 화면 제공
    - 별도로 만들 필요 없
- project명/project폴더명 : 환경폴더
- 하나하나 메뉴를 app으로 본다.
- settings.py & urls.py 파일만 주로 수정한다.
- WSGI (Web Server Gateway Interface)
    - 웹서버는 Nginx나 Apache 같은 서버 컴퓨터에서 사용자 요청을 받아서 처리
    - python 에서는 웹서버 - Nginx / WSGI 서버 - Gunicorn 사용
- 내장 서버는 기능이 단순하고 대량 요청이나 동시요청을 효율적 처리 불가
    - python manage.py runserver는 내장 서버 실행한 것
- Middleware는 주로 보안관한 내용이 다수
- sqlmigration
    - python으로 작성한 코드를 변경하여 DB로 저장하겠다.