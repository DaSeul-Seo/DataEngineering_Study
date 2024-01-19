- 실시간(nohup) 백그라운드(&) 실행
    - nohup으로 실행할 쉘 스크립트 파일은 현재 퍼미션이 755 상태여야 한다.
    - nohup 끄기
        - kill -9 PID

```bash
nohup jupyter -lab --ip=0.0.0.0 &
```

- ssh RSA key 발급
    - 아래 명령어를 실행하면 ~/.ssh폴더가 생기고 key 파일이 생긴다.
    - id_rsa(600) : private key
    - id_rsa.pub(644) : public key

```bash
ssh-keygen -k rsa
```

- cat id_rsa.pub > authorized_keys
    - id_rsa.pub를 authorized_keys 파일에 주입시킨다.

```bash
> : 새로 파일은 만들어서 내용을 주입
>> : 없으면 새로 만들고 있으면 update
```

- os.listdir
    - 폴더에 있는 파일 리스트를 뿌려준다.
- os.walk
    - 그 파일에 모든 파일을 보여준다

---

### 데이터 가져오기

1. 스타벅스
    1. 전국의 스타벅스 매장 데이터 가져오기

```python
**payload= {
"in_biz_cds" : "0",
"in_scodes" : "0",
"ins_lat" : "37.56682",
"ins_lng" : "126.97865",
"search_text" : "",
"p_sido_cd" : "01",
"p_gugun_cd" : "",
"in_distance" : "0",
"in_biz_cd" : "",
"isError" : "true",
"searchType" : "C",
"set_date" : "",
"all_store" : "0",
"T03" : "0",
"T01" : "0",
"T27" : "0",
"T12" : "0",
"T09" : "0",
"T30" : "0",
"T05" : "0",
"T22" : "0",
"T21" : "0",
"T10" : "0",
"T36" : "0",
"T43" : "0",
"T48" : "0",
"Z9999" : "0",
"P10" : "0",
"P50" : "0",
"P20" : "0",
"P60" : "0",
"P30" : "0",
"P70" : "0",
"P40" : "0",
"P80" : "0",
"whcroad_yn" : "0",
"P90" : "0",
"P01" : "0",
"new_bool" : "0",
"iend" : "1000",
"rndCod" : "EMP6WZ23VQ",}**
```

```python
import requests

# 전국 스타벅스 매장리스트 가져올 url
url = "https://www.starbucks.co.kr/store/getSidoList.do"

# 해당 url을 POST 요청을 보내서 전국 시/도에 스타벅스 정보를 가지고 온다.
r = requests.post(url)
print(r.status_code) # 요청코드 확인
print("------------")

# 요청 결과의 json화 list를 가지고 온다.
# 요청 결과의 sido_nm과 sido_cd를 확인
for x in r.json()['list']:
    print(x['sido_nm'], x['sido_cd'])
```

```python
import requests
import pandas as pd
from tqdm import tqdm

# 전국의 시도 url
url_cd = "https://www.starbucks.co.kr/store/getSidoList.do"
r = requests.post(url_cd)
# 스타벅스 지역의 정보 url
url_place = "https://www.starbucks.co.kr/store/getStore.do?r=6DKBI0DUO3"

total = []
for x in tqdm(r.json()['list']):
    #print(x['sido_nm'], x['sido_cd'])
    # 시도 코드를 payload를 지역정보의 값에 넣는다.
    payload['p_sido_cd'] = x['sido_cd']
    # 지역정보를 가지고 온다.
    r = requests.post(url_place, data=payload)
    # 요청의 결과를 r.json()['list']에서 받아와 데이터프레임 형태로 변환
    # 해당 정보를 json화 => 리스트를 가지고 온다. => total에 append 한다.
    total.append(pd.DataFrame(r.json()['list']))

# total을 다 합친다.
starbuck_df = pd.concat(total)
```

```python
# 지점명 중복제거 후 확인
starbuck_df['s_name'].unique().size

# 컬럼을 생략하지 말고 확인하기
pd.set_option('display.max_columns', None)

# 데이터프레임에서 결측치가 일정 개수 이상인 열(column)들을 삭제하는 작업
star_df = starbuck_df.loc[:, ~(starbuck_df.isnull().sum() > 1800)].copy()

# DT가 포함된 셀
star_df['DT'] = star_df['s_name'].apply(lambda x : 'DT' in x)

# 시별로 DT 매장 갯수
star_df.groupby('sido_name')['DT'].sum().sort_index()
```

```python
# 스타벅스가 언제 오픈을 많이 했는지
from datetime import datetime

# zip() : 여러 개의 interable 객체를 묶어서 각 개체의 요소를 순서대로 묶어준다.
# range(7)과 "월화수목금토일"문자열을 한 쌍으로 묶어준다.
# dict() : zip()으로 생성된 튜플 시퀀스를 딕셔너리로 변환 (key-value)
dict(zip(range(7), "월화수목금토일"))

# open_df 컬럼을 datetime 타입으로 변환
star_df['open_df'] = pd.to_datetime(star_df['open_df'])

# 어느 요일에 가장 많이 오픈했는지 확인
star_df["open_dt"].apply(lambda x : dict(zip(range(7), "월화수목금토일"))[x.weekday()]).value_counts()
```

```python
from urllib import request
import os 

# 폴더 확인 - 확인해서 없으면 만든다
if os.path.isdir("./starbucks") == False:
    os.mkdir("./starbucks")

image_url = "https://www.starbucks.co.kr"
for idx, row in tqdm(star_df.iterrows()):
    img = row['defaultimage']
    # print(image_url + img)
    # print(image_url)
    # print(img)
		# 이미지 저장
    request.urlretrieve(image_url + img, f"./starbucks/{row['defaultimage'].split('/')[-1]}")
    # while(True):
    #     try:
    #         request.urlretrieve(image_url + img, f"./starbucks/{row['defaultimage'].split('/')[-1]}")
    #         break
    #     except:
    #         time.sleep(5)
```

### DB 설치

- apt update
    
    ```bash
    sudo apt update
    ```
    
- MariaDB 서버 설치
    
    ```bash
    sudo apt install mariadb-server
    ```
    
- MariaDB 서비스 확인
    
    ```bash
    sudo service mysql status
    ```
    
- MariaDB 초기설정
    
    ```bash
    sudo mysql_secure_installation
    # Disallow root login remotely? [Y/n] n 빼고 다 Y
    ```
    
    ```bash
    NOTE: RUNNING ALL PARTS OF THIS SCRIPT IS RECOMMENDED FOR ALL MariaDB
          SERVERS IN PRODUCTION USE!  PLEASE READ EACH STEP CAREFULLY!
    
    In order to log into MariaDB to secure it, we'll need the current
    password for the root user. If you've just installed MariaDB, and
    haven't set the root password yet, you should just press enter here.
    
    Enter current password for root (enter for none):
    OK, successfully used password, moving on...
    
    Setting the root password or using the unix_socket ensures that nobody
    can log into the MariaDB root user without the proper authorisation.
    
    You already have your root account protected, so you can safely answer 'n'.
    
    Switch to unix_socket authentication [Y/n] Y
    Enabled successfully!
    Reloading privilege tables..
     ... Success!
    
    You already have your root account protected, so you can safely answer 'n'.
    
    Change the root password? [Y/n] Y
    New password:
    Re-enter new password:
    Password updated successfully!
    Reloading privilege tables..
     ... Success!
    
    By default, a MariaDB installation has an anonymous user, allowing anyone
    to log into MariaDB without having to have a user account created for
    them.  This is intended only for testing, and to make the installation
    go a bit smoother.  You should remove them before moving into a
    production environment.
    
    Remove anonymous users? [Y/n] Y
     ... Success!
    
    Normally, root should only be allowed to connect from 'localhost'.  This
    ensures that someone cannot guess at the root password from the network.
    
    Disallow root login remotely? [Y/n] n
     ... skipping.
    
    By default, MariaDB comes with a database named 'test' that anyone can
    access.  This is also intended only for testing, and should be removed
    before moving into a production environment.
    
    Remove test database and access to it? [Y/n] Y
     - Dropping test database...
     ... Success!
     - Removing privileges on test database...
     ... Success!
    
    Reloading the privilege tables will ensure that all changes made so far
    will take effect immediately.
    
    Reload privilege tables now? [Y/n] Y
     ... Success!
    
    Cleaning up...
    
    All done!  If you've completed all of the above steps, your MariaDB
    installation should now be secure.
    
    Thanks for using MariaDB!
    ```
    
- MariaDB 들어가기
    
    ```bash
    mysql -uroot -pqhdkscjfwj1!
    ```