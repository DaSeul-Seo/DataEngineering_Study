py -3.11 -m venv .venv
python -m pip install —upgrade pip
pip install fastapi
pip install “uvicorn[standard]”

server start : uvicorn server:app --reload
server : 파일명
app : FastAPI 객체