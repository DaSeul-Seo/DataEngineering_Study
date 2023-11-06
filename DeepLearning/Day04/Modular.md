### Modular

- `data_setup.py`: a file to prepare and download data if needed.
    - 데이터 다운로드, 데이터 전처리 관련된 파일
- `engine.py`: a file containing various training functions.
    - 학습에 관련된 함수 파일
- `model_builder.py`: a file to create a Pytorch model.
    - pytorch 모델을 정의 파일
- `train.py`: a file to leverage all other files and train a target Pytorch model.
    - pytorch 모델을 실행 시키는 파일 (engine, model_builder)
- `utils.py`: a file dedicated to helpful utility functions.
    - 유용한 기능들을 모아 놓은 파일
