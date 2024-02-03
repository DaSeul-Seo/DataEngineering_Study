### IP

- netmask 다르면 통신이 안된다.
- 192.168.56.000/24 (ip addr)
    - 24 : netmask 1의 갯수
- 192.168.56.1
    - 맨 뒤의 1은 Gateway 이다
- 192 : C 클래스
- 172 : B 클래스

### MariaDB

- 콘솔 접속
    
    ```bash
    mysql -u {계정} -p {비밀번호}
    ```
    

### VPN

- 가상망

---

### 폴더구조 설명

- bin
    - 명령어가 들어가 있는 폴더
    - cp, mkdir, rmdir, mv, rm, cat 등
- boot
    - 부팅관련 파일이나 커널 등 시스템 부팅 시 필요한 파일이 들어있는 디렉토리
- dev
    - 하드 디스크, CD-ROM, 터미널 등과 같이 실제 존재하는 물리적인 장치 등을 파일화 하여 관리하는 디렉토리
- etc
    - 시스템 환경 설정 파일 및 부팅 관련된 스트립트 파일이 있는 디렉토리
- home
    - 개인 사용자 디렉토리
- lib
    - 각 라이브러리가 저장되어 있는 디렉토리
- mnt
    - CD-ROM, 플로피 디스크, 하드 디스크, 네트워크 파일 시스템 등 마운트할 떄 포인터가 되는 디렉토리
- opt
    - 응용프로그램들의 설치를 위해 사용되는 디렉토리
- proc
    - 가상 파일 시스템으로 시스템에서 운영되고 있는 다양한 프로세서의 상태정보, 하드웨어 정보, 기타 시스템 정보 등을 담고 있는 디렉토리
- tmp
    - 임시저장 디렉토리
    - 소켓 파일, 프로세스 작업할 때 임시로 생성되는 파일을 저장하는 디렉토리
- usr
    - 시스템 운영에 필요한 명령, 응용프로그램들이 위치하는 디렉토리
    - mysql, php 등이 usr 하위 디렉토리에 설치된다.
- var
    - 시스템 운영 로그 파일 등과 같은 가변적인 데이터를 보관하는 디렉토리
- media
    - 이동식 장치인 cdrom, floppy 등을 마운트하기 위해 제공되는 디렉토리
- srv
    - 사이트에서 생성되는 데이터를 저장하는 디렉토리
    - FTP, WWW, CSV 등과 같은 서비스에 의해 생성되는 데이터를 저장하는 디렉토리
- sys
    - 커널에서 사용되는 가상 파일 시스템인 sysfs에서 사용되는 디렉토리
    - USB 처럼 hot plug 하드웨어 정보를 가지고 있는 디렉토리
- lib64
    - 64비트 기반의 라이브러리 관련 디렉토리
- run
    - 부팅 이후 동작 중인 프로세스의 런타임 데이터를 저장하고 있는 디렉토리
    - 프로세스 ID 파일과 락 파일이 생성되고 재부팅하면 다시 생성된다.

---

- 127.0.0.1 = Loopback IP

### vim Key

- G : 페이지 끝
- gg : 페이지 처음
- :/ : 찾기
    - n : 다음찾기
    - N : 이전찾기

### 데이터 DB에 넣기

1. pip install pymysql
    1. python에서 DB 연결학 위해 필요한 패키지

```python
import pandas as pd
import pickle 

# 파일 불러오기
with open("./starbucks.pkl", "rb") as f:
    star_df = pickle.load(f)
```

```python
pip install pymysql
```

```python
import pymysql

# sql 연결
conn = pymysql.connect(host="{IP}", user="encore", password="1q2w3e!", db="mydata")
# cursor : SQL 쿼리를 실행하고 그 결과를 가져오는 데 사용
cursor = conn.cursor()
cursor.execute("show tables")
```

```python
sql = """create table starbucks (
    s_code int(10) not null COMMENT '',
    s_name varchar(30) not null COMMENT '',
    tel varchar(30) not null COMMENT '',
    fax varchar(30) not null COMMENT '',
    sido_code int not null COMMENT '',
    sido_name varchar(10) not null COMMENT '',
    gugun_code varchar(10) not null COMMENT '',
    gugun_name varchar(10) not null COMMENT '',
    addr varchar(40) not null COMMENT '',
    defaultimage varchar(50) not null COMMENT '',
    open_dt varchar(10) not null COMMENT '',
    doro_address varchar(40) not null COMMENT '',
    lat float not null COMMENT '',
    lot float not null COMMENT '',
	PRIMARY KEY(s_code)
);"""
cursor.execute(sql)
```

```python
cursor.execute("ALTER TABLE mydata.starbucks MODIFY COLUMN defaultimage varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL;")
cursor.execute("ALTER TABLE mydata.starbucks MODIFY COLUMN addr varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL;")
cursor.execute("ALTER TABLE mydata.starbucks MODIFY COLUMN doro_address varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL;")
```

```python
sql_insert = "INSERT INTO starbucks VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
cursor.execute(sql_insert , star_df.iloc[0,:].tolist())
# DB에 직접 적용
conn.commit()
```

```python
star_df.loc[star_df['gugun_name'].isnull(), 'gugun_name'] = "None"
```

```python
for idx, row in star_df.iterrows():
    try:
        cursor.execute(sql_insert, row.tolist())
    except Exception as e:
        print(e)

# DB에 직접 적용
conn.commit()
```

```python
# 쿼리 결과를 데이터프레임으로 만들어준다
pd.read_sql_query("select * from starbucks", con=conn)
```

2. pip install sqlalchemy
    1. ORM
    2. JPA 처럼 사용
    3. 서버에 설치 - mysqlclient 의존성 때문
        
        ```bash
        sudo apt-get install python3-dev default-libmysqlclient-dev build-essential pkg-config
        pip install mysqlclient
        ```
        
    4. 데이블 만들고 데이터 넣기
        
        ```bash
        import sqlalchemy
        
        user = "encore"
        password = "1q2w3e!"
        host = "{IP}"
        port = "3306"
        database = "mydata"
        
        engine = sqlalchemy.create_engine(f"mysql://{user}:{password}@{host}:{port}/{database}")
        ```
        
        ```bash
        star_df.to_sql(name="star2", if_exists="replace", con=engine)
        ```
        

---

- docker는 linux only이기에 윈도우에 실제 설치되어 있는 곳은 wsl에 설치된다.

---

- 컴퓨터 이름 변경
    - sudo hostnamectl set-hostname client
- 계정 추가
    - sudo adduser hadoop
- hsdoop에 sudo 권한 주기
    - sudo visudo
        - User privilege specification에 계정 추가
- 계정 전환
    - su {계정}

### java jdk 설치

- **sudo apt update**
- **sudo apt install openjdk-8-jdk**
- 프로그램 위치 확인
    - whereis {프로그램명}
- 실제 실행되는 프로그램 위치 확인
    - which {프로그램명}
- JAVA_HOME 설정
    
    ```bash
    vim ~/.bashrc
    export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
    source ~/.bashrc 
    echo $JAVA_HOME
    ```
    

---

### 하둡

- 저장소
- 클러스터 → 그룹
- google 오픈소스
- 하둡 설치
    
    ```bash
    cd ~ 
    wget https://dlcdn.apache.org/hadoop/common/hadoop-3.3.6/hadoop-3.3.6.tar.gz
    ```
