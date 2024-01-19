### Virtual Box 실행

- 설정
    - 새로 만들기
    - ISO 이미지 파일 선택
    - 사용자 이름 / 암호 설정
    - 하드웨어 메모리 설정
        - 메모리 : 8192MB
        - 프로세서 : 2CPU
    - 하드 디스크 기본값
        - 새 가상 하드 디스크 : 25GB
    - 키보드 설정
        - 호스트키 조합 : Left Window + Shift
- 들어가기
    - Ubuntu software 에서 Terminator 설치
    - Administrator 계정 만들기
        - User → Unlock → Add User
        - Account Type : Administrator로 설정
        - 사용자 이름 / 암호 설정
    - 호스팅 설정
        - sudo shutdown -h now : 가상머신 나가기
        - Virtual Box 설정
            - 어댑터2
            - 호스트 전용 어댑터 선택
    - 가상머신 재실행
    - Terminator 실행
        - sudo service ssh status 상태확인
        - Error 발생
            - sudo apt-get install openssh-server
        - ip 확인
            - ip addr

### Windows cmd

- ssh {계정명}@ip
- whoami : 현재 사용중인 계정 확인
- 권한
    
    ```bash
    rwx r-x r-x (755)
    소유자 그룹 others
    r : read (4)
    w : write (2)
    x : exe (1)
    ```
    
- pip 설치
    - sudo apt install python3-pip
- Miniconda 설치
    - 파일 다운로드
        - wget https://repo.anaconda.com/miniconda/Miniconda3-latest-Linux-x86_64.sh
    - Miniconda 파일 권한 변경
        - chmod 700 ./Miniconda3-latest-Linux-x86_64.sh
        - 권한 변경확인 (ls -al)
    - 실행
        - ./Miniconda3-latest-Linux-x86_64.sh
        - q(끝까지 내리기) → enter → yes → enter → yes
- bashrc 적용 (환경설정 변경내용 적용)
    - source ~/.bashrc
        - 유저가 로그인할 때 읽어들이는 파일
        - 유저 개인의 alias 및 변수 설정
- jupyter 설치
    - pip3 install jupyter

</br>
### 💡 Reference

- Virtual Box 설치 (Windows hosts)
    - https://www.virtualbox.org/wiki/Downloads
- Virtual Box 설치시 Microsoft Visual C++ 에러
    - https://olppaemmit.tistory.com/216
- Ubuntu (22.04.3 LTS)
    - https://ubuntu.com/download/desktop
- source ~/.bashrc
    - https://imksh.com/93