# 🚦 EasyMoto API – FIAP Challenger (Java / Spring Boot)

EasyMoto é uma API RESTful desenvolvida como solução para um desafio real da Mottu.  
O objetivo é facilitar o mapeamento inteligente de pátios, o gerenciamento de motos e toda a jornada de aluguel e localização em múltiplas filiais, trazendo escalabilidade, performance e integração real.

---

## 🛠️ Tecnologias Utilizadas

- ☕ Java 17
- 🌱 Spring Boot 3
- 🟦 Spring Data JPA
- 🟩 Bean Validation
- 📦 Spring Cache
- 📄 Swagger/OpenAPI
- 🛢️ Banco de Dados Oracle 
- 🐳 Docker (containerização da API)

---

## 🗂️ Entidades e Relacionamentos

O projeto implementa as seguintes entidades, todas com CRUD completo (GET, GET/{id}, POST, PUT, DELETE) e relacionamentos via chave estrangeira conforme modelagem Oracle:

- Cliente
- Empresa
- Filial (relacionada a Empresa)
- Funcionario (relacionado a Filial)
- Operador (relacionado a Filial)
- Patio (relacionado a Filial)
- ClienteLocacao (relacionada a Cliente)
- Localizacao
- Moto (relacionada a ClienteLocacao e Localizacao)
- Vaga (relacionada a Moto e Patio)

---

## 🔗 Rotas Principais (Exemplo Cliente)
- `GET    /api/Cliente`
- `GET    /api/Cliente/{id}`
- `POST   /api/Cliente`
- `PUT    /api/Cliente/{id}`
- `DELETE /api/Cliente/{id}`

### Demais entidades seguem o mesmo padrão RESTful, trocando "clientes" pelo nome da entidade.
---

## 🚀 Como rodar o projeto (Java)

1. **Clone o repositório:**
    ```bash
   git clone https://github.com/akemilol/EasyMotoChallenger-Java.git
    
2. **Configure o banco de dados em `src/main/resources/application.properties`:**

3. **Usando Oracle (SQL Developer):**
   - Abra o Oracle SQL Developer
   - Conecte-se ao seu banco
   - Execute os comandos INSERT fornecidos em `data.sql`

4. **Rode a aplicação pela sua IDE (IntelliJ/Eclipse):**
    - Encontre a classe principal `EasyMotoApplication.java`
    - Clique com o direito e selecione "Run" ou utilize o botão de execução da IDE

5. **Acesse a documentação Swagger:**
    - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
      
## 👩‍💻 Integrantes

- 💁‍♀️ Valéria Conceição Dos Santos — RM: 557177  
- 💁‍♀️ Mirela Pinheiro Silva Rodrigues — RM: 558191
---

# 🐳 Parte 2: Docker & Cloud — Containerização da API

## 📦 Como subir o projeto via Docker

**Pré-requisitos:**
- Docker instalado na máquina/servidor.
- Banco de dados Oracle acessível.

### Build e execução

1. **Gere o JAR (obrigatório para Docker):**
    ```sh
    ./gradlew clean build -x test
    ```

2. **Construa a imagem Docker:**
    ```sh
    docker build -t easymoto-api .
    ```

3. **Rode o container (ajuste as variáveis conforme necessário):**
    ```sh
    docker run -p 8080:8080 \
      -e DB_URL="URL" \
      -e DB_USERNAME="SEU_USUARIO" \
      -e DB_PASSWORD="SUA_SENHA" \
      easymoto-api
    ```

4. **Acesse a aplicação:**
    - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Observações importantes

- Os dados das entidades devem ser inseridos no Oracle manualmente (via SQL Developer), conforme os INSERTs fornecidos em `data.sql` ou na documentação.
- Para deploy em nuvem (Azure/AWS), basta subir o Docker na VM e garantir o acesso externo à porta 8080.

---
