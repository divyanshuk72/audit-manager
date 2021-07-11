FROM openjdk:latest

ADD target/eurekaServer-0.0.1-SNAPSHOT.jar docker-eureka.jar

ENTRYPOINT ["java","-jar","docker-eureka.jar"]

EXPOSE 8761