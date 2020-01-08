FROM openjdk:11.0.5-jre-slim

ENV SAMPLE_BUILD_MODE=dockerfile
LABEL PROJECT_NAME=hello-world \
      PROJECT=${project.artifactId}

# Arbitrary files can be added
COPY maven/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8080