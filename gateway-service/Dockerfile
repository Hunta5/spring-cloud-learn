# ===== 第一阶段：构建 =====
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml /build/pom.xml
COPY common /build/common
COPY gateway-service /build/gateway-service

RUN mvn -f /build/pom.xml -N install
RUN mvn -f /build/pom.xml install -pl common -DskipTests
RUN mvn -f /build/pom.xml package -pl gateway-service -DskipTests

# ===== 第二阶段：运行 =====
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /build/gateway-service/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
