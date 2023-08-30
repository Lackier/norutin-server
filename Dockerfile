# Use the official Gradle Alpine image as the base image
FROM gradle:6.8-jdk15

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Expose the port your application will listen on
EXPOSE 8080

# Command to run your Spring Boot application
CMD ["gradle", "flywayMigrate", "bootRun"]
