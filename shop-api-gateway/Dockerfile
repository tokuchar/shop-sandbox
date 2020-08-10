FROM openjdk:8-jre-alpine
ADD target/shop-api-gateway-*.jar shop-api-gateway.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "shop-api-gateway.jar"]