FROM openjdk:8

# Add Maintainer Info
LABEL maintainer="pparsuram15@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8200 available to the world outside this container
EXPOSE 8200

# The application's jar file
ARG JAR_FILE=target/OrderMethodApp-0.0.1-SNAPSHOT.jar


ADD ${JAR_FILE} OrderMethodApp.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/OrderMethodApp.jar"]