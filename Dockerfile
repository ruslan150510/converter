FROM openjdk:14-jdk-alpine
MAINTAINER Akbashev Ruslan
COPY target/converter-0.0.1-SNAPSHOT.jar converter.jar
ENTRYPOINT ["java", "-jar", "/converter.jar"]