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
- jupyter 설치
    - pip3 install jupyter

### jupyter 설정

- jupyter 설정파일 만들기
    - jupyter notebook --generate-config

### ipython (Interactive Python)

- jupyter server 비밀번호 설정
    - jupyter server password
- argon 키 확인
    - vi /home/[계정]/.jupyter/jupyter_server_config.json
- vi /home/[계정]/.jupyter/jupyter_notebook_config.py
- 819열
    - c.ServerApp.ip = '0.0.0.0’
- 923열
    - 주석풀기
- 927열
    - 암호화 비밀번호 추가 (본인이 설정한 비밀번호)
    - argon2:$argon2id$v=….

### jupyter 실행

- nohup jupyter lab &

- nohup 설치
    - sudo apt install nohup