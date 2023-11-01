# 제1회 국민대학교 AI빅데이터 분석 경진대회
- 주제 : 이력서 맞춤형 채용 공고 추천 AI 모델 개발
- 설명
  - 이력서, 채용 공고 및 지원 히스토리 데이터를 활용하여 구직자에게 맞춤화된 채용 공고를 자동으로 추천할 수 있는 추천시스템 알고리즘 개발
- 대회 일정
  - 2023년 10월 26일 : 대회 시작
  - 2023년 11월 06일 : 팀 병합 마감
  - 2023년 11월 13일 : 대회 종료
  - 2023년 11월 17일 : 코드 및 PPT 제출 마감
  - 2023년 11월 26일 : 코드 검증
  - 2023년 11월 27일 : 최종 결과 발표
----------
- 팀 결성 : 2023년 10월 25일
- 회의
  <details>
  <summary>2023년 10월 30일</summary>
    
    - 대회 전반적인 설명
      - target, feature가 무엇인지
      - 최종 결과를 무엇을 뽑아야 하는지
      - BaseModel 코드 해석
      - 평가모델 : Recall5
      - Public Score: 전체 테스트 이력서 샘플 중 사전 샘플링된 30%
      - Private Score: 전체 테스트 이력서 샘플 중 나머지 70%
    - 앞으로 해야할 목록
      - feature 뽑기
      - Model 확정 및 수정
      - EDA 구역 배분
        
  </details>

  <details>
  <summary>2023년 11월 01일</summary>
    
  - Negative Samping 목표
    
  - Titanic과 동일시 하려고

  ### 국민대 데이터
  
  - apply-train
  - resume
  - recruitment
  
  ### Flow
  
  - Import
  - 데이터 로드
      - apply-train
      - resume
          - resume_education
          - resume_language
          - resume_certificate
      - recruitment
          - company
      - submission
  - EDA
      - apply_train 기준으로 merge
      - merge_total = merge_resume + merge_recruitment
      - 새로운 구직자 f, 새로운 공고 f
  - Negative sampling
      - 데이터를 이진분류로 변경
      - 지원한 공고 갯수, 지원하지 않은 공고 갯수 뽑기
          - 실제값(지원한 공고) 만큼 거짓데이터(지원하지 않은 공고) 뽑는다
      - train / test split
          - df_shuffle
              - idx, resume_seq, recruitment_seq, target(실제지원결과: 1/0)
  - 학습
      - Boosting : 이진분류(0/1)
      - LightGBM 사용
          - 인코딩 따로 할 필요 없고 속도가 빠름
  - 예측
      - 유저당 모든 공고에 대한 예측값을 구한다. ⇒ for
  - output
      - 유저당 상위 5개(지원한 공고 제외)를 뽑아서 제출
  
  ### 해야할 것
  
  - 코드 구현
  - EDA를 통한 feature 생성
  - Model LightGBA
    -  최적의 하이퍼파라미터 찾기
  - 7일까지는 되어야 한다.
    
  </detail>
