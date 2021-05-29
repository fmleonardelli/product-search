FROM openjdk:11.0.11-oracle

MAINTAINER fleonardelli

RUN mkdir /app

COPY build/libs/*.jar /app/product-search.jar

ENTRYPOINT ["java", "-jar", "/app/product-search.jar"]