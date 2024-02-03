- powertoy 다운로드
    - 호스트명으로 url 들어가서 확인하기 위해 설정
- Cliet에서 hadoop 시작
    - ssh namenode start-dfs.sh
    - ssh secondnode start-yarn.sh
    - ssh namenode mr-jobhistory-daesmon.sh start historyserver
    - ssh namenode jps
    - ssh secondnode jps
    - ssh datanode3 jps
- hadoop에 폴더 만들기
    - http://namenode:50070/explorer.html#/ 에서 확인
    - hdfs dfs -mkdir /encore
- 하둡에 밀어넣기
    - wget https://gutenberg.org/cache/epub/100/pg100.txt
    - hdfs dfs -put {올릴파일} {올릴경로}
    - hdfs dfs -put ./pg100.txt /encore/
- mapreduce
    - cd ~/hadoop/share/hadoop/mapreduce/
    - hadoop jar ./hadoop-mapreduce-examples-3.3.6.jar grep /encore /result/ '[a-z]+ ‘
        - 워드카운트 예제
        - 결과를 /result 폴더로 떨궈줘라.
            - hdfs dfs -car /result/*
- alias 설정
    - bashrc 설정
        
        ```bash
        alias start-dfs="ssh namenode start-dfs.sh"
        alias start-yarn="ssh secondnode start-yarn.sh"
        alias stop-dfs="ssh namenode stop-dfs.sh"
        alias stop-yarn="ssh secondnode stop-yarn.sh"
        alias start-mr="ssh namenode mr-jobhistory-daemon.sh start historyserver"
        alias stop-mr="ssh namenode mr-jobhistory-daemon.sh stop historyserver"
        ```
        
    - source ~/.bashrc