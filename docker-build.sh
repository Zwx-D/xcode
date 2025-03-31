#!/bin/bash

# 设置 Git 仓库地址
REPO_URL="https://github.com/Zwx-D/xcode.git"
PROJECT_DIR="xcode"

# 克隆或更新代码
if [ ! -d "$PROJECT_DIR" ]; then
    echo "项目不存在，开始克隆代码..."
    git clone $REPO_URL $PROJECT_DIR
else
    echo "项目已存在，拉取最新代码..."
    cd $PROJECT_DIR
    git pull origin main
    cd ..
fi

# 进入项目目录
cd $PROJECT_DIR

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
docker build -t xcode-app .

# 启动容器（使用 docker-compose）
echo "启动 Docker 容器..."
docker-compose up -d --force-recreate

echo "✅ 部署完成！"
