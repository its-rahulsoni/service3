# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set timezone (e.g., Asia/Kolkata)
ENV TZ=Asia/Kolkata

# Set the working directory in the container
WORKDIR /app

# Add the Spring Boot JAR file to the container
COPY target/service3-0.0.1-SNAPSHOT.jar /app/service3-0.0.1-SNAPSHOT.jar

# Expose the port that the app runs on (usually 8080 for Spring Boot)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/service3-0.0.1-SNAPSHOT.jar"]