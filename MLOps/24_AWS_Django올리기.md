- AWS 인스턴스 30개 제한
- AWS 이미지 굽기
    - 이미지 구울 인스턴스 중지
    - 인스턴스 선택
        - 작업
        - 이미지 및 템플릿
        - 이미지 생성
- 탄력적 IP
    - 고정 IP
    - 4-5 제한
    - 한달 5,000원 정도

---

### AWS - Django 올리기

- AWS
    - 인스턴스 시작
    - public IP 복사
    - 푸티로 서버 시작
        
        ```bash
        sudo apt update
        ```
        
        ```bash
        sudo apt install python3-pip
        # OK : Enter
        ```
        
    - Django 올릴 디렉토리 생성
        - mkdir ~/repos
    - 저장소 공간 만들기
        - cd ~/repos
        - repos폴더를 깃이 저장할 폴더로 지정하겠다. (띄어쓰기 조심)
            - git init --bare .
        - ls -al 하면 파일들이 생김
    - 내컴퓨터에서 ssh 키 확인 (로컬)
        - users\{사용자이름}\.ssh
        - 여기에 id_rsa.pub 파일 내용 복사
        - 없는 경우
            - ssh-keygen -t rsa
    - ubuntu 에서 cd ~/.ssh
        - vi authorized_keys
        - 로컬 키 추가 (pub)
    - terminal에서 ec2 서버 접속
        - ssh ubuntu@{IP}
    - https://www.toptal.com/developers/gitignore
        - 해당 프로젝트응 Django니까 Django 검색해서 복사
        - 깃 ignore 리스트 만들기
    - vs code
        - 장고 프로젝트 폴더에서 터미널
            - django/mysite (가상환경 X)
        - git init
        - 서버랑 연결 ⇒ 로컬에 있는 것 aws에 올리기
            - git remote add origin ssh://ubuntu@3.38.98.111:/home/ubuntu/repos
        - 해당 프로젝트 올리기
            - git add .
            - git commit -m “first”
            - git push origin master
    - 서버
        - ssh-keygen -t rsa
        - cat ~/.ssh/id_rsa.pub >> authorized_keys
        - mkdir ~/workspace && cd ~/workspace
        - git clone ssh://ubuntu@localhost:/home/ubuntu/repos
        - cd ~/workspace/repos
            - sudo apt-get install python3-dev default-libmysqlclient-dev build-essential pkg-config
            - pip install -r requirements.txt
    - AWS 해당 인스턴스 보안 port 열기
        - 보안 → 보안그룹 선택
        - 인바운드 규칙 편집 → 규칙 추가
            - 사용자지정 TCP → port : 8000 → 0.0.0.0/0
    - 서버 실행
        - python3 manage.py runserver 0.0.0.0:8000
    - IP를 통해 접속하기

### Django 추가작업

- 수정파일
    - pybo/templatetags/pybo_filter.py
    - templates/pybo/question_detail.html
    - pybo/urls.py
    - pybo/views.py
- AWS 인스턴스를 재시작 했기 때문에 IP가 변경되었음
    - git remote 연결 해제
        - git remote remove origin
    - git remote 등록
        - git remote add origin ssh://ubuntu@{IP}:/home/ubuntu/repos