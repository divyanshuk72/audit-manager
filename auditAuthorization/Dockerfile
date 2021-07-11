FROM openjdk:latest

ADD target/auditAuthorization-0.0.1-SNAPSHOT.jar docker-authorization.jar

ENTRYPOINT ["java","-jar","docker-authorization.jar"]

EXPOSE 8084