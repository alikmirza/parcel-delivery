FROM openjdk:11.0.7-jre-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /home/courier-0.0.1.jar
ENTRYPOINT ["java","-jar","/home/courier-0.0.1.jar"]

