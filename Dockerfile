# 使用 OpenJDK 11 作为基础镜像
FROM openjdk:11-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制 JAR 包（确保 Gradle 构建时 `bootJar` 生成了 `app.jar`）
COPY build/libs/app.jar /app/app.jar

# 设置配置文件路径（让 Spring Boot 读取外部 `application.yml`）
VOLUME ["/config"]
ENV SPRING_CONFIG_LOCATION=/config/application.yml


# 创建上传文件存储目录（用于持久化）
RUN mkdir -p /app/file/upload

# 挂载上传文件目录
VOLUME ["/app/file/upload"]

# 暴露 8080 端口
EXPOSE 8080

# 运行 Spring Boot 应用，并指定配置文件路径
CMD ["java", "-jar", "/app/app.jar"]
