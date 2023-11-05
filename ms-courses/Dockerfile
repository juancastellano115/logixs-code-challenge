# Use a lightweight Java runtime
FROM amazoncorretto:21-alpine-jdk

ARG JAR_FILE=target/*.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8083