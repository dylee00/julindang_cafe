ARG TARGET_PLATFORM
FROM --platform=${TARGET_PLATFORM} openjdk:17-jdk
WORKDIR /api-server
COPY ./build/libs/*.jar /api-server/server.jar
ENV JAVA_OPTS "-Xms256m -Xmx512m"
