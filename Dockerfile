FROM ubuntu:latest
FROM openjdk:17-jdk-alpine
LABEL authors="Основной"

COPY ./build/libs/demo-0.0.1-SNAPSHOT.jar /opt/service.jar
EXPOSE 8080
CMD ls
CMD exec java -jar /opt/service.jar