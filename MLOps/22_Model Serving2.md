※ 모델 서빙 localhost

※ request는 VM 서버

※ ⭐모델 서빙 시, 서버 ip를 입력해 request를 받을 수 있게 한다.

### Model - localhost

- Model에 맞는 환경 구축
    - python 3.9
    - requirements.txt
- 모델 api 실행
    
    ```bash
    python managy.py runserver 192.168.70.180:8000
    ```
    

### request - VM Server

- Server 에서 jupyter 실행

```bash
# 백그라운드 실행
nohup jupyter lab &
```

- jupyter 실행
    - hyul-server:8888
- jupyter 작업

```python
# 패키지 다운
pip install sqlalchemy
pip install pymysql
pip install mysqlclient
pip install pandas
```

- mariaDB 연결

```python
import sqlalchemy
from urllib import parse

# MySQL 계정
user = 'encore'
# MySQL 비밀번호
password = '1234'
# MySQL host
host='192.168.70.31'
# MySQL port
port = 3306
# MySQL 데이터베이스명
database = 'encore'
password = parse.quote_plus(password)
# MySQL 연결 engine
engine = sqlalchemy.create_engine(f"mysql://{user}:{password}@{host}:{port}/{database}")
```

- Query 날리기

```python
import pandas as pd
df = pd.read_sql_query("select * from pybo_answer", con=engine)
```

- 모델 api 쏘기

```python
import requests

# VM Server IP로 URL 날리기
url = "http://192.168.70.180:8000/predict/"
for idx, row in df.iloc[10:,:].iterrows():
    r = requests.post(url, data={'text':row['content']})
    print(row['content'], end = "===>")
    print((lambda x:"긍정" if x==1 else "부정")(r.json()['sentence'][0]))
    if idx > 20:
        break
```