#FROM openjdk:17
FROM bellsoft/liberica-openjdk-alpine-musl:17
VOLUME /tmp
COPY target/bot-app-1.0-SNAPSHOT.jar bot-app.jar
ENTRYPOINT ["java","-jar","/bot-app.jar"]