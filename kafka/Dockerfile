FROM openjdk:15-jdk-alpine
EXPOSE 9080
ADD /target/kafka-0.0.1-SNAPSHOT.jar kafkatest.jar
ENTRYPOINT ["java","-jar","kafkatest.jar"]