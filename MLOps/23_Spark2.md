### hadoop에 추가 데이터 넣기

- cd {압축푼 폴더}/data/flight-data/json
- hadoop에 폴더 만들고 데이터 넣기
    - hdfs dfs -mkdir /data/flight/json
    - hdfs dfs -put *.json /data/flight/json

### 스키마

- DB에서의 스키마의 개념은 동일
    - DataFrame의 컬럼명과 데이터 타입을 정의
- ETL(추출, 변환, 적재) 작업에 스파크를 사용한다면 직접 스키마를 정의해야함

### Apache Zeppelin

- 하둡의 에코 시스템
- 데이터를 노트북 기반으로 interactive하게 데이터 분석을 할 수 있게 도와주는 프로그램