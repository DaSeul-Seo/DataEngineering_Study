- Hadoop
    - Data Lake
    - HDFS
    - Namenode
        - 장부
        - 메인 서버
    - SecondNode
        - 백업
    - 데이터 복제
        - 3개로 복사되게 설정
    - workers
        - 실제 데이터 저장하는 곳
        - datanode1 - 컴퓨터 사정상 Namenode와 datanode1 역할을 동시 실행
        - datanode2 - 컴퓨터 사정상 Secondnode와 datanode1 역할을 동시 실행
        - datanode3
    - 알고리즘
        - MapReduce - Java기반
    - 에코시스템
        - Hive → Java 코드를 사용하기 싫어서 사용
            - HiveQL (SQL)
            - 분석가, 엔지니어
            - Meta Data
                - MySQL (MariaDB) - 오픈소스이기에 사용했음
                - 다른 DB 사용 가능
        - 데이터 이관 (Migration)
            - Sqoop
        - ETL
            - 꺼내서 처리하고 적제 → 전처기
            - Pig
        - 분석
            - ⭐ Spark
                - Hive, Sqoop,  ETL 역할을 다 한다.
                - DataFrame
                - SQL
                - 실시간 처리
        - 실시간 처리
            - Kafka

---

### Database 백업

- mysqldump파일을 백업

```sql
sudo mysqldump -u {계정} -p {Database명} > {저장할 경로}.sql
----------
sudo mysqldump -u root -p hivedb > ~/workspace/seul/hi
vedb.sql
```

- 백업 테스트

```sql
-- DB 접속
mysql -u root -p

-- Database 확인
SHOW DATABASES;

-- Database 삭제
DROP DATABASE hivedb;

-- 빈 Database 생성
CREATE DATABASE hivedb;

-- 백업된 Database 복원 (DB 나와서)
mysql -u root -p hivedb < ~/workspace/seul/hivedb.sql
mysql -u {계정} -p {database명} < {백업 DB 파일 경로}.sql
```

- Airflow
    - 자동화 처리 프로그램

---

### Selenium

- 웹 개발이 끝나고 웹 테스트를 자동화 하려고 만든 프로그램
- 지금은 웹 크롤링 할 때 많이 사용
- Javascript로 되어 있는 사이트
    - Javascript 이후에 데이터가 생성되는 코드는 Selenium 사용해야 한다.

### Hadoop 실습

- KBO 선수들 리스트 가지고 온다.
- 선수 id로 csv 파일로 저장한다.
    - 용병들은 연봉을 한화로 변경해서 저장한다.
- hadoop에 넣는다.
- hiveQL로 구단별 평균연봉 구한다.

- 모듈 설치

```python
pip install selenium
pip install pandas
pip install tqdm
pip install requests
pip install bs4
```

- 모듈 import

```python
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup
from tqdm import tqdm
import pandas as pd
import requests
import time
import re
```

- selenium을 사용하기 위한 webdriver

```python
CHROME_DRIVER_PATH = './chromedriver-win64/chromedriver.exe'
service = Service(executable_path=CHROME_DRIVER_PATH)
options = webdriver.ChromeOptions()
driver = webdriver.Chrome(service=service, options=options)
```

```python
# 해당 url로 들어간다.
driver.get("https://www.koreabaseball.com/")

# 해당 태그를 클릭해서 들어간다.
driver.find_element(By.CSS_SELECTOR, "#popupVideo > img").click()
driver.find_element(By.CSS_SELECTOR, "#lnb > li:nth-child(3) > a").click()
driver.find_element(By.CSS_SELECTOR,"#cphContents_cphContents_cphContents_ddlTeam").click()
```

- KBO 선수들 playerid 가져오기

```python
pattern = re.compile("playerId=([0-9]+)")
playid = []
select_team = "#cphContents_cphContents_cphContents_ddlTeam > option:nth-child({})"
select_page = "#cphContents_cphContents_cphContents_ucPager_btnNo{}"
for x in range(2,12):
    for_1 = select_team.format(x)
    driver.find_element(By.CSS_SELECTOR, for_1).click()
    time.sleep(2)
    #playid.extend(pattern.findall(driver.page_source))
    for y in range(1,6):
        f2 = select_page.format(y)
        try:
            driver.find_element(By.CSS_SELECTOR, f2).click()
            time.sleep(1)
            playid.extend(pattern.findall(driver.page_source))
        except Exception as e:
            print (e)
        time.sleep(2)
```

- 선수들 id 길이 확인

```python
playid.__len__()
```

- 선수들 프로필 가져오기

```python
# 달러환율
dal = 1317

# 선수들 id를 하나씩 가져온다.
for pId in tqdm(playid):
	# 선수에 해당하는 url
  url = f"https://www.koreabaseball.com/Record/Player/PitcherDetail/Basic.aspx?playerId={pId}"
	# url 정보 가져오기
  r = requests.get(url)
  
  # BeautifulSoup을 사용하여 HTML 파싱
  soup = BeautifulSoup(r.text, "html.parser")
	
	# 결과를 담을 dictionary
  player = []

  # 클래스로 요소 선택하기 -> div태그 중 클래스명이 player_basic으로된 값 가져오기
  elements_with_class = soup.find_all("div", class_='player_basic')
  # 아이디로 요소 선택하기 -> 해당 id 값 가져오기
  teamName = soup.find(id="h4Team").text

  # 클래스 요소 반복
  for element in elements_with_class:
    playerDataList = element.text.strip().split("\n")
		# li로 된 선수정보 row 하나씩 꺼내기
    for playerData in playerDataList:
			# : 기준으로 자르기
      pl = playerData.split(": ")
			# 저장할 key
      key = pl[0]
			# 해당 값이 없는 경우가 있기 때문에 없으면 -로 기입
      value = "-" if len(pl) <= 1 else pl[1]
			
			# -면 다음 li
      if (value == "-"):
          continue
			# 만원이라는 단어가 포함되어 있으면 한글 없애고 0 붙이기
      elif ("만원" in pl[1]) :
          value = pl[1].replace("만원", "0000")
			# 달러라는 단어가 있으면 해당 값에 환율 값을 곱해서 저장
      elif ("달러" in pl[1]):
          value = pl[1].replace("달러", "")
          value = str(int(value) * dal)
			# 기본
      else:
          value = pl[1]
      # 해당 결과를 dictionary key-value 형태로 저장한다
      player.append({
          "Key": key,
          "Value": value
      })

  # dictionary를 dataframe 화 한다. => Key를 컬럼으로 해서
  df = pd.DataFrame(player).set_index('Key').T
  df["팀명"] = teamName
  df.to_csv(f"./player/{pId}.csv", index=False, encoding="utf-8-sig")
```