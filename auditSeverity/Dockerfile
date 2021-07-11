FROM openjdk:latest

ADD target/auditSeverity-0.0.1-SNAPSHOT.jar docker-severity.jar

ENTRYPOINT ["java","-jar","docker-severity.jar"]

EXPOSE 8083