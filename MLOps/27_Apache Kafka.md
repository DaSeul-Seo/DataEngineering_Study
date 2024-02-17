### Apache Kafka

- 비빔밥의 고추장 역할
- 독단적으로 쓰일 수 없음
- 여러 대의 분산 서버에서 대량의 데이터를 처리하는 분산 메시징 시스템
- 데이터(메시지)를 받고, 받은 메시지를 다른 시스템이나 장치에 보내기 위해 사용
- 여러 시스템과 장치를 연결하는 중요한 역할 담당
- 금융권 - KB 카드에서 사용

### 메시지 큐

- 링크드인 개념
1. RabbitMQ
    1. 대량의 데이터를 손실없이 전달하는 것이 목적

### ⭐ 메시징 모델

- 3가지 요소로 구성
    - Producer : 메시지 생산자
    - Broker : 메시지 수집/전달 역할 (Kafka)
    - Consumer : 메시지 소비자

### Celery

- FastAPI, Django, Flask랑 붙는다.
- 장고에서 들어오는 데이터를 Celery가 중간에서 전달

### 링크드인

- 높은 처리량으로 실시간 처리
- 임의의 타이밍에서 데이터를 읽음
- 다양한 제품과 시스템에 쉽게 연동
- 메시지를 잃지 않음