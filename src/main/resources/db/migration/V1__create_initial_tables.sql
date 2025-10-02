DROP TABLE IF EXISTS vaga, moto, cliente_locacao, patio, operador, funcionario, filial, noticia, auditoria_moto, localizacao, empresa, cliente CASCADE;

CREATE TABLE cliente (
    id_cliente BIGSERIAL PRIMARY KEY,
    nome_cliente VARCHAR(100) NOT NULL,
    cpf_cliente VARCHAR(14) NOT NULL UNIQUE,
    telefone_cliente VARCHAR(15) NOT NULL,
    email_cliente VARCHAR(100) NOT NULL
);

CREATE TABLE empresa (
    id_empresa BIGSERIAL PRIMARY KEY,
    nome_empresa VARCHAR(50) NOT NULL,
    cnpj VARCHAR(14) NOT NULL UNIQUE
);

CREATE TABLE localizacao (
    id_localizacao BIGSERIAL PRIMARY KEY,
    status_loc SMALLINT NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    zona_virtual VARCHAR(50) NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL
);

CREATE TABLE auditoria_moto (
    id_audit BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255),
    operacao VARCHAR(10),
    data_hora TIMESTAMP,
    old_values CLOB,
    new_values CLOB
);