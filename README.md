# Catvie API - Uma API de Ranking de Filmes
![_76d984a9-299e-4fc5-9b2d-26ffa6940d48](https://github.com/01Dri/CatvieSpringBoot/assets/124473653/cb627143-db24-496a-956a-2b6c1fdb0ce3)

## Sobre o Projeto

Catvie API é um projeto criado com o intuito de aprimorar o aprendizado em tecnologias como Spring Boot, PostgreSQL, RESTful APIs e muito mais. Inspirado em plataformas de avaliação de filmes, como IMDb e Rotten Tomatoes, esta API permite o ranking e avaliação de filmes.

## Arquitetura 
Estou estudando a arquitetura hexogonal (leia sobre aqui: link)
Então decidi aplica-la nesse projeto como forma de estudo.

## VARIAVEIS DE AMBIENTE:
Note que alguns arquivos utilizam variaveis de ambiente para a sua configuração. Dessa forma recomendo que criem suas proprias variaveis para o funcionamento correto da aplicação.
Abaixou vou listar arquivos que utilizam essas variaveis.
- **Configuração e chaves de acesso AWS SES**: Para utilizar o serviços de envio de email SES, é necessario configurar suas variaveis de ambiente contendo os valores das chaves de acesso da AWS.
- Path: application.properties ![image](https://github.com/01Dri/CatvieSpringBoot/assets/124473653/e4d8d932-1912-4a9b-8948-0db1684698ac)
- **Senha para acesso ao banco de dados**: Para configurar o banco de dados, utilizei uma variavel de ambiente, você pode setar manualmente ou utilizar uma variavel.
- Path: application.properties ![image](https://github.com/01Dri/CatvieSpringBoot/assets/124473653/70e6c52e-471e-486a-8e5d-363094fb9860)
- ** Chave SECRET para utilizar token service JWT**: Para criptografar e descriptografar token JWT, é necessário uma chave secret, você precisar setar em uma variavel de ambiente no sistema.
- Path: infra.config.SecurityConfiguration.java ![image](https://github.com/01Dri/CatvieSpringBoot/assets/124473653/d5d07079-c94a-4abe-8b06-6d843c230339)
  
## Instalaçoes obrigatórias
 - **JAVA SDK**: Antes de tudo, é necessário instalar JDK da Oracle[https://www.oracle.com/java/technologies/downloads/], nessa aplicação estou utilizando o Java 21 e Spring Boot 3, recomendo utilizar a mesma versão para um bom funcionamento.
 - **APACHE MAVEN**: Após isso, é necessário também instalar o APACHE MAVEN[https://maven.apache.org/download.cgi], gerenciador de dependencias do projeto.
 - **DOCKER**: O projeto suporta Docker, então para utiliza-lo é necessário instala-lo a partir deste link[https://www.docker.com/].
   
## Como iniciar
- **Para iniciar a aplicação, basta apenas chamar o comando no terminal desta forma: ``` mvn spring-boot:run ```
## Tecnologias Utilizadas

Catvie API é construída com um conjunto de tecnologias modernas:

- **[Spring Boot](https://spring.io/projects/spring-boot)**: Utilizado como framework para desenvolver a aplicação Java de forma eficiente e simplificada.
- **[PostgreSQL](https://www.postgresql.org)**: Banco de dados relacional utilizado para armazenar informações dos filmes e avaliações.
- **JUnit**: Utilizado para realizar testes unitários.
- **Mockito**: Utilizado para simular comportamentos de objetos. 
- **RestAssured**: Utilizado para realizar testes de integração.
- **Flyway**: Utilizado para gerenciar as migrações do banco de dados, mantendo-o atualizado junto com a evolução da aplicação.
- **Spring Security**: Responsável pela segurança da aplicação.
- **Autenticação com JWT Token**: Implementa autenticação baseada em tokens JWT para proteger as rotas da API.
- **Documentação com Swagger OpenAPI**: Gera documentação interativa da API, tornando mais fácil o entendimento e teste das funcionalidades.
  **Docker e Docker-Compose**: Usados para a conteinerização do sistema e garantir o isolamento e a performance do sistema.

## Documentação (Swagger OpenAPI)

Acesse a documentação da API em tempo real usando o Swagger OpenAPI. Basta acessar: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

- ![2023-12-18 21-12-44](https://github.com/01Dri/CatvieSpringBoot/assets/124473653/d15d0c46-da03-427f-8350-c88efea92cf3)


## Estado Atual do Projeto

O projeto ainda está em desenvolvimento.

## Contribuição
Este projeto é totalmente voltado para aprendizado e experimentação. Se você encontrar bugs ou tiver sugestões de melhorias, sinta-se à vontade para clonar o repositório, implementar melhorias e enviar suas dicas!

