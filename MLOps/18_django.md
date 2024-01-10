※ AWS

※ 인스턴스 : t2.medium이어야 hadoop이 돌아간다.

---

1. 작업 폴더 만들기
2. 가상환경 만들기
    
    ```bash
    # virtualenv 패키지 설치
    pip install virtualenv
    
    # 가상환경 만들기
    python -m virtualenv venv
    
    # python:select interpreter
    ctrl + shift p -> 권장 버전
    
    # pip list 확인
    pip list
    ```
    
3. django 설치
    
    ```bash
    pip install django
    ```
    
4. mysite project 만들기
    
    ```bash
    django-admin startproject mysite
    ```
    
5. django 구동
    1. manage.py  : django를 관리하는 프로그램
    
    ```bash
    python manage.py runserver
    ```
    
### Django

- MVT 패턴
    - Model - View(Controller) - Template(View)
- settings.py
    - 정적인 이미지 등 환경설정파일
- Web Server VS Was Server
    - 효율성 때문에 나누었다.
    - Web Server : 정적 (html, image, css 등)
    - Was Server : 동적 (통신기능 등)
        - Django는 구니콘
        - Java는 Tomcat
        - NGINX, Apache, GWS
- project명/project폴더명 : 환경폴더
- 하나하나 메뉴를 app으로 본다.
- db 환경 구축
    - python manage.py migrate