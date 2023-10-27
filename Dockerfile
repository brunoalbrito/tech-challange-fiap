FROM openjdk:17-alpine
WORKDIR /target
MAINTAINER tech-challenge
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]