FROM openjdk:8-jre-alpine
ADD target/customer-*.jar customer.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "customer.jar"]