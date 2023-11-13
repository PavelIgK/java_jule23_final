#FROM openjdk:17
FROM bellsoft/liberica-openjdk-alpine-musl:17
VOLUME /tmp
COPY target/web-app-service-1.0-SNAPSHOT.jar web-app-service.jar
ENTRYPOINT ["java","-jar","/web-app-service.jar"]