- ajax
    - api 통신을 통해 비동기로 데이터를 가져온다.
- Linux 휴지통 경로
    - cd /home/사용자/.local/share/Trash/
- windows hosts 경로
    - C:\Windows\System32\drivers\etc\hosts

### 운영체제 batch job 설정

- crontab -e
- 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7 : 일-월)

### Web Crawling

- 개발자 도구 → network
- 확인할 것
    - Headers
        - Request URL
        - Request Method
        - Status Code
    - Response
    - Payload

```python
# 필요한 라이브러리 및 모듈 설치 / import
pip install pandas
pip install tqdm

import requests
import pandas as pd
```

- 한국 주식 데이터 크롤링

```python
# Request URL
url = "https://api.finance.naver.com/siseJson.naver?symbol=009410&requestType=1&startTime=20130101&endTime=20240104&timeframe=day"
# requests 라이브러리를 이용해 url로 데이터를 받아온다.
r = requests.post(url)
# 받아온 데이터 확인
r.text
# 받아온 데이터(문자열 표현식)를 해당 결과를 보기 좋게 반환
eval(r.text.strip())
# 데이터 프레임으로 만드는데 컬럼명을 지정해준다.
pd.DataFrame(eval(r.text.strip())[1:], columns=eval(r.text.strip())[0])
```

- 날짜와 종목 리스트 input으로 받아 데이터 가져오기

```python
start_date = "19900101"
end_date = "20240104"
symbol = "009410"
```

```python
url1 = f"https://api.finance.naver.com/siseJson.naver?symbol={symbol}&requestType=1&startTime={start_date}&endTime={end_date}&timeframe=day"
r1 = requests.post(url1)
df = pd.DataFrame(eval(r1.text.strip())[1:], columns=eval(r1.text.strip())[0])
```

```python
# 5개 데이터 묶고(rolling) 평균을 구하기
df['MA05'] = df['종가'].rolling(5).mean()
df['MA20'] = df['종가'].rolling(20).mean()
df['MA60'] = df['종가'].rolling(60).mean()
df['MA90'] = df['종가'].rolling(90).mean()
df['MA120'] = df['종가'].rolling(120).mean()
```

```python
# 결측치 제거
df.dropna(inplace = True)
```

- 문제
    
    > stock 안에 kospi, kosdaq 대상으로
    2015년 ~ 현재까지 종목 데이터를 각각의 파일로 저장
    파일 형식은 종목코드.csv
    ma05, 20, 60, 90, 120
    결측치 삭제
    DataFrame 안에 종목 코드 포함
    > 
    - 종목 리스트 가져오기
    
    ```python
    # 종목리스트 URL
    krx = "http://data.krx.co.kr/comm/bldAttendant/getJsonData.cmd"
    # payload
    payload = {
        "bld": "dbms/MDC/STAT/standard/MDCSTAT01901",
        "locale": "ko_KR",
        "mktId": "ALL",
        "share": "1",
        "csvxls_isNo": "false",
    }
    # requests 라이브러리를 이용해 url로 데이터를 받아온다.
    krx_r = requests.post(krx, data=payload)
    # 받아온 데이터를 데이터 프레임화
    krx_df = pd.DataFrame(krx_r.json()['OutBlock_1'])
    ```
    
    ```python
    # stock 안에 kospi, kosdaq 대상으로 
    krx_tmp_df = krx_df.loc[(krx_df['MKT_TP_NM'] == "KOSPI") | (krx_df['MKT_TP_NM'] == "KOSDAQ")]
    
    # kospi와 kosdaq인 종목 리스트 추출
    symbolList = krx_tmp_df['ISU_SRT_CD']
    ```
    
    ```python
    from tqdm import tqdm
    for symbol in tqdm(symbolList):
        # 2015년~ 현재까지 종목데이터를 각각의 파일로 저장 
        start_date = "20150101"
        end_date = "20240104"
        url1 = f"https://api.finance.naver.com/siseJson.naver?symbol={symbol}&requestType=1&startTime={start_date}&endTime={end_date}&timeframe=day"
        r1 = requests.post(url1)
        df = pd.DataFrame(eval(r1.text.strip())[1:], columns=eval(r1.text.strip())[0])
    
        # ma05, 20,60, 90, 120
        # 5개 데이터 묶고(rolling) 평균을 구하기
        df['MA05'] = df['종가'].rolling(5).mean()
        df['MA20'] = df['종가'].rolling(20).mean()
        df['MA60'] = df['종가'].rolling(60).mean()
        df['MA90'] = df['종가'].rolling(90).mean()
        df['MA120'] = df['종가'].rolling(120).mean()
        
        # 결측치 제거
        df.dropna(inplace = True)
        # dataframe안에 종목 코드도 포함 
        df['ticker'] = symbol
        # 데이터 csv로 저장 - 파일 형식은 종목코드.csv
        df.to_csv(f"./stock/{symbol}_data.csv", index=False, encoding="utf-8-sig")
    ```
    
- 강사님 방법
    
    ```python
    import requests 
    import pandas as pd
    import os 
    from multiprocessing import Pool
    import pickle 
    
    def get_stock(code, date_="20240103"):
        url = "https://api.finance.naver.com/siseJson.naver?symbol={}&requestType=2&count=2000&startTime={}&timeframe=day"
        r = requests.post(url.format(code, date_))
        df = pd.DataFrame(eval(r.text.strip())[1:], columns=eval(r.text.strip())[0])
        df['MA05'] = df['종가'].rolling(5).mean()
        df['MA20'] = df['종가'].rolling(20).mean()
        df['MA60'] = df['종가'].rolling(60).mean()
        df['MA90'] = df['종가'].rolling(90).mean()
        df['MA120'] = df['종가'].rolling(120).mean()
        df.dropna(inplace=True)
        df['ticker'] = code
        df.to_csv(f"./stock/{code}.csv", index=False, encoding='utf-8')
    
    if __name__ == "__main__":
        with open("./code.pkl", "rb") as f:
            code_list = pickle.load(f)
    
        pool = Pool(processes=2)
        pool.map(get_stock, code_list)
    ```
    
    ```python
    from datetime import datetime
    import pickle
    
    krx = "http://data.krx.co.kr/comm/bldAttendant/getJsonData.cmd"
    payload = {"bld": "dbms/MDC/STAT/standard/MDCSTAT01901",
    "locale": "ko_KR",
    "mktId": "ALL",
    "share": "1",
    "csvxls_isNo": "false",}
    krx_r = requests.post(krx, data=payload)
    krx = pd.DataFrame(krx_r.json()['OutBlock_1'])
    
    krx['MKT_TP_NM'].unique()
    code_list = krx[krx['MKT_TP_NM'].isin(['KOSDAQ', 'KOSPI'])]['ISU_SRT_CD'].tolist()
    
    with open("./code.pkl", "wb") as f:
        pickle.dump( code_list, f)
    
    datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    ```