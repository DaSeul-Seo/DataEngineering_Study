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