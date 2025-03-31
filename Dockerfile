# 使用 OpenJDK 11 作为基础镜像
FROM openjdk:11-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制项目 JAR 包到容器
COPY target/xcode.jar /app/xcode.jar

# 复制默认配置（在宿主机会被挂载覆盖）
COPY config /app/config

# 暴露 8080 端口
EXPOSE 8080

# 运行 Spring Boot 应用，并指定配置文件路径
ENTRYPOINT ["java", "-jar", "/app/xcode.jar", "--spring.config.location=/app/config/application.properties"]
