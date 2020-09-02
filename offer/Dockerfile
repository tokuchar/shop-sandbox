FROM openjdk:8-jre-alpine
ADD target/offers-*.jar offers.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "offers.jar"]