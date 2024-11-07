# Fabel

### Prerequisites
- Have java installed 
    - to check the installation:
    ```bash
        java -version
     ``` 
- Have maven installed
    - to check the installation:
    ```bash 
        mvn -version 
    ``` 
___

### How to run the project


1. cleans up old files, compiles the code, runs the tests, and installs the artifact in the local repository.
    ```bash
    mvn clean install
    ```

2. runs a Spring Boot application directly from the source code, without needing to package the project in a .jar file first. It compiles and starts the application.
    ```bash
    mvn spring-boot:run
    ```
___

