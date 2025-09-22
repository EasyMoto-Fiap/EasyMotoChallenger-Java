CREATE TABLE cliente (
    id_cliente BIGSERIAL PRIMARY KEY,
    nome_cliente VARCHAR(100) NOT NULL,
    cpf_cliente VARCHAR(11) NOT NULL UNIQUE,
    telefone_cliente VARCHAR(15) NOT NULL,
    email_cliente VARCHAR(100) NOT NULL
);