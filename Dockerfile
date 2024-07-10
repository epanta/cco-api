FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/cco-api-0.0.1-SNAPSHOT.jar /app/laudos-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "laudos-api.jar"]
