- 강사님 코드

```python
from bs4 import BeautifulSoup as BS
import requests
import csv, os
# 선수 프로필 정보 
url = "https://www.koreabaseball.com/Record/Player/PitcherDetail/Basic.aspx?playerId={}"
if os.path.isdir("./kbodata") == False:
  os.mkdir("./kbodata")
for id_ in playid[:]:
  #print(url.format(x))
  bs = BS(requests.get(url.format(id_)).text)
  rt = [x.text.split(":")[-1].strip() for x in bs.find("div", class_="player_basic").findAll("li")]
  try:
      if rt[-3].find("달러") >= 0 :
          rt[-3] = (int(rt[-3].replace("달러", "")) * 1320 ) / 10000
      else:
          rt[-3] = int(rt[-3].replace("만원", ""))
  except:
      pass
  f = open(f"./kbodata/{id_}.csv", "w", encoding='utf-8')
  csv_writer = csv.writer(f)
  csv_writer.writerow(rt)
  f.close()
```

- 팀 컬럼 추가한 csv 파일 새로 만들기
    - server에서 .py 파일 만들기

```python
import os

# 폴더 생성
if os.path.isdir("./kbo_trans") == False:
  os.mkdir("./kbo_trans")

# 기존 csv 파일을 읽어와서 데이터 추가 후, 다른 파일로 저장
for file in os.listdir("./kbo/"):
	# csv 파일을 연다.
  f1 = open("./kbo/{}".format(file), "r", encoding='utf-8')
	# csv 파일을 읽는다.
  data = f1.read()
	# 기존 정보에 데이터 추가 (성수 ID)
  data = file.split(".")[0] + "," + data  
	# 새로 파일 저장
  with open("./kbo_trans/{}".format(file), "w") as f2:
    f2.write(data)
```

- {선수 ID, 팀} 파일 만들기 = kbo_master.csv
- kbo & kbo_master.csv 데이터 넣기

```python
hdfs dfs -mkdir /kbo
hdfs dfs -mkdir /kbo_master

hdfs dfs -put *.csv /kbo
hdfs dfs -put ./kbo_master.csv /kbo_master
```

- hive 작업

```sql
-- 테이블 생성 (kbo) 후, 데이터 넣기
CREATE EXTERNAL TABLE IF NOT EXISTS kbo (
    kboid	              STRING,
    PlayerName       STRING,
    PlayerId STRING,
    Birth	             STRING,
    Position STRING,
    Height_Weight STRING,
    Career STRING,
    Contact STRING,
    Salary FLOAT,
    Draft	             STRING,
    DebutYear STRING)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
LOCATION '/kbo';
```

```sql
-- 테이블 생성 (kbo_master)
CREATE EXTERNAL TABLE IF NOT EXISTS master(
	team STRING,
	kboid STRING)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';
```

- View 생성

```sql
CREATE VIEW kbo_view AS SELECT * FROM kbo WHERE kboid != '';
```

- kbo_master 데이터 넣기
    - 파일 자체를 가져와서 하이브 저장소에 저장

```sql
LOAD DATA INPATH '/kbo_master' OVERWRITE INTO TABLE master;
```

- 테이블 조인(kbo & kbo_master)해서 새로운 테이블(kbo_total) 만들기

```sql
CREATE TABLE kbo_total AS
SELECT
    a.kboid, 
    a.playername,
    a.playerid,
    a.birth,
    a.position,
    a.height_weight,
    a.career,
    a.contact,
    a.salary,
    a.draft,
    a.debutyear, b.team
FROM kbo_view a
JOIN master b 
ON b.kboid == a.kboid;
```

- 평균 연봉, 중위 연봉, 최대 연봉, 팀 뽑기

```sql
SELECT avg(salary) AS mean_salary, percentile(cast(salary AS BIGINT), 0.5) AS median_salary, 
max(salary) AS max_salary, team FROM kbo_total
GROUP BY team;
```

- 선수 BMI 지수 구하기

```sql
SELECT
    CAST(REGEXP_EXTRACT(SPLIT(height_weight, '/')[1],'[0-9]+',0) AS FLOAT) / 
    POWER(CAST(REGEXP_EXTRACT(SPLIT(height_weight, '/')[0],'[0-9]+',0) AS FLOAT) / 100, 2) AS bmi,
    playername,
    ROW_NUMBER() OVER (ORDER BY bmi DESC) AS rank
from kbo_view
order by bmi desc;
```