FROM alpine:edge
MAINTAINER bastiaan
RUN apk add --no-cache openjdk11
COPY target/guitarshop-api-0.0.2-SNAPSHOT.jar /app/app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]