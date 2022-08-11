# spring-data-neo4j-rest-api
A REST API using Spring Boot and Spring Data Neo4j.

## Project setup
```
git clone https://github.com/nextzakir/spring-data-neo4j-rest-api.git
cd ./spring-data-neo4j-rest-api
```

### Update/Install dependencies
```
./mvnw clean install -U
```

### To run the application
```
./mvnw spring-boot:run
```

### To compile and package (.jar) for production
```
./mvnw clean package
```

### To run the packaged application
```
/path/to/java -jar ./target/*.jar
```

## Notes

1. After cloning the repository, set up the neo4j database connection values accordingly in the ```./src/main/resources/application.yaml``` file.
2. After running the application, it will be available on <http://localhost:8080>.