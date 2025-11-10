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
| ☕ Java 21 | 🍃 Thymeleaf | 💾 Oracle Database (SQL Developer) | 🔐 Spring Security | 🐳 Docker |
| 🌱 Spring Boot 3.4.5 | 🎨 Bootstrap 5 | 🐘 Spring Data JPA (Hibernate) | 🔑 JWT (JSON Web Tokens) | 🚀 GitHub Actions (CI/CD) |
| 📦 Gradle | 🌐 HTML5 / CSS3 | 🦋 Flyway (Migrations) | 🔑 BCrypt (Password Encoding) | |
| ✅ Spring Validation | | ⚡ Spring Cache (Caching Simples) | | |


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

5.  **Configure o Banco de Dados Oracle (SQL Developer):**
    - A partir da Sprint 4, o banco de dados principal da aplicação passou a ser o **Oracle Database**, administrado via **Oracle SQL Developer**.
    - Atualize o arquivo `src/main/resources/application.properties` (ou `application.yml`) com as credenciais do seu schema Oracle, por exemplo:

      ```properties
      spring.datasource.url=jdbc:oracle:thin:@<HOST>:<PORTA>/<SERVICE_NAME>
      spring.datasource.username=<SEU_USUARIO_ORACLE>
      spring.datasource.password=<SUA_SENHA_ORACLE>
      spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

      spring.jpa.hibernate.ddl-auto=none
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
      ```

    - No **Oracle SQL Developer**, crie uma conexão usando o mesmo usuário e senha e verifique se as tabelas foram criadas corretamente pelas migrations (Flyway) ao subir a aplicação.

---

## 🌐 Deploy em Nuvem (Sprint 4)

A partir da Sprint 4, o projeto passou a contar com deploy em ambiente de nuvem, permitindo acesso externo para avaliação.

**[>> Link do Deploy (Sprint 4) <<](https://easymoto-rm558191.azurewebsites.net/login)**

- **Ambiente:** descreva aqui onde a aplicação está publicada (por exemplo: Azure Web App, Render, Railway, etc.).
- **Observações:** se houver usuário/senha específicos para acesso no ambiente de produção, descreva-os aqui.

---

## 🎥 Vídeo de Demonstração (YouTube)

Um vídeo demonstrando as principais funcionalidades da aplicação está disponível no YouTube.

**[>> Link para o Vídeo Final da Sprint 4 <<](https://youtu.be/xxXqfxA5bcg)**

---

## 👩‍💻 Equipe

| Nome | RM |
|---|---|
| Valéria Conceição Dos Santos | 557177 |
| Mirela Pinheiro Silva Rodrigues | 558191 |
