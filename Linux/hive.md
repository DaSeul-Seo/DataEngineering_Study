- 하이브 다운로드
    - https://dlcdn.apache.org/hive/hive-3.1.3/ 링크로 들어가서 해당되는 목록 오른쪽 마우스 해서 링크 복사
    - wget {복사한 경로}
- 압축 풀기
    - tar xvfz apache-hive-3.1.3-bin.tar.gz
- 폴더 이름 변경
    - mv apache-hive-3.1.3-bin hive
- DB 접속
    - mysql -uroot -p1234
    - 데이터 베이스 생성
        - create database hivedb;
    - 유저 만들기
        - create user hiveuser@localhost identified by 'hivepw';
    - 권한
        - grant all privileges on hivedb.* to hiveuser@localhost;
        - grant all privileges on hivedb.* to hiveuser@'client' identified by 'hivepw';
        - 갱신 = flush privileges;
    
    ```sql
    -- 비밀번호 변경 시
    set password for 'hiveuser'@'localhost' = password('1234');
    set password for 'hiveuser'@'client' = password('1234');
    ```
    
- hive 설정파일 옮기기
    - jar
        - /home/hadoop/workspace/seul/hive/lib
        - guava-29.0-jre.jar
        - mysql-connector-java-8.0.21.jar
    - conf 파일
        - /home/hadoop/workspace/seul/hive/conf
        - hive-site.xml
        - hive-env.sh
- hadoop 폴더 생성
    - hdfs dfs -mkdir -p /user/hive/warehouse
    - hdfs dfs -chmod g+w /user/hive/warehouse
    - hdfs dfs -mkdir /tmp
    - hdfs dfs -chmod g+w /tmp
- hive 환경설정
    - vi ~/.bashrc
    
    ```sql
    export HIVE_HOME=/home/hadoop/hive
    export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin:$HIVE_HOME/bin
    ```
    
    - source ~/.bashrc
- schematool -initSchema -dbType mysql
    - 에러 해결
        
        ### 문제발생
        
        - schematool -initSchema -dbType mysql
            - **SQL Error code: 1045 에러발생**
                
                ```sql
                SLF4J: Class path contains multiple SLF4J bindings.
                SLF4J: Found binding in [jar:file:/home/hadoop/hive/lib/log4j-slf4j-impl-2.17.1.jar!/org/slf4j/impl/StaticLoggerBinder.class]
                SLF4J: Found binding in [jar:file:/home/hadoop/hadoop/share/hadoop/common/lib/slf4j-reload4j-1.7.36.jar!/org/slf4j/impl/StaticLoggerBinder.class]
                SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
                SLF4J: Actual binding is of type [org.apache.logging.slf4j.Log4jLoggerFactory]
                Metastore connection URL:        jdbc:mysql://client:3306/hivedb?createDatabaseIfNotExist=true
                Metastore Connection Driver :    com.mysql.jdbc.Driver
                Metastore connection User:       hiveuser
                Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
                org.apache.hadoop.hive.metastore.HiveMetaException: Failed to get schema version.
                Underlying cause: java.sql.SQLException : Access denied for user 'hiveuser'@'client' (using password: YES)
                **SQL Error code: 1045**
                Use --verbose for detailed stacktrace.
                *** schemaTool failed ***
                ```
                
                - 비밀번호가 동일하지 않아서 발생
                - hive/conf/hive-site.xml에서 비밀번호 설정 수정
                    
                    ```sql
                    <property>
                    	<name>javax.jdo.option.ConnectionPassword</name>
                    	<value>1234</value>
                    	<description>password to use against metastore database</descriptio     n>
                    </property>
                    ```
                    
        - 다시 실행했을 때 **SQL Error code: 0 발생**
            
            ```sql
            SLF4J: Class path contains multiple SLF4J bindings.
            SLF4J: Found binding in [jar:file:/home/hadoop/hive/lib/log4j-slf4j-impl-2.17.1.jar!/org/slf4j/impl/StaticLoggerBinder.class]
            SLF4J: Found binding in [jar:file:/home/hadoop/hadoop/share/hadoop/common/lib/slf4j-reload4j-1.7.36.jar!/org/slf4j/impl/StaticLoggerBinder.class]
            SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
            SLF4J: Actual binding is of type [org.apache.logging.slf4j.Log4jLoggerFactory]
            Metastore connection URL:        jdbc:mysql://client:3306/hivedb?createDatabaseIfNotExist=true
            Metastore Connection Driver :    com.mysql.jdbc.Driver
            Metastore connection User:       hiveuser
            Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
            org.apache.hadoop.hive.metastore.HiveMetaException: Failed to get schema version.
            Underlying cause: java.sql.SQLException : The server time zone value 'KST' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the 'serverTimezone' configuration property) to use a more specifc time zone value if you want to utilize time zone support.
            SQL Error code: 0
            Use --verbose for detailed stacktrace.
            *** schemaTool failed ***
            ```
            
        
        ### 해결방법
        
        - mariadb address 수정
            - cd /etc/mysql/mariadb.conf.d
            - sudo vim 50-server.cnf
                - bind-address = 0.0.0.0
            - sudo service mysql restart
        - **SQL Error code: 1045 에러발생**
            - 비밀번호가 동일하지 않아서 발생
            - hive/conf/hive-site.xml에서 비밀번호 설정 수정
                
                ```sql
                <property>
                	<name>javax.jdo.option.ConnectionPassword</name>
                	<value>1234</value>
                	<description>password to use against metastore database</descriptio     n>
                </property>
                ```
                
        - **SQL Error code: 0 발생**
            - Timezone 추가
                - <value>jdbc:mysql://client:3306/hivedb?createDatabaseIfNotExist=true&amp;serverTimezone=UTC</value>
            
            ```sql
            <property>
                <name>javax.jdo.option.ConnectionURL</name>
                <value>jdbc:mysql://client:3306/hivedb?createDatabaseIfNotExist=true&amp;serverTimezone=UTC</value>
                <description>JDBC connect string for a JDBC metastore</description>
              </property>
            ```
            
        
- hive 데이터 넣기
    
    ```sql
    CREATE EXTERNAL TABLE IF NOT EXISTS stocks (
    day STRING,
    open INT,
    high INT,
    low INT,
    close INT,
    trade INT,
    alien INT,
    ma05 FLOAT,
    ma20 FLOAT,
    ma60 FLOAT,
    ma90 FLOAT,
    ma120 FLOAT,
    ticker STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
    LOCATION '/stock'
    tblproperties ("skip.header.line.count"="1");
    ```
    
- 일별 총 거래 금액
    
    ```sql
    -- SELECT 결과를 해당 경로 파일에 저장
    INSERT OVERWRITE DIRECTORY "/stock_result"
    SELECT day, sum(close * trade) as trade_money 
    FROM stocks
    GROUP BY day
    ORDER BY day;
    ```
    
- 시각화
    
    ```sql
    pip install hdfs
    ```
    
    ```sql
    from hdfs import InsecureClient
    client = InsecureClient('http://namenode:50070', user='hadoop')
    with client.read("/stock_result/000000_0") as data:
        for line in data:
            print(" - ".join(line.decode('utf-8').strip().split("")))
    ```
    
    ```sql
    import matplotlib.pyplot as plt
    
    dates = []
    total_amounts = []
    
    with client.read("/stock_result/000000_0") as data:
        for line in data:
            decoded_line = line.decode('utf-8').strip()
            elements = decoded_line.split("")
            dates.append(elements[0])
            total_amounts.append(int(elements[1]))
    
    plt.figure(figsize=(10, 6))
    plt.plot(dates, total_amounts, marker='o', linestyle='-')
    plt.title('Daily Total Transaction Amount')
    plt.xlabel('Date')
    plt.ylabel('Total Amount')
    plt.xticks(rotation=45)
    plt.tight_layout()
    plt.show()
    ```
    
- 환경설정 파일
    - https://eyeballs.tistory.com/165
    
- ⭐ 가상환경 설정
    - hostname 과 hadoop 설정 이름과 동일해야 한다!