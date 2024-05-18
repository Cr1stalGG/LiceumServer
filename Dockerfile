FROM maven:3.9.6-sapmachine-17 as build

COPY pom.xml .
COPY src src

RUN mvn package -DskipTests

FROM openjdk:20

COPY --from=build ./target/LiceumServer-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]