FROM openjdk:latest

ADD target/auditBenchmark-0.0.1-SNAPSHOT.jar docker-benchmark.jar

ENTRYPOINT ["java","-jar","docker-benchmark.jar"]

EXPOSE 8082