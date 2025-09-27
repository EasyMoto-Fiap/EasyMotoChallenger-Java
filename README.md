# 🚦 EasyMoto - Sistema de Gerenciamento de Frotas (FIAP Challenge)

Bem-vindo ao repositório do EasyMoto, uma solução completa desenvolvida em **Java e Spring Boot** para o desafio da Mottu proposto pela FIAP. Este projeto consiste em uma aplicação web com uma API RESTful para o gerenciamento inteligente de frotas de motocicletas, locações, clientes e pátios.

O sistema foi projetado com foco em boas práticas de desenvolvimento, separação de responsabilidades e uma arquitetura robusta, incluindo uma interface web administrativa construída com Thymeleaf e uma API segura para integrações.

---

## ✨ Funcionalidades Principais

A aplicação é dividida em duas frentes principais: uma interface web para administração e uma API RESTful para consumo de dados.

### Interface Web (Thymeleaf & Spring Security)
- **Dashboard Inicial:** Visão geral com atalhos para as principais funcionalidades.
- **Login Seguro:** Autenticação de usuários com Spring Security, com perfis de `ADMIN` e `USER`.
- **Gerenciamento de Clientes:** CRUD completo para clientes.
- **Gerenciamento de Motos:** CRUD completo para a frota, com opção de filtro por status (`Disponível`, `Em Uso`, `Manutenção`).
- **Gerenciamento de Locações:** CRUD completo para os contratos de locação, com filtros por cliente, status e datas.
- **Gerenciamento de Vagas:** CRUD e listagem de vagas nos pátios.
- **Administração (Acesso Restrito):**
    - **Gerenciamento de Funcionários:** CRUD de usuários do sistema, com atribuição de cargos (`ADMIN`, `USER`).
    - **Auditoria de Motos:** Tela para visualizar o histórico de alterações (INSERT, UPDATE, DELETE) na frota de motos, com filtros avançados.
- **Configurações de Conta:** Permite que o usuário logado altere sua própria senha.

### API RESTful (Spring Web & Swagger)
- **Endpoints Seguros:** Todas as rotas da API (exceto autenticação) são protegidas com JWT (JSON Web Tokens).
- **CRUD Completo:** Endpoints RESTful para todas as principais entidades da aplicação:
    - `Cliente`
    - `Moto`
    - `Locacao`
    - `Vaga`
    - `Funcionario`
    - `Empresa`, `Filial`, `Patio`, `Operador`
- **Documentação Interativa:** A API é 100% documentada com Swagger (OpenAPI), permitindo testar os endpoints diretamente pelo navegador.

---

## 🛠️ Tecnologias Utilizadas

| Backend                               | Frontend (Web Admin)                | Banco de Dados & Persistência     | Segurança                      | DevOps                      |
| ------------------------------------- | ----------------------------------- | --------------------------------- | ------------------------------ | --------------------------- |
| ☕ Java 21                            | 🍃 Thymeleaf                        | 💾 H2 (Banco em Memória)          | 🔐 Spring Security             | 🐳 Docker                   |
| 🌱 Spring Boot 3                      | 🎨 Bootstrap 5                      | 🐘 Spring Data JPA (Hibernate)    | 🔑 JWT (JSON Web Tokens)       | 🚀 GitHub Actions (CI/CD)   |
| 📦 Gradle                             | 🌐 HTML5 / CSS3                     | 🦋 Flyway (Migrations)            | 🔑 BCrypt (Password Encoding)  |                             |
| ✅ Spring Validation                  |                                     | ⚡ Spring Cache (Caching Simples) |                                |                             |
| 📄 SpringDoc (Swagger/OpenAPI)        |                                     |                                   |                                |                             |

---

## 🚀 Como Executar o Projeto Localmente

**Pré-requisitos:**
- JDK 21 instalado.
- IntelliJ IDEA, Eclipse ou outra IDE de sua preferência.

### Passo a Passo:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/akemilol/EasyMotoChallenger-Java.git](https://github.com/akemilol/EasyMotoChallenger-Java.git)
    cd EasyMotoChallenger-Java
    ```

2.  **Abra o projeto na sua IDE:**
    - Abra o projeto como um projeto Gradle. A IDE irá baixar todas as dependências automaticamente.

3.  **Execute a aplicação:**
    - Encontre a classe principal `EasyMotoApplication.java`.
    - Clique com o botão direito e selecione "Run".

4.  **Acesse a aplicação web:**
    - Abra o navegador em: **[http://localhost:8080](http://localhost:8080)**
    - Utilize um dos usuários de teste para fazer login (a senha para ambos é `123456`):
        - **Admin:** `admin@easymoto.com`
        - **Usuário Comum:** `user@easymoto.com`

5.  **Acesse o console do banco de dados H2 (Opcional):**
    - Para visualizar o banco de dados em memória, acesse: **[http://localhost:8080/h2-console](http://localhost:8080/h2-console)**
    - Use as seguintes credenciais definidas em `application.properties`:
        - **JDBC URL:** `jdbc:h2:mem:easymoto`
        - **User Name:** `sa`
        - **Password:** `password`

6.  **Acesse a documentação da API (Swagger):**
    - Para explorar e testar a API RESTful, acesse: **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

---

## 🐳 Executando com Docker

1.  **Gere o arquivo `.jar` da aplicação:**
    ```sh
    ./gradlew clean bootJar
    ```

2.  **Construa a imagem Docker:**
    ```sh
    docker build -t easymoto-api .
    ```

3.  **Rode o container:**
    ```sh
    docker run -p 8080:8080 --name easymoto-container easymoto-api
    ```
    A aplicação estará acessível em `http://localhost:8080`.

---

## 👩‍💻 Equipe

| Nome                              | RM      |
| --------------------------------- | ------- |
| Valéria Conceição Dos Santos      | 557177  |
| Mirela Pinheiro Silva Rodrigues   | 558191  |
| Luiz Eduaro Da Silva Pinto        | 55213   |
