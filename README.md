# Gerenciador de Pagamentos de Futebol - API REST

API REST desenvolvida em Java para um sistema simplificado de gerenciamento de pagamentos de mensalistas em jogos de futebol.
Este projeto foi realizado como parte do Trabalho 02 da disciplina de Desenvolvimento Web pelos alunos: Fabio Gabriel Ivancheski Fagundes, Gabriel de Azevedo Zanini e Maria Eduarda Oliveira Machado.
O sistema utiliza o ecossistema Spring (Spring Boot, Spring MVC, Spring Data JPA) e segue o padrão arquitetural MVC.
O foco é a disponibilização de endpoints para o cadastro e consulta de jogadores e seus respectivos pagamentos, não incluindo interface gráfica.

## Tecnologias Utilizadas

* **Java:** JDK 20 (ou a versão configurada no `pom.xml`)
* **Spring Boot:** (v3.2.5 ou a versão utilizada no `pom.xml`)
    * **Spring MVC:** Para a construção da API REST.
    * **Spring Data JPA:** Para persistência de dados e interação com o banco de dados.
* **Hibernate:** Como provedor JPA padrão do Spring Data JPA.
* **Maven:** Para gerenciamento de dependências e build do projeto.
* **H2 Database:** Banco de dados em memória utilizado para desenvolvimento (configurável para outros bancos).

## Funcionalidades Principais

* **Gerenciamento de Jogadores:**
    * Cadastrar novos jogadores (nome, email, data de nascimento).
    * Listar todos os jogadores cadastrados.
    * Buscar um jogador específico por seu código.
    * Atualizar dados de um jogador existente.
    * Deletar um jogador.
* **Gerenciamento de Pagamentos:**
    * Registrar um novo pagamento para um jogador (ano, mês, valor).
    * Listar todos os pagamentos registrados.
    * Listar todos os pagamentos de um jogador específico.
    * Buscar um pagamento específico por seu código.
    * Atualizar dados de um pagamento existente.
    * Deletar um pagamento.

## Estrutura do Projeto

O projeto segue uma estrutura de pacotes organizada para separar responsabilidades, dentro do pacote base `app.futebol`:

* `app.futebol.FutebolApplication.java`: Classe principal que inicializa a aplicação Spring Boot.
* `app.futebol.model`: Contém as entidades JPA (`Jogador.java`, `Pagamento.java`) que representam os dados da aplicação[cite: 3].
* `app.futebol.repository`: Interfaces do Spring Data JPA (`JogadorRepository.java`, `PagamentoRepository.java`) responsáveis pela interação com o banco de dados.
* `app.futebol.service`: Classes de serviço (`JogadorService.java`, `PagamentoService.java`) que implementam a lógica de negócios da aplicação.
* `app.futebol.controller`: Controladores REST (`JogadorController.java`, `PagamentoController.java`) que expõem os endpoints da API e lidam com as requisições HTTP.
* `app.futebol.dto`: Objetos de Transferência de Dados (`PagamentoDTO.java`) usados para modelar os dados de entrada e saída da API.

## Endpoints da API

A API é acessível através da URL base: `http://localhost:8080` (ou a porta configurada em `application.properties`).

### Jogadores (`/api/jogadores`)

* `POST /api/jogadores`
    * **Descrição:** Cria um novo jogador.
    * **Corpo da Requisição (JSON):**
        ```json
        {
            "nome": "Nome do Jogador",
            "email": "email@example.com",
            "dataNasc": "YYYY-MM-DD"
        }
        ```
* `GET /api/jogadores`
    * **Descrição:** Lista todos os jogadores.
* `GET /api/jogadores/{codJogador}`
    * **Descrição:** Busca um jogador pelo seu código.
* `PUT /api/jogadores/{codJogador}`
    * **Descrição:** Atualiza um jogador existente.
    * **Corpo da Requisição (JSON):** (similar ao POST)
* `DELETE /api/jogadores/{codJogador}`
    * **Descrição:** Deleta um jogador.

### Pagamentos

* `POST /api/jogadores/{codJogador}/pagamentos`
    * **Descrição:** Cria um novo pagamento para um jogador específico.
    * **Corpo da Requisição (JSON):**
        ```json
        {
            "ano": 2024,
            "mes": 5,
            "valor": 100.50
        }
        ```
* `GET /api/jogadores/{codJogador}/pagamentos`
    * **Descrição:** Lista todos os pagamentos de um jogador específico.
* `GET /api/pagamentos`
    * **Descrição:** Lista todos os pagamentos de todos os jogadores.
* `GET /api/pagamentos/{codPagamento}`
    * **Descrição:** Busca um pagamento pelo seu código.
* `PUT /api/pagamentos/{codPagamento}`
    * **Descrição:** Atualiza um pagamento existente.
    * **Corpo da Requisição (JSON):** (similar ao POST para `ano`, `mes`, `valor`)
* `DELETE /api/pagamentos/{codPagamento}`
    * **Descrição:** Deleta um pagamento.

## Como Executar o Projeto

### Pré-requisitos

* JDK 20 (ou a versão Java especificada no `pom.xml`).
* Apache Maven 3.x.

### Passos para Execução

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/SEU_USUARIO_GITHUB/SEU_REPOSITORIO_GITHUB.git](https://github.com/SEU_USUARIO_GITHUB/SEU_REPOSITORIO_GITHUB.git)
    cd SEU_REPOSITORIO_GITHUB
    ```
    (Substitua `SEU_USUARIO_GITHUB` e `SEU_REPOSITORIO_GITHUB` pelos seus dados)

2.  **Execute a aplicação usando Maven:**
    Na raiz do projeto, execute o seguinte comando no terminal:
    ```bash
    mvn spring-boot:run
    ```

3.  A aplicação estará disponível em `http://localhost:8080` (ou na porta configurada no arquivo `src/main/resources/application.properties`).

## Configuração

As principais configurações podem ser encontradas no arquivo `src/main/resources/application.properties`. Por padrão:
* A aplicação utiliza um banco de dados H2 em memória (os dados são perdidos ao reiniciar).
* A porta do servidor padrão é `8080`.

Para utilizar um banco de dados persistente (como PostgreSQL, MySQL), as configurações de `datasource` no `application.properties` devem ser ajustadas e a dependência do driver correspondente adicionada ao `pom.xml`.

## Observações

* Este projeto foca na implementação da API backend e não inclui interface gráfica[cite: 3].
* Validações de entrada e tratamento de erros podem ser expandidos para maior robustez.
