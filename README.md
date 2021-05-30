# Product-Search API

 The actual objetive of Product Seach Api is to make a service available to check the current price for a product in specified date.
 
### Tecnologies

- Java Development Kit 11 Oracle.
- Spring Boot Framework: Web, Data, Actuator.
- Gradle.
- Vavr: Functional Features.
- H2 database (for DEV scope).
- MySql 8 (for STAGE scope).
- Flyway: DB Control Version
- Docker-Compose (for stage scope)
- Jacoco: test coverage

### Build

- **Dev Scope**: this profile connects to the h2 database.
For run locally, in the root folder project:

./gradlew bootRun

- **Stage Scope**: this profile was intended as the productive (also to manage profiles within the app). For this reason, docker-compose is to coordinate the api with mysql database.
Anyway, it can be build locally with docker compose installed. In te root folder project:

docker-compose up

### Execution/Api Doc

The app when run locally use the 8080 port.

- HealthCheck: {host}/product-search/health
- Api doc (Swagger): {host}/product-search/swagger-ui/

### Notes
- Clean Arquitecture is used.
- This app use Vavr for use the Monads Either and Option.
- The nature of the problem applies to the use of a non-relation db maybe Mongo would be the most appropiate, but it was decided not to implement(like other features) it to comply with the exercise.



