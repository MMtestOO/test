FROM openjdk:11-jdk as builder
ARG JAR_FILE=./mm.jar
ENV TZ=Asia/Seoul
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
