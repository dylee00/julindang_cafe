#!/bin/zsh
# 운영체제의 아키텍처 감지
PLATFORM=$(uname -m)

# 운영체제에 따라 플랫폼 값 설정
if [ "$PLATFORM" = "x86_64" ]; then
  PLATFORM_VALUE="linux/amd64"
elif [ "$PLATFORM" = "aarch64" ]; then
  PLATFORM_VALUE="linux/arm64"
elif [ "$PLATFORM" = "arm64" ]; then
  PLATFORM_VALUE="linux/arm64"
else
  PLATFORM_VALUE="linux/amd64"  # 기본 값
fi

# .env 파일에 저장
echo "PLATFORM=$PLATFORM_VALUE" > .env
