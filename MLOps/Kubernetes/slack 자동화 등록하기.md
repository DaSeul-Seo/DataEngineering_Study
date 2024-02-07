1. Slack api 생성
    1. https://api.slack.com/apps
2. 권한추가 (OAuth & Permissions)
    1. channels:history
    2. chat:write
    3. chat:write.public
3. 인스턴스
    1. pip install slack_sdk
4. 토큰값
5. 채널 ID
    1. channelid ⇒ 슬랙에 채널 ID가 있음
6. 노트북
    
    ```bash
    from slack_sdk import WebClient
    from slack_sdk.errors import SlackApiError
    
    # 토큰값
    slack_token = "{토큰값}"
    client = WebClient(token=slack_token)
    ```
    
    ```bash
    try:
        response = client.chat_postMessage(
            channel="{슬랙 채널 ID}", #채널 id를 입력합니다.
            text="api test~!"
        )
    except SlackApiError as e:
        assert e.response["error"]
    ```