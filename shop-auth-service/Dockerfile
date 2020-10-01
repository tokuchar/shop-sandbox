FROM openjdk:8-jre-alpine
ADD target/shop-auth-service-*.jar shop-auth-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "shop-auth-service.jar"]