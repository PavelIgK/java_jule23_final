#FROM openjdk:17
FROM bellsoft/liberica-openjdk-alpine-musl:17
VOLUME /tmp
COPY target/schedule-service-1.0-SNAPSHOT.jar schedule-service.jar
ENTRYPOINT ["java","-jar","/schedule-service.jar"]