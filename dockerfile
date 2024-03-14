FROM  maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY ./pom.xml ./pom.xml

COPY ./src ./src

RUN mvn clean package  -DskipTests

FROM openjdk:23-ea-13-jdk-oraclelinux8

WORKDIR /app

COPY --from=build /app/target/Resumlik-0.0.1.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
