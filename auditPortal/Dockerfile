FROM openjdk:latest

ADD target/auditPortal-0.0.1-SNAPSHOT.jar docker-portal.jar

ENTRYPOINT ["java","-jar","docker-portal.jar"]

EXPOSE 8085