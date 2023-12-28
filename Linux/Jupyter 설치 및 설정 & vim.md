### jupyter 설정

```bash
jupyter notebook --generate-config
vim
sudo apt install vim -y
vim /home/[계정]/.jupyter/jupyter_notebook_config.py
```

---

- api 서버(Fast API 등) 전에 메시지 서버(redis)를 띄어놓는다.
- Queue에 데이터가 많이 쌓이면 터진다. ⇒ Queue 메모리를 늘려준다.
- ⭐ 웹서버 VS WAS 차이점 알 것
    - 웹서버
        - 정적인 것
            - HTTP 요청 받기
            - HTML, 이미지, CSS 파일 등의 정적인 콘텐츠 제공
        - nginx
    - WAS
        - 동적인 것
            - 동적 콘텐츠를 처리하며 웹 서버와 연동해 완전한 웹 어플리케이션 제공
            - 웹서버보다 더 많은 기능 제공
            - 데이터베이스와 상호작용
            - 비즈니스 로직 수행
            - 프로그래밍 언어(Java, .NET)로  작성된 웹 어플리케이션의 실행환경 제공
            - 클라이언트의 요청에 대한 동적 응답 생성
        - Container
        - Kubernetes
    - 간혹 웹서버와 WAS의 기능을 하나의 소프트웨어에 통합되어 제공

---

### Vim

- 보기 모드
- 수정 모드
- 명령어 모드

---

### ipython (Interactive Python)

- jupyter server password
- vi /home/root_user/.jupyter/jupyter_{}?config.json
- 923열
    - 주석풀기
- 927열
    - 암호화 비밀번호 추가 (본인이 설정한 비밀번호)
    - argon2:$argon2id$v=19$m=10240,t=10,p=8$1JI94pEMaremEqTyx11DUw$s27vaplAlACviJO/rqXgAUsZobTytVJl84t5TBekmE0

---

jupyter lab --ip=0.0.0.0 : 모든 ip 허용

192와 172는 private IP 이다.

다른 사람에게 알려줄 때는 pubic IP를 알려주어야 한다.

---

- 크롤링 할 때 필요한 것
    - Request URL
    - Method
    - Payload

---

- nohub : 24시간 계속 프로세스 실행 (실시간)
- & : 백그라운드
- ps -ef : 현재 실행되고 있는 프로세스
- kill -9 {pid} : 프로세스 죽이기
- rm -rf : 폴더 삭제

---

### 파일 쓰기 & 읽기

```bash
# wb : write binary
with open("./mydata.pkl", "wb") as f:
    pickle.dump(mydata, f)

# rb : read binary
with open("./tmp.pkl", "rb") as f:
    pickle.load(f)
```

- pickle
    - 데이터를 직렬화해서 0, 1로 저장
