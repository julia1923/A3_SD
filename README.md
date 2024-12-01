# Projeto Fabel

O nosso projeto Fabel é uma aplicação Java desenvolvida com Spring Boot, utilizando frameworks como Spring DATA JPA, Spring Security, Spring Web, Spring Test que gerencia entidades como jogos, lojas e usuários. A aplicação inspirada na plataforma Steam, representa um processo de registro, compra e instalação de jogos de maneira objetiva e simples.

## Estrutura do Projeto

### Arquivos principais

- **default.env**: Arquivo com variáveis de ambiente padrão.
- **docker**: Configuração do ambiente Docker.
- **docker-compose.yml**: Configuração para orquestrar os containers.
- **Dockerfile**: Receita para construção da imagem Docker.
- **mvnw e mvnw.cmd**: Wrappers do Maven para facilitar a execução.
- **pom.xml**: Configuração de dependências e plugins do Maven.

### Arquivos .http

- **requestGame.http, requestStore.http e requestUser.http**: Requisições de exemplo para testar os endpoints da API.

## Estrutura de Código

### src/main

#### java/com/example/fabel

##### Controller

- **DatabaseController.java**: Configuração inicial do banco de dados.
- **GameController.java**: Endpoints para gerenciamento de jogos.
- **StoreController.java**: Endpoints para gerenciamento de lojas.
- **UserController.java**: Endpoints para gerenciamento de usuários.

##### Model

- **Games.java**: Entidade representando jogos.
- **Store.java**: Entidade representando lojas.
- **Users.java**: Entidade representando usuários.

##### Repository

Interfaces para comunicação com o banco de dados:

- **GameRepository.java**
- **StoreRepository.java**
- **UserRepository.java**

##### Security

- **Security.java**: Configuração de segurança da aplicação.

- **FabelApplication.java**: Classe principal que inicia a aplicação.

#### resources

- **application.properties**: Configurações da aplicação (como banco de dados e servidor).

### src/test

#### java/com/example/fabel

Testes unitários e de integração:

- **FabelApplicationTests.java**: Testes gerais.
- **GameControllerTest.java, StoreControllerTest.java, UserControllerTest.java**: Testes para os controladores.

## Funcionalidades

- **Gerenciamento de Jogos**: Criar, listar, atualizar e excluir jogos.
- **Gerenciamento de Lojas**: Gerenciar informações sobre lojas que “vendem” os jogos.
- **Gerenciamento de Usuários**: CRUD de usuários e autenticação.
- **Segurança**: Configuração básica de autenticação/segurança.

## Requisitos

- Java 17+
- Maven
- Docker (opcional, para execução em containers)

## Como Executar

### Configurar Variáveis de Ambiente:
- Edite o arquivo `default.env` com os valores necessários.

### Compilar e Executar:
./mvnw spring-boot:run

### Executar os Testes
mvn test

## Participantes

- **Renan Cesar Silveira** - RA: 12523216913
- **Julia Freitas Nascimento** - RA: 12523214930
- **Lucas Machado da Silva** - RA: 1252325224
- **Stefani Santos Dias** - RA: 1252326904

