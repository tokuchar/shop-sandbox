FROM openjdk:8-jre-alpine
ADD target/shop-service-discovery-*.jar shop-service-discovery.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "shop-service-discovery.jar"]