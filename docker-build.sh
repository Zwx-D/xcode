#!/bin/bash

# 获取时间戳
TIMESTAMP=$(date +"%Y%m%d%H%M%S")

# 镜像名称
IMAGE_NAME="build_xcode_${TIMESTAMP}"

echo "Building Docker image: $IMAGE_NAME"

# 设置 Git 仓库地址
REPO_URL="https://github.com/Zwx-D/xcode.git"
PROJECT_DIR="xcode"

# 更新代码
git pull

# 运行 Maven 构建
echo "开始编译 Spring Boot 项目..."
mvn clean package -DskipTests

# 确保 JAR 包存在
JAR_FILE="target/xcode.jar"
if [ ! -f "$JAR_FILE" ]; then
    echo "❌ JAR 包未生成，构建失败！"
    exit 1
fi

# 构建 Docker 镜像
echo "开始构建 Docker 镜像..."
docker build -t $IMAGE_NAME .

# 启动容器（使用 docker-compose）
echo "启动 Docker 容器..."
docker-compose up -d --force-recreate

echo "✅ 部署完成！"
