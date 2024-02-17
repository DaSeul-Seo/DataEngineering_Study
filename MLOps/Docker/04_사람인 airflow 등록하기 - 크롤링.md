### 필요 라이브러리 설치

```python
# BeautifulSoup
!pip install bs4
# Pandas
!pip install pandas
```

### import

```python
import os
import requests
from bs4 import BeautifulSoup as BS
import pandas as pd
```

### requests

```python
head = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36 Edg/121.0.0.0'
}

url = "https://www.saramin.co.kr/zf_user/jobs/list/domestic?page=1&cat_mcls=2&exp_cd=1&loc_mcd=101000&loc_cd=102190&search_optional_item=y&search_done=y&panel_count=y&preview=y&isAjaxRequest=0&page_count=1000&sort=RD&type=domestic&is_param=1&isSearchResultEmpty=1&isSectionHome=0&searchParamCount=4&tab=domestic#searchTitle"
```

```python
r = requests.get(url, headers=head)
bs = BS(r.text)
```

### 원하는 data 추출

- 공고 리스트

```python
data = bs.find("div", class_="list_body")

len(data.findAll("div", class_="list_item"))

data2 = data.findAll("div", class_="list_item")
```

```python
# 회사명
data2[0].a.text.strip()
# 직무 
"|".join([x.text for x in data2[0].find(class_="job_sector").findAll("span")])
# 위치
data2[0].find(class_="work_place").text
# 경력 / 신입 
data2[0].find(class_="career").text
# 학력
data2[0].find(class_="education").text
# 마감일 
data2[0].find(class_="date").text
```

### 추출한 데이터를 데이터 프레임으로 만들기

```python
url = "https://www.saramin.co.kr/zf_user/jobs/list/domestic?page={}&cat_mcls=2&exp_cd=1&loc_mcd=101000&loc_cd=102190&search_optional_item=y&search_done=y&panel_count=y&preview=y&isAjaxRequest=0&page_count=1000&sort=RD&type=domestic&is_param=1&isSearchResultEmpty=1&isSectionHome=0&searchParamCount=4&tab=domestic#searchTitle"
all_total = []
for page in range(1,5):
    r = requests.get(url.format(page), headers=head)
    bs = BS(r.text)
    data = bs.find("div", class_="list_body")
    data2 = data.findAll("div", class_='list_item')
    total = []
    for raw in data2[:]:
        tmp = {}
        # 회사명
        tmp['companyName'] = raw.a.text.strip()
        # 직무
        tmp['jobSector'] = "|".join([x.text for x in raw.find(class_="job_sector").findAll("span")])
        # 장소
        tmp['locate'] = raw.find(class_="work_place").text
        # 경력/신입
        tmp['career'] = raw.find(class_="career").text
        # 학력
        tmp['education'] = raw.find(class_="education").text
        # 마감일
        tmp['deadline'] = raw.find(class_="date").text
        total.append(tmp)
    all_total.append(pd.DataFrame(total))
```

```python
career = pd.concat(all_total, ignore_index=True)
career
```

### 중복제거

```python
career = career.drop_duplicates()
career
```

### d-day 컬럼 생성

```python
import re
p = re.compile("[0-9]{2}\.[0-9]{2}")

p2 = re.compile('rec-[0-9]{8,10}')
p2.findall(str(raw))[0]

career['d-day'] = career.deadline.apply(lambda x : p.findall(x)[0] if len(p.findall(x)) > 0 else '')
```

### 자동화 등록하기
1. Slack api 생성
    1. https://api.slack.com/apps
2. 권한추가 (OAuth & Permissions)
    1. channels:history
    2. chat:write
    3. chat:write.public
3. 인스턴스
    1. pip install slack_sdk
4. 토큰값
5. 채널 ID
    1. channelid ⇒ 슬랙에 채널 ID가 있음
6. 노트북
    
    ```bash
    from slack_sdk import WebClient
    from slack_sdk.errors import SlackApiError
    
    # 토큰값
    slack_token = "{발급한 토큰}"
    client = WebClient(token=slack_token)
    ```
    
    ```bash
    try:
        response = client.chat_postMessage(
            channel="{채널 ID}", #채널 id를 입력합니다.
            text="api test~!"
        )
    except SlackApiError as e:
        assert e.response["error"]
    ```

### 사람인 slack 등록하기 - Airflow
- EC2 실행
- workspace에 가상환경 만들기
    
    ```bash
    python3 -m virtualenv venv
    
    # virtualenv 가 설치 안되어 있는 경우
    pip3 install virtualenv
    
    python3 -m virtualenv venv
    ```
    
- 가상환경 들어가기
    
    ```bash
    . ./venv/bin/activate
    ```
    
- airflow 설치
    
    ```bash
    # 가상환경 
    export AIRFLOW_HOME=~/airflow
    ```
    
    ```bash
    AIRFLOW_VERSION=2.8.1
    PYTHON_VERSION="$(python --version | cut -d " " -f 2 | cut -d "." -f 1-2)"
    CONSTRAINT_URL="https://raw.githubusercontent.com/apache/airflow/constraints-${AIRFLOW_VERSION}/constraints-${PYTHON_VERSION}.txt"
    pip install "apache-airflow==${AIRFLOW_VERSION}" --constraint "${CONSTRAINT_URL}"
    ```
    
- airflow db
    
    ```bash
    airflow db migrate
    ```
    
    - 계정만들기
    
    ```bash
    airflow users create \
        --username gen2 \
        --firstname Peter \
        --lastname Parker \
        --role Admin \
        --email spiderman@superhero.org
    ```
    
    - 비밀번호 복잡하게 해야함.
- airflow 실행위치 확인
    
    ```bash
    which airflow
    ```
    
- airflow 실행
    
    ```bash
    nohup airflow webserver --port 8080 &
    nohup airflow scheduler &
    ```
    
- 웹으로 확인
    
    ```bash
    IP:8080
    ```
    
    - 에러
        - 포트
        
        ```bash
        # 현재 사용중인 포트와 pid 
        sudo netstat -ntpl
        ```
        
        - 프로세스 죽이기
        
        ```bash
        # 프로세스 죽이기
        sudo kill -9 pid
        # 프로세스 찾기
        sudo ps -ef | grep [프로그램이름]
        # 해당 이름이 들어간 프로세스 다 죽이기
        sudo pkill -f [이름]
        ```
        
        - 도커 죽이기
        
        ```bash
        docker stop $(docker ps -a -q)
        ```
        
- airflow 시작 ⇒ 웹으로 확인
- 폴더 추가
    
    ```bash
    cd ~/airflow
    ls -al
    mkdir dags
    ```
    
- find dataset
    
    ```bash
    # permission denied같은 기본적인 것은 안보여준다. = 2>/dev/null
    find / -name "dataset_consumes_1.py" 2>/dev/null
    ```
    
- 파일 추가
    - 추가 후 웹에 추가될때까지 기다리자
    
    ```bash
    cd ~/airflow/dags 
    vim encore_01.py
    ```
    
    ```bash
    import airflow
    from airflow import DAG
    from airflow.operators.bash import BashOperator
    
    dag = DAG(
        dag_id="listing_4_01",
        start_date=airflow.utils.dates.days_ago(3),
        schedule_interval="@hourly",
    )
    
    get_data = BashOperator(
        task_id="get_data",
        bash_command=(
            "curl -o /tmp/wikipageviews.gz "
            "https://dumps.wikimedia.org/other/pageviews/"
            "{{ execution_date.year }}/"
            "{{ execution_date.year }}-{{ '{:02}'.format(execution_date.month) }}/"
            "pageviews-{{ execution_date.year }}"
            "{{ '{:02}'.format(execution_date.month) }}"
            "{{ '{:02}'.format(execution_date.day) }}-"
            "{{ '{:02}'.format(execution_date.hour) }}0000.gz"
        ),
        dag=dag,
    )
    ```
    
- 파일 추가 2
    - 추가 후 웹에 추가될때까지 기다리자
    
    ```bash
    vi encore_02.py
    ```
    
    ```bash
    from urllib import request
    
    import airflow.utils.dates
    from airflow import DAG
    from airflow.operators.bash import BashOperator
    from airflow.operators.python import PythonOperator
    
    dag = DAG(
        dag_id="listing_4_18",
        start_date=airflow.utils.dates.days_ago(1),
        schedule_interval="@hourly",
        max_active_runs=1,
    )
    
    def _get_data(year, month, day, hour, output_path, **_):
        url = (
            "https://dumps.wikimedia.org/other/pageviews/"
            f"{year}/{year}-{month:0>2}/pageviews-{year}{month:0>2}{day:0>2}-{hour:0>2}0000.gz"
        )
        print(url)
        request.urlretrieve(url, output_path)
    
    get_data = PythonOperator(
        task_id="get_data",
        python_callable=_get_data,
        op_kwargs={
            "year": "{{ execution_date.year }}",
            "month": "{{ execution_date.month }}",
            "day": "{{ execution_date.day }}",
            "hour": "{{ execution_date.hour }}",
            "output_path": "/tmp/wikipageviews.gz",
        },
        dag=dag,
    )
    
    extract_gz = BashOperator(
        task_id="extract_gz", bash_command="gunzip --force /tmp/wikipageviews.gz", dag=dag
    )
    
    def _fetch_pageviews(pagenames, execution_date, **_):
        result = dict.fromkeys(pagenames, 0)
        with open("/tmp/wikipageviews", "r") as f:
            for line in f:
                domain_code, page_title, view_counts, _ = line.split(" ")
                if domain_code == "en" and page_title in pagenames:
                    result[page_title] = view_counts
    
        with open("/tmp/postgres_query.sql", "w") as f:
            for pagename, pageviewcount in result.items():
                f.write(
                    "INSERT INTO pageview_counts VALUES ("
                    f"'{pagename}', {pageviewcount}, '{execution_date}'"
                    ");\n"
                )
    
    fetch_pageviews = PythonOperator(
        task_id="fetch_pageviews",
        python_callable=_fetch_pageviews,
        op_kwargs={"pagenames": {"Google", "Amazon", "Apple", "Microsoft", "Facebook"}},
        dag=dag,
    )
    
    get_data >> extract_gz >> fetch_pageviews
    ```