# DUFF Project
Project made as a technical part for a job selection for [Ciclic](ciclic.com.br)

It consists in a REST Microservice for finding the ideal beer style 
for a desired temperature and a playlist matching this beer.

## Requirements
- Maven
- Docker
- Java 8+

## Building

### Creating Database
```(bash)
$ docker run -d -p 1521:1521 --name duff-database wnameless/oracle-xe-11g
$ docker start duff-database
```

### Maven Build
```(bash)
$ cd $(PROJECT_HOME)
$ mvn clean install
```

### Running
```(bash)
$ cd $(PROJECT_HOME)
$ java -jar duff-backend/target/duff-backend-0.0.1.jar 
```

### Edit settings 
- Edit the file `$(PROJECT_HOME)/application.properties` to edit server port
- Edit the file `$(PROJECT_HOME/duff-backend/pom.xml` to edit backend settings
- Edit the file `$(PROJECT_HOME/duff-database/pom.xml` to edit flyway settings