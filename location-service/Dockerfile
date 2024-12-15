# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build artifact (JAR) into the container
COPY target/location-service.jar location-service.jar

# Expose the port the app runs on
EXPOSE 9091

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "location-service.jar"]
