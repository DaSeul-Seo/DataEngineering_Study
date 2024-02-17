- Docker를 모니터링하는 프로그램
    
    ```bash
    docker run \
      --volume=/:/rootfs:ro \
      --volume=/var/run:/var/run:rw \
      --volume=/sys:/sys:ro \
      --volume=/var/lib/docker/:/var/lib/docker:ro \
      --publish=9000:8080 \
      --detach=true \
      --name=cadvisor \
      google/cadvisor:latest
    ```
    

### Docker 컨테이너를 이미지로 만들기

- Docker 이미지 만들기
    
    ```bash
    docker run -it --name commit_test ubuntu:14.04
    ```
    
- 컨테이너 안에서 작업했던 것을 이미지로 만든다.
- docker commit -a {’누가’} -m {’왜?= 설명’} {대상도커이미지} {새로운이미지명:TAG}
- 새로운이미지명:TAG ⇒ 콜론이 있으면 항상 최신버전이라고 인식함

```bash
docker commit -a 'encore' -m 'first commit' commit_test myimage:0.1
```

- 만든 이미지를 가지고 새로운 컨테이너 만들어서 들어가기
    
    ```bash
    docker run -it --name commit_test2 myimage:0.1
    ```
    
- 2에서 파일 만들기
    
    ```bash
    echo test_second >> second
    ```
    
- 2가지고 새로운 이미지 만들기
    
    ```bash
    docker commit -a 'encore' -m 'second image' commit_test2 myimage:0.2
    ```
    
- 이미지 히스토리 확인
    
    ```bash
    docker history myimage:0.2
    
    '''
    IMAGE          CREATED          CREATED BY                                      SIZE      COMMENT
    711f85a59b20   6 minutes ago    /bin/bash                                       168B      second image
    a5dfd95376a9   14 minutes ago   /bin/bash                                       49B       first commit
    13b66b487594   2 years ago      /bin/sh -c #(nop)  CMD ["/bin/bash"]            0B
    <missing>      2 years ago      /bin/sh -c mkdir -p /run/systemd && echo 'do…   7B
    <missing>      2 years ago      /bin/sh -c [ -z "$(apt-get indextargets)" ]     0B
    <missing>      2 years ago      /bin/sh -c set -xe   && echo '#!/bin/sh' > /…   195kB
    <missing>      2 years ago      /bin/sh -c #(nop) ADD file:276b5d943a4d284f8…   196MB
    '''
    ```
    
- 이미지 지우기
    - 연결된 컨테이너가 살아있거나 존재하면 이미지 지우기 불가능
    - 강제 지우기 : -f
        - docker rmi -f myimageL:0.1
    
    ```bash
    # docker 전부 정지
    docker stop $(docker ps -a -q)
    # docker 전부 삭제
    docker rm $(docker ps -a -q)
    # docker image 삭제
    docker rmi myimage:0.1
    ```
    
- Docker 컨테이너 생성
    
    ```bash
    docker run -it --name mycont ubuntu:22.04
    ```
    
    ```bash
    # 컨테이너 내부 작업
    apt update
    # 필요 소프트웨어 설치
    apt install software-properties-common -y
    # python 설치
    apt install python3.11
    # pip 설치
    apt install python3-pip
    ```
    
- 참고 - 배포판마다 설치 명령어 차이
    - ubuntu -> apt
    - redhat(centos) -> yum, dnf
    - alpine -> apk
- Docker 이미지 만들기
    
    ```bash
    docker commit -a 'encore' -m 'project images' mycont project:0.1
    ```
    

### Docker 이미지 추출

- docker save -o {파일명.확장자} {추출할 이미지명}

```bash
docker save -o project.tar project:0.1
```

- 생성한 tar 파일 빼오기
    - docker export -i {파일명}
    
    ```bash
    docker export -o project.tar
    ```
    
- tar 파일을 이용해 이미지 생성
    - docker load -i {파일명}
    
    ```bash
    docker load -i project.tar
    ```
    

### Dockerfile 만들기

- 도커 이미지 설정을 가지고 한번에 도커 이미지를 생성한다.
- 레시피를 가지고 도커 이미지를 생성한다.
- Dockerfile 만들 폴더 만들기
    
    ```bash
    mkdir ~/mydocker
    ```
    
- 임의 파일 만들기
    
    ```bash
    echo "hi" >> test.html
    ```
    
- Dockerfile 만들기
    
    ```bash
    
    FROM ubuntu:14.04
    MAINTAINER jungwoon
    LABEL "purpose"="pratice"
    RUN apt-get update
    RUN apt-get install apache2 -y
    ADD test.html /var/www/html
    WORKDIR /var/www/html
    RUN ["/bin/bash", "-c", "echo hello >> test2.html"]
    EXPOSE 80
    CMD apachectl -DFOREGROUND
    ```
    
    - FROM
        - 기본이 될 이미지
    - MAINTAINER
        - 이미지를 생성한 개발자의 정보 (1.13 이후 사용 x) ⇒ 생량 가능
    - LABEL
        - 이미지에 메타데이터를 추가 (key-value 형테) ⇒ 생략가능
    - ARG
        - docker build 명령을 사용해 빌더에 전달할 수 있는 변수 정의
        - --build-arg=
        - 사용자에게 프린트 될 내용이 필요 시 사용
    - RUN
        - 보통 소프트웨어 설치 시 사용
        - 내부에서 실행하는 명령
    - ADD
        - 파일을 추가, 파일은 Dockerfile이 위치한 디렉토리에서 가져옴
    - WORKDIR
        - 디렉토리 이동, cd 명령어와 동일
    - EXPOSE
        - -p 옵션을 같이 써야 작동됨
        - Dockerfile의 빌드로 생성된 이미지에서 열어줄 포트
    - CMD & ENTRYPOINT 둘 중 하나는 설정이 되어있어야 함.
        - CMD
            - 컨테이너가 실행될 때 실행될 명령어
            - docker run과 유사하지만 컨테이너가 인스턴스화 된 후에만 실행됨
            - 여러 개의 CMD를 작성해도 마지막 하나만 처리됨
        - ENTRYPOINT
            - 컨테이너가 실행될 때 실행될 명령어
            - --entrypoint=
                - CMD 는 출력하지 않고 바로 들어감
                - ENTRYPOINT는 출력 후 들어감
            
            ```bash
            docker run -it {} -- name test ubuntu:14.04
            docker run -it --entrypoint="echo" -- name test ubuntu:14.04
            ```
            
    - ENV
        - 이미지 안에 각종 환경 변수를 지정하는 경우 작성
            - 자바 홈 디렉토리
            - mysql 디렉토리
- Dockerfile 실행하기
    - docker build -t {생성할 이미지명:TAG} .
    
    ```bash
    docker build -t encore:0.1 .
    ```
    
- Docker 이미지 확인
- Docker 컨테이너 생성
    
    ```bash
    docker run -it -d -p90:80 --name myweb encore:0.1
    ```
    
- Web에서 확인
    - IP:90
    - IP:90/test.html
    - IP:90/test2.html

### Docker Compose

- 다수의 음식을 만들 수 있는 레시피
    - 각각의 파일이 독립적
- 다운로드를 따로 받아야 한다.
    
    ```bash
    sudo curl -L "https://github.com/docker/compose/releases/download/1.27.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    ```
    
- 실행권한 주기
    
    ```bash
    sudo chmod +x /usr/local/bin/docker-compose
    ```
    
- 링크 걸기
    
    ```bash
    sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
    ```
    

- 우리가 만든 게시판  올리기
- 폴더 만들기
    
    ```bash
    mkdir ~/django
    ```
    
- Docker compose 압축파일 가져와서 풀기
    
    ```bash
    # unzip 설치
    sudo apt install unzip 
    
    cd ~/django
    unzip docker_compose_encore.zip
    ```
    
- docker-compose.yml
    
    ```bash
    version: '3'
    
    services:
      nginx:
        build: ./nginx
        volumes:
          - static_volume:/usr/scr/app/static
        ports:
          - 80:80
        depends_on:
          - web
      web:
        build: .
        command: gunicorn config.wsgi:application --bind 0.0.0.0:8000
        volumes:
          - static_volume:/usr/src/app/static
          - ./:/usr/src/app/
        ports:
          - 8000:8000
    volumes:
      static_volume:
    ```
    
    - depends_on
        - 서비스 띄우는 순서 정의
        - web을 먼저 구동 후 nginx 구동
    - 만든 서비스 올리기
        - settings.py
            - db 변경
            - allow_host 추가 '*'
            - debug False로 변경
        - Docker compose 올리기
            - docker-compose up
        - Docker compose 내리기
            - docker-compose down
        - Docker compose 빌드
            - docker-compose build
            - docker-compose.yml 파일 변경시
    
    ### Airflow설정
    
- 폴더 만들기
    
    ```bash
    mkdir ~/airflow_work
    mkdir ~/airflow_work/dags
    ```
    
- 파일 옮기기
- Docker-compose 실행
    - 백그라운드로 실행
    
    ```bash
    docker-compose up -d
    ```
    
- airflow
- download_rocket_launches 토글을 켜야 스케줄러가 시작됨
- 주식 예시
    - 파일 옮기기
        - docker-compose.yml
        
        ```bash
        version: '3.7'
        
        # ====================================== AIRFLOW ENVIRONMENT VARIABLES =======================================
        x-environment: &airflow_environment
          - AIRFLOW__CORE__EXECUTOR=LocalExecutor
          - AIRFLOW__CORE__LOAD_DEFAULT_CONNECTIONS=False
          - AIRFLOW__CORE__LOAD_EXAMPLES=False
          - AIRFLOW__CORE__SQL_ALCHEMY_CONN=postgresql://airflow:airflow@postgres:5432/airflow
          - AIRFLOW__CORE__STORE_DAG_CODE=True
          - AIRFLOW__CORE__STORE_SERIALIZED_DAGS=True
          - AIRFLOW__WEBSERVER__EXPOSE_CONFIG=True
        
        x-airflow-image: &airflow_image apache/airflow:2.0.0-python3.8
        # ====================================== /AIRFLOW ENVIRONMENT VARIABLES ======================================
        services:
          postgres:
            image: postgres:12-alpine
            environment:
              - POSTGRES_USER=airflow
              - POSTGRES_PASSWORD=airflow
              - POSTGRES_DB=airflow
            ports:
              - "5432:5432"
        
          init:
            image: *airflow_image
            depends_on:
              - postgres
            environment: *airflow_environment
            entrypoint: /bin/bash
            command: -c 'airflow db init && airflow users create --username admin --password admin --firstname Anonymous --lastname Admin --role Admin --email admin@example.org'
        
          webserver:
            image: *airflow_image
            restart: always
            depends_on:
              - postgres
            ports:
              - "8080:8080"
            volumes:
              - logs:/opt/airflow/logs
            environment: *airflow_environment
            command: webserver
        
          scheduler:
            image: *airflow_image
            restart: always
            depends_on:
              - postgres
            volumes:
              - logs:/opt/airflow/logs
              - ./dags:/opt/airflow/dags # 로컬의 dags와 컨테이너 내 /opt/airflow/dags와 링크
            environment: *airflow_environment
            command: scheduler
        
        volumes:
          logs:
        ```
        
        - dags