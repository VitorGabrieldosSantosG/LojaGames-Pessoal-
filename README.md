# Games - API de Jogos

Este é um projeto Spring Boot para gerenciamento de jogos e usuários. A aplicação fornece uma API REST para operações CRUD em jogos e usuários.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal
- **Spring Boot**: Framework para desenvolvimento de aplicações Java
- **Maven**: Gerenciador de dependências e build
- **Spring Data JPA**: Para persistência de dados
- **H2 Database**: Banco de dados em memória para desenvolvimento (pode ser configurado para outros bancos)

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- IDE de sua preferência (recomendado: IntelliJ IDEA, Eclipse ou VS Code)

## Instalação e Configuração

1. **Clone o repositório**:
   ```
   git clone <url-do-repositorio>
   cd Games
   ```

2. **Compile o projeto**:
   ```
   mvn clean compile
   ```

3. **Execute a aplicação**:
   ```
   mvn spring-boot:run
   ```

A aplicação estará rodando em `http://localhost:8080`.

## Estrutura do Projeto

- **Controller**: Camada de controle para endpoints REST
  - `GameController.java`: Endpoints para jogos
  - `UserController.java`: Endpoints para usuários
- **DTO**: Objetos de Transferência de Dados
  - `GameDTO.java`
  - `UserDTO.java`
- **Model**: Entidades do banco de dados
  - `GameModel.java`
  - `UserModel.java`
- **Repository**: Interfaces para acesso a dados
  - `GameRepository.java`
  - `UserRepository.java`
- **Util**: Utilitários
  - `Utils.java`

## Endpoints da API

### Jogos

- `GET /games`: Lista todos os jogos
- `GET /games/{id}`: Busca um jogo por ID
- `POST /games`: Cria um novo jogo
- `PUT /games/{id}`: Atualiza um jogo
- `DELETE /games/{id}`: Deleta um jogo

### Usuários

- `GET /users`: Lista todos os usuários
- `GET /users/{id}`: Busca um usuário por ID
- `POST /users`: Cria um novo usuário
- `PUT /users/{id}`: Atualiza um usuário
- `DELETE /users/{id}`: Deleta um usuário

## Configuração do Banco de Dados

Por padrão, a aplicação usa H2 Database em memória. Para alterar, edite o arquivo `src/main/resources/application.properties`.

Exemplo para PostgreSQL:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/games
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Testes

Para executar os testes:
```
mvn test
```

## Próximos passos

Este projeto está em desenvolvimento, porém as próximas etapas, são desenvolver outras classes para gerenciamento de pedidos, adição de front end, etc.