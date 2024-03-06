FROM ubuntu:latest
LABEL authors="Основной"

COPY ./build/libs/demo-0.0.1-SNAPSHOT.-0.0.1.jar /opt/service.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://database:5432/intervals
ENV POSTGRES_USER=intervals
ENV POSTGRES_PASSWORD=229csx
EXPOSE 8080
CMD ls
CMD exec java -jar /opt/service.jar