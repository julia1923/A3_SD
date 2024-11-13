# Fabel

### Prerequisites
- Have java installed 
    - to check the installation:
    ```bash
        java -version
     ``` 
    - Install java in codespace
    ```bash
        sdk install java 21.0.5-oracle
    ```
- Have maven installed
    - to check the installation:
    ```bash 
        mvn -version 
    ``` 
___

### How to run the project without docker


1. cleans up old files, compiles the code, runs the tests, and installs the artifact in the local repository.
    ```bash
    mvn clean install
    ```

2. runs a Spring Boot application directly from the source code, without needing to package the project in a .jar file first. It compiles and starts the application.
    ```bash
    mvn spring-boot:run
    ```
___

### How to run the project with docker

1. Raises all the services necessary to run the program with docker compose
```bash
    docker compose -f docker/docker-compose.yml up  
```

### How to connect with database
1. using mysql client
    - To install 
    - ``` sudo apt install mysql-client```
    - To Connect 
    - ``` mysql -h [host] -u [user] -p ```
    
2. by container
    - ``` docker exec -it [container_name] mysql -u root -p```