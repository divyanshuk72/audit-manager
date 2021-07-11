FROM openjdk:latest

ADD target/auditChecklist-0.0.1-SNAPSHOT.jar docker-checklist.jar

ENTRYPOINT ["java","-jar","docker-checklist.jar"]

EXPOSE 8081