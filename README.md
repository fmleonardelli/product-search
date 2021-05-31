# [Product-Search API](https://product-price-search.herokuapp.com/product-search/swagger-ui/)

 The actual objetive of Product Seach Api is to make a service available to check the current price for a product in specified date.
 
 - [Product Search Heroku](https://product-price-search.herokuapp.com/product-search/swagger-ui/)

 
### Tecnologies

- [Java Development Kit 11 Oracle](https://www.oracle.com/ar/java/technologies/javase-jdk11-downloads.htmlhttps://www.oracle.com/ar/java/technologies/javase-jdk11-downloads.html).
- [Spring Boot](https://spring.io/projects/spring-boot) : Web, Data, Actuator, Test.
- [Gradle](https://gradle.org/).
- [Vavr](https://www.vavr.io/): Functional Features.
- H2 database (for DEV scope).
- MySql 8 (for STAGE scope).
- Flyway: DB Control Version
- Docker-Compose (for stage scope)
- Jacoco: test coverage

### Build

- **Dev Scope**: this profile connects to the h2 database. **This profile is used locally and the version installed in Heroku**.
 
For run locally, in the root folder project:

```
./gradlew build

./gradlew bootRun
```


- **Stage Scope**: this profile was intended as the pre-productive (also to manage profiles within the app). For this reason, docker-compose is to coordinate the api with mysql database.
Anyway, it can be build **locally with docker compose installed**. In te root folder project:

```
docker-compose up
```

### Execution/Api Doc
The app when run locally use the 8080 port.

- HealthCheck: {host}/product-search/health
- Api doc (Swagger): {host}/product-search/swagger-ui/

### Notes
- Clean Arquitecture is used.
- Vavr for use the Monads Either and Option.
- The nature of the problem applies to the use of a non-relation db maybe Mongo would be the most appropiate, but it was decided not to implement(like other features) it to comply with the exercise.

### Contributors
- **Facundo Leonardelli**: facundoleonardelli@gmail.com 



