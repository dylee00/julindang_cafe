FROM openjdk:17-jdk
WORKDIR /api-server
COPY ./build/libs/*.jar /api-server/server.jar
CMD ["sh", "-c", "ls && java -jar server.jar"]
