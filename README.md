Projeto Fabel 

O nosso projeto Fabel é uma aplicação Java desenvolvida com Spring Boot, utilizando frameworks como Spring DATA JPA, Spring Security, Spring Web, Spring Test que gerencia entidades como jogos, lojas e usuários. A aplicação inspirada na plataforma Steam, representa um processo de registro, compra e instalação de jogos de maneira objetiva e simples. 

 

Estrutura do Projeto 

Arquivos principais 

default.env: Arquivo com variáveis de ambiente padrão. 

docker: Configuração do ambiente Docker. 

docker-compose.yml: Configuração para orquestrar os containers. 

Dockerfile: Receita para construção da imagem Docker. 

mvnw e mvnw.cmd: Wrappers do Maven para facilitar a execução. 

pom.xml: Configuração de dependências e plugins do Maven. 

Arquivos .http: 

requestGame.http, requestStore.http e requestUser.http: Requisições de exemplo para testar os endpoints da API. 

Imagens PNG: Exemplos visuais (homem-ferro.png e png-homem-aranha.png). 

Estrutura de Código 

src/main 

java/com/example/fabel 

controller: 

DatabaseController.java: Configuração inicial do banco de dados. 

GameController.java: Endpoints para gerenciamento de jogos. 

StoreController.java: Endpoints para gerenciamento de lojas. 

UserController.java: Endpoints para gerenciamento de usuários. 

model: 

Games.java: Entidade representando jogos. 

Store.java: Entidade representando lojas. 

Users.java: Entidade representando usuários. 

repository: 

Interfaces para comunicação com o banco de dados: 

GameRepository.java 

StoreRepository.java 

UserRepository.java 

Security: 

Security.java: Configuração de segurança da aplicação. 

FabelApplication.java: Classe principal que inicia a aplicação. 

resources 

application.properties: Configurações da aplicação (como banco de dados e servidor). 

src/test 

java/com/example/fabel 

Testes unitários e de integração: 

FabelApplicationTests.java: Testes gerais. 

GameControllerTest.java, StoreControllerTest.java, UserControllerTest.java: Testes para os controladores. 

 

Funcionalidades 

Gerenciamento de Jogos: Criar, listar, atualizar e excluir jogos. 

Gerenciamento de Lojas: Gerenciar informações sobre lojas que “vendem” os jogos. 

Gerenciamento de Usuários: CRUD de usuários e autenticação. 

Segurança: Configuração básica de autenticação/segurança. 

 

Requisitos 

Java 17+ 

Maven 

Docker (opcional, para execução em containers) 

 

Como Executar 

Clonar o Repositório: 

bash 

Copiar código 

git clone <URL_DO_REPOSITORIO> 
cd fabel 
 

Configurar Variáveis de Ambiente: 

Edite o arquivo default.env com os valores necessários. 

Compilar e Executar: 

bash 

Copiar código 

./mvnw spring-boot:run 
 

Executar via Docker (opcional): 

bash 

Copiar código 

docker-compose up --build 
 

Acessar a Aplicação: 

Endpoints disponíveis em: http://localhost:8080 

 

Testes 

Executar os testes: 

bash 

Copiar código 

./mvnw test 
 

 

Participantes 

Nome: Renan Cesar Silveira / RA:12523216913 

  

Nome: Julia Freitas Nascimento / RA:12523214930 

  

Nome: Lucas Machado da Silva / RA:1252325224 

  

Nome: Stefani Santos Dias / RA:1252326904 

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
