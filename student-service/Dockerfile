# ===== 第一阶段：构建 =====
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# 设置工作目录
WORKDIR /build

# 先复制父 pom.xml
COPY pom.xml /build/pom.xml

# 复制 common 模块（student-service 依赖它）
COPY common /build/common

# 复制 student-service 模块
COPY student-service /build/student-service

# 构建：先安装 common，再打包 student-service
RUN mvn -f /build/pom.xml install -pl common -am -DskipTests
RUN mvn -f /build/pom.xml package -pl student-service -DskipTests

# ===== 第二阶段：运行 =====
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# 从第一阶段复制打好的 jar
COPY --from=builder /build/student-service/target/*.jar app.jar

# 暴露端口
EXPOSE 8081

# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]
