CREATE TABLE empresa (
    id_empresa BIGSERIAL PRIMARY KEY,
    nome_empresa VARCHAR(50) NOT NULL,
    cnpj VARCHAR(14) NOT NULL UNIQUE
);