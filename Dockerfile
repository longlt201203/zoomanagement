FROM openjdk:17-alpine
WORKDIR /app
COPY ./out/artifacts/ZooManagement_jar .
EXPOSE 8080
CMD ["java", "-jar", "ZooManagement.jar"]