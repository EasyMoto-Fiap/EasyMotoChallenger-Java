# 🚦 EasyMoto - Sistema de Gerenciamento de Frotas (FIAP Challenge)

Bem-vindo ao repositório do EasyMoto, uma solução completa desenvolvida em **Java e Spring Boot** para o desafio da Mottu proposto pela FIAP. Este projeto consiste em uma aplicação web com uma API RESTful para o gerenciamento inteligente de frotas de motocicletas, locações, clientes e pátios.

O sistema foi projetado com foco em boas práticas de desenvolvimento, separação de responsabilidades e uma arquitetura robusta, incluindo uma interface web administrativa construída com Thymeleaf e uma API segura para integrações.

---

## ✨ Funcionalidades Principais

A aplicação é dividida em duas frentes principais: uma interface web para administração e uma API RESTful para consumo de dados.

### Interface Web (Thymeleaf & Spring Security)
- **Dashboard Inicial:** Visão geral com atalhos para as principais funcionalidades.
- **Login Seguro:** Autenticação de usuários com Spring Security, com perfis de `ADMIN` e `USER`. Inclui sistema de "Lembrar de mim" e recuperação de senha.
- **Gerenciamento de Clientes:** CRUD completo para clientes.
- **Gerenciamento de Motos:** CRUD completo para a frota, com opção de filtro por status (`Disponível`, `Em Uso`, `Manutenção`).
- **Gerenciamento de Locações:** CRUD completo para os contratos de locação, com filtros por cliente, status e datas.
- **Gerenciamento de Vagas:** CRUD e listagem de vagas nos pátios, com filtros por status.
- **Administração (Acesso Restrito - `ADMIN`):**
    - **Gerenciamento de Funcionários:** CRUD de usuários do sistema, com atribuição de cargos (`ADMIN`, `USER`).
    - **Auditoria de Motos:** Tela para visualizar o histórico de alterações (INSERT, UPDATE, DELETE) na frota de motos, com filtros avançados por usuário, operação e data.
- **Configurações de Conta:** Permite que o usuário logado altere sua própria senha.
- **Visualização de Dados da Empresa:** Telas para visualização de Empresas, Filiais, Pátios e Operadores.

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

| Backend | Frontend (Web Admin) | Banco de Dados & Persistência | Segurança | DevOps |
|---|---|---|---|---|
| ☕ Java 21 | 🍃 Thymeleaf | 💾 H2 (Banco em Memória) | 🔐 Spring Security | 🐳 Docker |
| 🌱 Spring Boot 3.4.5 | 🎨 Bootstrap 5 | 🐘 Spring Data JPA (Hibernate) | 🔑 JWT (JSON Web Tokens) | 🚀 GitHub Actions (CI/CD) |
| 📦 Gradle | 🌐 HTML5 / CSS3 | 🦋 Flyway (Migrations) | 🔑 BCrypt (Password Encoding) | |
| ✅ Spring Validation | | ⚡ Spring Cache (Caching Simples) | | |
| 📄 SpringDoc (Swagger/OpenAPI) | | | | |

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
    - Abra o navegador em: **[http://localhost:8081](http://localhost:8081)**
    - Utilize um dos usuários de teste para fazer login (a senha para ambos é `password`):
        - **Admin:** `admin@easymoto.com`
        - **Usuário Comum:** `user@easymoto.com`

5.  **Acesse a documentação da API (Swagger):**
    - Para explorar e testar a API RESTful, acesse: **[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)**

6.  **Acesse o Console do Banco de Dados H2:**
    - Para visualizar e interagir com o banco de dados em memória, acesse: **[http://localhost:8081/h2-console](http://localhost:8081/h2-console)**
    - Utilize as seguintes credenciais para conectar:
        - **JDBC URL:** `jdbc:h2:mem:easymoto`
        - **User Name:** `sa`
        - **Password:** `password`

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
    docker run -p 8081:8080 --name easymoto-container easymoto-api
    ```
    A aplicação estará acessível em `http://localhost:8081`.

---

## 🎥 Vídeo de Demonstração (YouTube)

Um vídeo demonstrando as principais funcionalidades da aplicação está disponível no YouTube.

**[>> Link para o Vídeo <<](https://www.youtube.com/seu-link-aqui)**

**Pontos demonstrados no vídeo:**
- **Autenticação:**
    - Login com usuário `ADMIN` e `USER`, mostrando as diferenças de permissão.
    - Processo de logout.
- **Gerenciamento (CRUDs):**
    - Criação, listagem, edição e exclusão de Clientes, Motos e Locações.
    - Demonstração dos filtros nas telas de listagem.
- **Funcionalidades de Administrador (`ADMIN`):**
    - Acesso à área de Gerenciamento de Funcionários.
    - Acesso à tela de Auditoria de Motos, explicando como os filtros funcionam.
- **Configurações de Conta:**
    - Demonstração da funcionalidade de alteração de senha pelo próprio usuário.
- **API (Swagger):**
    - Breve navegação pela documentação da API no Swagger-UI.

---

## 👩‍💻 Equipe

| Nome | RM |
|---|---|
| Valéria Conceição Dos Santos | 557177 |
| Mirela Pinheiro Silva Rodrigues | 558191 |
| Luiz Eduardo Da Silva Pinto | 55213 |
