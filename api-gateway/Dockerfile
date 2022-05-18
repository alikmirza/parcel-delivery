FROM openjdk:11.0.7-jre-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /home/api-gateway-0.0.1.jar
ENTRYPOINT ["java","-jar","/home/api-gateway-0.0.1.jar"]

