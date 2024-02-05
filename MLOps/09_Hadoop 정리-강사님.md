- 가상 머신 설정 때 네트워크 장비를 2개 설정함
    - 하나의 외부 인터넷과 통신하고 다른 하나는 내부 통신을 위한 장비
1. 계정 생성 전 설치가 필요한 것들
    1. net-tools 설치
        1. yum install net-tools
    2. network 장비 ip 설정
        1. /etc/sysconfig/network-scripts 안의 네트워크 파일 수정
            1. IPADDR={IP}
            2. nmcli device connect enp0s8
            
            ---
            <aside>
            1) ifdown 장비명
            </br>
            2) ifup 장비명
            
            i. ifup / ifdown 명령어
            1) 이더넷 카드의 현재 설정을 유지한 채 /etc/sysconfi.../network.../ifcnfg-en... 파일의 설정을 유지한 채로 디바이스를 구동시키고 다운 시키는 작업을 담당
            이 박스안의 내용은 centos7에 해당하는 내용 8버전부터는 nmcli
            
            i. 네트워크 시스템 서비스 재가동
            1) systemctl restart NetworkManager.service
            
            </aside>
            
            ----

    3. wget 설치
        1. sudo yum install wget -y
    4. vim 설치
        1. sudo yum install vim -y
    5. 자바 설치
        1. sudo dnf install java-1.8.0-openjdk ant -y
    6. 호스트 네임 변경
        1. sudo hostnamectl set-hostname client
2. 보안 설정 끄기
    1. sudo vi /etc/selinux/config
    2. SELINUX=disabled
        1. 컨테이너가 서비스에 필요한 자원에 접근할 수 있도록 보안 설정 변경 (재부팅하면 복구 됨)
    3. sudo setenforce 0
        1. sestatus 명령어를 사용해서 상태값 확인 할 수 있음
        2. 영구적으로 설정
    4. sudo sed -i 's/^SELINUX=enforcing$/SELINUX=permissive/' /etc/selinux/config
        1. 옵션은 변경된 결과를 파일에 반영
3. 재부팅
    1. sudo reboot
4. 계정 생성
    1. sudo adduser hadoop
    2. sudo passwd hadoop
    3. sudo visudo
        1. hadoop ALL=(ALL)   ALL 추가
5. hadoop 계정으로 변경
    1. su hadoop
6. 공개키 설정
    1. ssh-keygen -t rsa
        1. 기본값 모두 엔터
    2. cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
    3. chmod 640 ~/.ssh/authorized_keys
    4. ssh localhost
        1. 정상적으로 접속이 되어야 함
7. bashrc 설정값 변경
    1. vi ~/.bashrc
        1. JAVA_HOME의 경로는 /usr/lib/jvm/ 밑에 폴더 명을 설정한다.
        2. export JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.272.b10-1.el8_2.x86_64"
        3. 참고 파일에 있는 내용 복사(단 자바 경로는 확인)
8. 하둡 다운로드
    1. wget http://mirror.apache-kr.org/hadoop/common/hadoop-3.2.1/hadoop-3.2.1.tar.gz
        1. 다운로드 폴더 위치는 /home/hadoop
    2. tar xvzf hadoop-3.2.1.tar.gz
9. hadoop 폴더 이름 변경
    1. mv hadoop-3.2.1 hadoop
10. 하둡 환경 파일 수정
    1. core-site.xml
    2. hdfs-site.xml
    3. mapred-site.xml
    4. yarn-site.xml
    5. workers
        1. slaves(datanode 설정)
            1. 데이터노드를 누구로 설정할 것인지
        2. 아래를 추가
            1. datanode1
            2. datanode2
11. ec2 인스턴스 종료 종료하기
    1. sudo shutdown -h now
12. 인스턴로 이미지 생성하고 해당 이미지로 인스턴스 2개 추가 생성
13. hosts 파일 수정
    1. sudo vi /etc/hosts
14. hdfs-site.xml 의 데이터 폴더가 /data로 되어 있다면
    1. sudo 계정이 되는 계정으로
        1. sudo chmod 777 /data
        2. 두 개 인스턴스 모두 해야함
15. namenode 포맷(네임노드에서..)
    1. hadoop namenode -format
16. 하둡 실행 순서
    1. 실행하기 : HDFS -> YARN -> MR-History Server
        1. namenode   : start-dfs.sh
        2. secondnode : start-yarn.sh
        3. namenode   : mr-jobhistory-daemon.sh start historyserver
    2. 종료하기 : YARN -> MR-History Server -> HDFS
        1. secondnode : stop-yarn.sh
        2. namenode   : mr-jobhistory-daemon.sh stop historyserver
        3. namenode   : stop-dfs.sh