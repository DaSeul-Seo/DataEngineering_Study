### Docker

- 도커 (회사명) = 가상머신
    - 컨테이너에 여러 기능을 추가함으로써 애플리케이션을 컨테이너로서 좀더 쉽게 사용 가능
    - 환경구축의 편리함
        - 같은 코드, 같은 환경
        - 발전한 것이  dev-ops
            - 개발과 동시에 운영이 가능
            - 에어플로우, 젠킨스 등
    - GO언어로 작성 → 구글에서 만든 언어
    - 컨테이너를 관리해주는 소프트웨어
    - 리눅스 기능
        - 네임스페이스, chroot, cgroup
    - ⭐ 리눅스 기능으로 만든 것이 도커 ⇒ 가상머신과의 차이점
        - 도커는 리눅스에서만 가능
- 가상머신
    - 가상머신은 해석해주는 중간단계가 필요하다. (하이퍼바이저)
        - 느리다
        - 성능 손실
    - 배포하기 위한 이미지로 만들었을때 이미지의 크기가 큰 단점이 존재
- 컨테이너
    - 독립된 공간과 시스템 자원을 할당받아 사용
    - 리눅스의 자체 기능인 chroot, 네임스페이스, cgroup을 사용
    - 프로세스 단위의 격리 환경을 만든기 때문에 성능 손실이 거의 없음
    - 컨테이너에 필요한 커널은 호스트의 커널을 공유해 사용
        - 게스트 운영체제 : 하이퍼바이저에 의해 작동되는 운영체제
        - 호스트 운영체제 : 게스트 운영체제 위에서 작동되는 운영체제
    - 컨테이너 안에는 애플리케이션을 구동하는데 필요한 라이브러리 및 실행파일만 존재
    - 컨테이너로 만든 이미지는 가상머신에 비해 매우 작음
    - 작업했던 내용을 보존하고 싶으면 볼륨에 저장하면 된다.
    - 망가져도 지우고 다시 만들면 된다.
- 장점
    - 개발과 배포가 편리
        - 개발환경과 배포환경이 동일
    - 독립성, 확장성 높음
    - 모놀리스 : 일체형
    - 마이크로서비스 : 분리형
    - 컨테이너는 마이크로서비스 구조에서 많이 사용되는 가상화 기술
        - 기존 모놀리스서비스에서 마이크로서비스로 바꾸고 싶어한다.
        - 컨테이너에 담아서 분리시키고 그것을 가지고 마이크로서비스로 만든다.
        - 마이크로서비스에서 넷플릭스가 유명
- 도커 엔진
    - 사용하는 단위는 이미지
- 도커 레이어
    - 이미 있는 것은 냅두고 없는 것만 다운받는다.
- 쿠버네티스
    - 수많개의 컨테이너를 관리해주는 오케스트레이션
    - 컨테이너 증폭 / 감축
- 도커컴포즈
    - 도커 파일은 하나의 이미지만 담는다.
    - 도커 파일은 합치는 것이 도커 컴포즈
- 워드프레스
    - 간단한 웹 사이트
- SageMaker
    - 하둡, S3, GPU 등 기능이 많다
    - 아마존에서 제공하는 주피터
    - 클라우드 기반 분석
    - EMR :  하둡
    - AutoML 기능 있음
        - 최고의 파라미터를 찾아준다
- Lambda
    - 서버리스 서비스
    - 학습 모델을 만들면 바로 서빙이 가능
    - SageMaker에서 모델을 학습하고 Lambda에서 서빙
    - crontab을 사용안할거면 Lamda에서 하면된다.
    - Max 15분밖에 안돈다.
- 쿠버플로우
    - 쿠버네티스가 가동이 되어있어야 한다.
    - Hadoop 구성보다 설정이 어렵다.
    - AutoML 기능이 있음

### Docker 실습

- EC2에서 작업
- AWS 인스턴스 시작
- docker 설치
    
    ```bash
    sudo curl -sSL get.docker.com | sh
    
    # docker 설치 확인
    docker
    ```
    
- docker 실행
    - sudo docker run -it ubuntu:14.04
        - 우리가 지정한 이미지 명(ubuntu:14.04)을 찾아서 docker hub에서 받아온다
        - ubuntu, yolo 등
        - ubuntu:14.04 : 컨테이너를 생성하기 위한 이미지 명
        - -it : 컨테이너와 상호 입출력하는 옵션
            - 키보드, 마우스, 모니터
- docker 권한 ⇒ sudo 명령어를 안써도됨
    
    ```bash
    sudo usermod -aG docker $USER
    # 유저 그룹을 수정할거야 USER(현재 계정)을 그룹( docker)에 추가해줘
    ```
    
- 컨테이너 ID는 중복불가
- 이름 지정해서 docker 설치
    - docker run -it --name test ubuntu:14.04
- 도커 컨테이너 명령어
    
    ```bash
    # 도커 컨테이너 상태 확인 : a는 실행도고 있는 것과 죽은 것 다 보여줌
    docker ps -a
    
    # 컨테이너 종료하지 않고 나가기
    ctrl + p, q
    
    # 컨테이너 다시 들어가기
    docker attach {컨테이너 ID}
    
    # 컨테이너 정지
    docker stop {컨테이너 ID}
    
    # 컨테이너 시작
    docker start {컨테이너 ID}
    
    # docker 이미지 확인
    docker images
    
    # 컨테이너 삭제
    docker tm {컨테이너 ID}
    
    # 컨테이너 전체 삭제
    docker rm $(docker ps -a -q)
    ```
    
- 컨테이너 생성
    - ⭐ 포트 바인딩
        - docker run -it --name webserver -p 90:80 ubuntu:14.04
        - 호스트 90번 포트와 연결
        - -p : 외부에서 접근하겠다
        - 90번을 통해 80번 포트로 접근할 수 있다
        - aws 보안에서 90번 포트 열기
- 도커 내 서버 설치
    
    ```bash
    apt update
    apt install apache -y
    ```
    
- 서버 시작 및 상태 확인
    
    ```bash
    service apache start
    service apache status
    ```
    
- mysql 도커 설치
    - db 컨테이너
    
    ```bash
    docker run -d --name wordpressdb -e MYSQL_ROOT_PASSWORD=encore -e MYSQL_DATABASE=wordpress mysql:5.7
    ```
    
    - -d : 백그라운드에서 실행
    - -e : 파라미터 전달
    - wordpress 서버 컨테이너
    
    ```bash
    docker run -d -e WORDPRESS_DB_USER=root -e WORDPRESS_DB_PASSWORD=encore -e WORDPRESS_DB_NAME=wordpress --name wordpress --link wordpressdb:mysql -p 90:80 wordpress
    ```
    
    - -- link : 실행하면서 mysql 호스트를 찾을 건데 우리가 지정한 명으로 찾겟다
        - wordpressdb:mysql
- DB 데이터 공유 (백업)
    
    ```bash
    docker run -d --name encoredb -e MYSQL_ROOT_PASSWORD=encore -e MYSQL_DATABASE=encore -p 3306:3306 -v /home/ubuntu/data:/var/lib/mysql mysql:5.7
    ```
    
    - -v :  공유옵션
- 컨테이너 명령어 실행해서 데이터베이스 들어가기
    - docker exec -it encoredb bash
    - mysql -u root -p encore
- 기존 데이터 베이스 옮기기
    - windows에서 서버로 파일 옮기기
        - scp ./encore_backup.sql ubuntu@{IP}:/home/ubuntu
    - docker내 db에 옮기기
        - docker cp encore_backup.sql encoredb:/root/
- docker 내 데이터베이스 복원하기
    - cd ~
    - mysql -uroot -pencore encore < encore_backup.sql
- docker를 내리고 실행
    
    ```bash
    # 데이터 확인 뒤
    docker stop encoredb
    docker rm encoredb
    
    docker run -d --name encoredb -e MYSQL_ROOT_PASSWORD=encore -e MYSQL_DATABASE=encore -p 3306:3306 -v /home/ubuntu/data:/var/lib/mysql mysql:5.7
    ```
    
- jupyter 컨테이너 설치
    - docker run -p 8888:8888 --name myjupyter -e JUPYTER_ENABLE_LAB=yes -v /home/ubuntu/jupyter:/home/jovyan/work --restart always jupyter/all-spark-notebook

### AWS CLI

- AWS CLI 설치
    
    [최신 버전의 AWS CLI 설치 또는 업데이트 - AWS Command Line Interface](https://docs.aws.amazon.com/ko_kr/cli/latest/userguide/getting-started-install.html)
    
    - 설치 확인 aws --version
- AWS CLI 환경
    - AWS에 인터넷으로 들어가지 않고 CLI 환경에서 상태확인 및 실행, 중지가 가능.
- aws configure
    
    ```bash
    AWS Access Key ID : 엑셀에 있는
    AWS Secret Access Key :
    Default region name : ap-northeast-2
    Default output format :
    ```
    
- aws ec2 describe-instances
    - 현재 갖고 있는 인스턴스가 나온다
    
    ```bash
    aws ec2 describe-instances --query 'Reservations[*].Instances[*].[InstanceId, State.Name, Tags[?Key==`Name`].Value | [0], PublicIpAddress, PrivateIpAddress, InstanceType, KeyName, Platform,InstanceType]' --output table
    ```
    
    - filter
    
    ```bash
    aws ec2 describe-instances --query 'Reservations[*].Instances[*].[InstanceId, State.Name, Tags[?Key==`Name`].Value | [0], PublicIpAddress, PrivateIpAddress, InstanceType, KeyName, Platform,InstanceType]' --output table | findstr {찾을내용}
    ```
    
- aws cli 사용해서 인스턴스 시작, 중지, 삭제
    
    ```bash
    # 시작
    aws ec2 start-instances --instance-ids <instance-id>
    # 중지
    aws ec2 stop-instances --instance-ids <instance-id>
    # 삭제
    aws ec2 terminate-instances --instance-ids <instance-id>
    ```
    

### airflow

- airbnb 회사에서 만든 것
- 스케쥴을 만드는 프로그램
- 단방향으로 진행
- crontab으로 하기 어려워서 만든 프로그램
- airflow 설치
    
    ```bash
    pip install apache-airflow==2.8.1 --constraint https://raw.githubusercontent.com/apache/airflow/constraints-2.8.1/constraints-3.10.txt
    ```
    
- airflow 실행
    
    ```bash
    airflow standalone
    ```
    
- AWS 에서 8080 포트 열기
- 해당 아이디와 비밀번호는 나온다.
- 윈도우에서는 설치가 불가능하다
    - 리눅스
- 무조건 쓰는 것은 좋지 않다
- 모든 과정을 연결해주는 것(파이프라인)이 airflow
- Kafka
- Redis
- Celery
    - 메세지 서버와 API 서버를 연동해준다


### 💡 Reference

- 도커 설치
    - https://jwkim96.tistory.com/114
- 도커 실행
    - https://deepmal.tistory.com/22
- 도커 허브
    - https://hub.docker.com/