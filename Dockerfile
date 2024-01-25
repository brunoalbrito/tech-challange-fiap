#gradlegit push --set-upstream origin feat/combo-clean-architecture
FROM gradle:8.2.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

#container
MAINTAINER tech-challenge
FROM openjdk:17-alpine
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/tech-challenge-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]