### cmd에서 파일 옮기기

- scp [대상파일] [계정]@[ip]:[경로]

### Server

- 모델 서빙을 위한 가상환경
    - conda create --name serving python=3.9
    - serving  이라는 이름으로 가상환경을 만들거고 python=3.9 버전이야
- 가상환경 로그인
    - conda activate serving
- 필요한 pip 리스트 다운
    - pip install -r requirements.txt
- 7zip 다운
    - sudo apt install p7zip
- hdf5
    - 빅데이터에서 많이 쓰는 데이터 타입 (텐서플로우에서 많이 씀)
- temp 폴더 만들고 이동하기
    - mkdir temp && mv ./dog_model_service.7z ./temp
    - cd temp
- 압축풀기
    - 7zr x dog_model_service.7z
- 모델 서빙
    - python manage.py runserver 0.0.0.0:8080
    - 에러 날 건데 tensorflow 때문에 발생
- gRPC
    - 구글에서 만은 api

### Hadoop

- 분산되서 저장되는 저장소
    - 컴퓨터의 갯수가 많아야 하는 단점이 있다.
- 회사는 각각의 부서에 디비가 있다.
    - 인사(HR), 제조(PP), 영업(SD), 마케팅(CRM)
- 구글 기반으로 분산 저장 개념
- MapReduce 처리 기술을 개발하는데 많은 영감을 줌
- 저렴한 하드웨어로 매우 안정적인 시스템을 구성할 수 있음
- 핵심기술
- 분산 파일 시스템
- 리소스 관리자와 스케줄러
- 분산 데이터 처리 프레임워크
- HDFS : 하둡의 저장소
- MapReduce = python의 map / Reduce = 집계
    - 처리된 데이터를 집계해서 결과를 낸다.
- Pig
    - ETL 작업
    - Spark로 대체 가능
- Ambari
    - 클러스터 전체 모니터링 툴
- Spark
- Sqoop
    - 마이그레이션 툴
    - Spark로 대체 가능
- Kafka
    - Java 기반
    - 데이터 수집
    - 대량의 데이터를 수집해 Hadoop 클러스터로 전송

### ssh key 만들기/비밀번호 안치고 로그인설정

- 로컬에서 ssh 만들어 서버 로그인시 비밀번호 묻지 않도록 하기
- cmd
    - ssh-keygen -t rsa
- server (hadoop)
    - 로컬에서 만든 id_rsa.pub 내용 복사에서 서버에 authorized_keys에 넣기 (update)
    - 권한 바꾸기
        - chmod 600 authorized_keys
        - cat id_rsa.pub >> authorized_keys
- test
    - ssh localhost

k-ict