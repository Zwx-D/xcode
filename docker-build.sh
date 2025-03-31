#!/bin/bash

# 获取时间戳
TIMESTAMP=$(date +"%Y%m%d%H%M%S")
IMAGE_NAME="build_xcode_${TIMESTAMP}"
CONTAINER_NAME="xcode_app"

echo "Building Docker image: $IMAGE_NAME"

# 设置 Git 仓库地址
REPO_URL="https://github.com/Zwx-D/xcode.git"
PROJECT_DIR="xcode"

# 更新代码
git pull

echo "============================="
echo "🛠 检查 & 安装 JDK11..."
echo "============================="

echo "============================="
echo "🚀 开始构建 Spring Boot 项目..."
echo "============================="

# 1️⃣ 清理 & 构建 Spring Boot JAR
./gradlew clean build -x test || { echo "❌ Gradle 构建失败！"; exit 1; }

echo "============================="
echo "📦 构建 Docker 镜像: $IMAGE_NAME"
echo "============================="

# 2️⃣ 构建 Docker 镜像
docker build -t $IMAGE_NAME . || { echo "❌ Docker 构建失败！"; exit 1; }

echo "============================="
echo "🛑 停止 & 删除旧容器"
echo "============================="

# 3️⃣ 停止 & 删除旧容器
docker stop $CONTAINER_NAME 2>/dev/null
docker rm $CONTAINER_NAME 2>/dev/null

echo "============================="
echo "🚀 启动新容器"
echo "============================="

docker run -d \
  --name $CONTAINER_NAME \
  -p 8080:8080 \
  -v /opt/backend/xcode/config:/config \
  -v /opt/backend/xcode/files:/app/file/upload \
  --restart unless-stopped \
  $IMAGE_NAME || { echo "❌ 容器启动失败！"; exit 1; }


echo "============================="
echo "✅ 部署完成！服务运行中..."
echo "============================="
