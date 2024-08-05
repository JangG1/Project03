name: Frontend CI

on:
  push:
    branches:
      - master  # master 브랜치에 푸시할 때 트리거
  pull_request:
    branches:
      - master  # master 브랜치에 PR을 보낼 때 트리거

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v2

      - name: Set up Node.js
        uses: actions/setup-node@v2
        with:
          node-version: '14'  # 사용할 Node.js 버전

      - name: Install dependencies
        run: |
          cd frontend  # Vue 애플리케이션 폴더로 이동
          npm install

      - name: Run tests
        run: |
          cd frontend
          npm run test  # 테스트 실행

      - name: Build
        run: |
          cd frontend
          npm run build  # 빌드 실행
