FROM openjdk:11.0.11-oracle

MAINTAINER fleonardelli

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY build.gradle settings.gradle gradlew $APP_HOME

COPY gradle $APP_HOME/gradle

RUN ./gradlew

COPY . .

RUN ./gradlew clean build

ENTRYPOINT ["java", "-jar", "/usr/app/build/libs/product-search-0.0.1-SNAPSHOT.jar"]
