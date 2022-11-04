FROM openjdk:8-jre-alpine
EXPOSE 8080:8080
WORKDIR /miniautorizador
COPY target/miniautorizador-0.0.1-SNAPSHOT.jar .
ENTRYPOINT [ "java", "-jar", "miniautorizador-0.0.1-SNAPSHOT.jar" ]