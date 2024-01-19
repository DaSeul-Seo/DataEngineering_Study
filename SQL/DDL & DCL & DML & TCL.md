- root
    - DB 생성 / 수정 / 삭제
    - 계정 생성 / 수정 / 삭제
- admin → DBA
- user → 모든 사람
    - 계정 관리 권한 X
    - DB 권한 X
    - Table CRUD → 선택적 권한

### SQL (Structured Query Language)

- DDL (Data Definition Language)
    - 데이터베이스 & 테이블 정의, 수정, 삭제
    - Create, Alter, Drop, Rename, Truncate
- DML (Data Manipulation Language)
    - 데이터 삽입, 조회, 수정, 삭제
    - Insert, Select, Update, Delete
- DCL (Data Control Language)
    - 데이터 보안, 권한, 무결성, 회복 등 데이터 제어
    - Grant, Revoke
- TCL (Transaction Control Language)
    - Commit, Rollback, Savepoint

---

- 외래키(FK)를 사용하지 않는 경우도 존재
    - ⇒ 그냥 컬럼명을 통일시켜 준다.
    - 제약조건이기 때문에 테이블조작 시 힘든 경우 발생
    - 기본키를 잘 확인하여야 한다.
- on {delete, update} cascade
    - 부모 테이블의 row를 update / delete 할 경우 자동으로 자식 테이블에 매치되는 row도 동일하게 반영
    - 데이터 관리 편리 및 일관성 유지

</br>

### 💡 Reference


- CASCADE
    - [https://velog.io/@eensungkim/ON-DELETE-CASCADE-feat.-row-한-번에-지우는-방법-TIL-78일차](https://velog.io/@eensungkim/ON-DELETE-CASCADE-feat.-row-%ED%95%9C-%EB%B2%88%EC%97%90-%EC%A7%80%EC%9A%B0%EB%8A%94-%EB%B0%A9%EB%B2%95-TIL-78%EC%9D%BC%EC%B0%A8)