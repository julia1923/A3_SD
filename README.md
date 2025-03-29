# Fable

### PREREQUISITES

- Java

To install Java in your codespace, run the following command:
```bash
    sdk install java 21.0.5-oracle
```
Check if the installation was sucessful by running:
    ```bash
        java -version
     ``` 

- REST Client
Make sure you have REST Client extension installed.
___

### ENVIRONMENT SETUP

1.Copy the default.env file to the root of the project directory.
2. Rename the copied file to .env.
3. Open .env file and fill the required values. You can use placeholder values, but ensure you provide valid information

4. Open the application.properties file.
5. Change the properties in application.properties to match the same variables defined in the .env file.
___

### RUNNING THE PROJECT

1. In your terminal, run the following command to start the necessary containers:
```
    docker compose -f docker/docker-compose.yml up
```

2. Open another terminal and type:
```
    mvn spring-boot:run
```
