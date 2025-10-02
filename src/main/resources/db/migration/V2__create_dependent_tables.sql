-- Migration 2: Criação das tabelas com chaves estrangeiras

CREATE TABLE filial (
    id_filial BIGSERIAL PRIMARY KEY,
    nome_filial VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    pais VARCHAR(50) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    empresa_id BIGINT NOT NULL,
    CONSTRAINT fk_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id_empresa)
);

CREATE TABLE funcionario (
    id_func BIGSERIAL PRIMARY KEY,
    nome_func VARCHAR(100) NOT NULL,
    cpf_func VARCHAR(11) NOT NULL UNIQUE,
    telefone_func VARCHAR(15) NOT NULL,
    email_func VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    cargo SMALLINT NOT NULL,
    reset_password_token VARCHAR(255),
    token_expiry_date TIMESTAMP,
    filial_id BIGINT NOT NULL,
    CONSTRAINT fk_filial_func FOREIGN KEY (filial_id) REFERENCES filial(id_filial)
);

CREATE TABLE operador (
    id_operador BIGSERIAL PRIMARY KEY,
    nome_opr VARCHAR(100) NOT NULL,
    cpf_opr VARCHAR(11) NOT NULL UNIQUE,
    telefone_opr VARCHAR(14) NOT NULL,
    email_opr VARCHAR(100) NOT NULL,
    filial_id BIGINT NOT NULL,
    CONSTRAINT fk_filial_opr FOREIGN KEY (filial_id) REFERENCES filial(id_filial)
);

CREATE TABLE patio (
    id_patio BIGSERIAL PRIMARY KEY,
    nome_patio VARCHAR(50) NOT NULL,
    tamanho_patio VARCHAR(50) NOT NULL,
    andar VARCHAR(1) NOT NULL,
    filial_id BIGINT NOT NULL,
    CONSTRAINT fk_filial_patio FOREIGN KEY (filial_id) REFERENCES filial(id_filial)
);

CREATE TABLE cliente_locacao (
    id_locacao BIGSERIAL PRIMARY KEY,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    status_locacao SMALLINT NOT NULL,
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_cliente_locacao FOREIGN KEY (cliente_id) REFERENCES cliente(id_cliente)
);

CREATE TABLE moto (
    id_moto BIGSERIAL PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE,
    modelo VARCHAR(50) NOT NULL,
    ano_fabricacao INT NOT NULL,
    status_moto SMALLINT NOT NULL,
    locacao_id BIGINT,
    localizacao_id BIGINT,
    CONSTRAINT fk_locacao_moto FOREIGN KEY (locacao_id) REFERENCES cliente_locacao(id_locacao),
    CONSTRAINT fk_localizacao_moto FOREIGN KEY (localizacao_id) REFERENCES localizacao(id_localizacao)
);

CREATE TABLE vaga (
    id_vaga BIGSERIAL PRIMARY KEY,
    status_vaga SMALLINT NOT NULL,
    patio_id BIGINT NOT NULL,
    moto_id BIGINT UNIQUE,
    fileira VARCHAR(1) NOT NULL,
    coluna VARCHAR(1) NOT NULL,
    CONSTRAINT fk_patio_vaga FOREIGN KEY (patio_id) REFERENCES patio(id_patio),
    CONSTRAINT fk_moto_vaga FOREIGN KEY (moto_id) REFERENCES moto(id_moto)
);