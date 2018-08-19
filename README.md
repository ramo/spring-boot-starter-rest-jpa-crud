# spring-boot-starter-rest-jpa-crud

Sample starter project for performing CRUD operations in Spring Boot with RESTful and JPA

### Prerequisites
- Install mysql 5.7.22 server
- DB properties need to be added in application.properties file

### Run Test cases

- cd to root of the project
- Run the below maven command
```  
mvn test
```

### Start the application server

- cd to root of the project 
- Run the below maven command
```
mvn spring-boot:run
```

### Alternate way to package and run the server

- cd to root of the project
- Run the below maven command
``` 
mvn package
```
- cd to target directory
- Run the below command 
```
java -jar spring-boot-starter-rest-jpa-crud-0.0.1-SNAPSHOT.jar
```

### Swagger Documentation
Swagger documentation is available in below URL.

```
http://localhost:8080/swagger-ui.html
```
