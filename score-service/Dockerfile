# ===== 第一阶段：构建 =====
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml /build/pom.xml
COPY common /build/common
COPY score-service /build/score-service

RUN mvn -f /build/pom.xml -N install
RUN mvn -f /build/common/pom.xml install -DskipTests
RUN mvn -f /build/score-service/pom.xml package -DskipTests

# ===== 第二阶段：运行 =====
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /build/score-service/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
