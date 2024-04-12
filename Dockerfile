FROM openjdk:17-jdk-slim
COPY build/libs/demo-0.0.1-SNAPSHOT.jar /opt/service.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://database:5432/intervals
ENV POSTGRES_USER=intervals
ENV POSTGRES_PASSWORD=229csx
CMD exec java -jar /opt/service.jar