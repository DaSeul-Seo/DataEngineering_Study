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
---
---
- 스파크는 함수가 다 분산되어 있다.
- 표현식
    - expr : 간단하게 사용 가능
- spark 내 네임스페이스에 함수명은 유일하다.
    - 동일 함수명을 설정하면 기존의 함수 기능은 실행 불가
    - 마지막 함수명, 함수 기능으로 실행됨
- 각 컬럼별 결측치 확인
    
    ```bash
    # 사용자가 요청 => 아래 코드는 실행 계획만 잡힘
    # show()하면 스테이지가 뜨고 동작을 한다.
    df2 = df.select([count(when(col(c).contains('None') | \
    				col(c).contains('NULL') | \
    				(col(c) == '' ) | \
    				col(c).isNull(), c
    				)).alias(c)
    				for c in df.columns])
    ```
    
    ```bash
    # 
    df = df.withColumn('성별', when(col('성별') == 'm', 'M').otherwise(col('성별')))
    
    df.groupby("성별").agg( count("성별").alias("값")).show()
    ```
    
- printschema()
    - 테이블 정보 출력 : 컬러명, 데이터 타입