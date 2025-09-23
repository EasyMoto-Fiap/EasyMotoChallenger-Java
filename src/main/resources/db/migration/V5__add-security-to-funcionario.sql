ALTER TABLE funcionario ADD COLUMN password VARCHAR(255);
ALTER TABLE funcionario ADD COLUMN cargo VARCHAR(20);

INSERT INTO filial(nome_filial, cidade, estado, pais, endereco, empresa_id) VALUES ('Matriz SP', 'São Paulo', 'SP', 'Brasil', 'Av. Paulista, 1000', (SELECT id_empresa FROM empresa LIMIT 1));

INSERT INTO funcionario (nome_func, cpf_func, telefone_func, email_func, password, cargo, filial_id)
VALUES ('Admin Chefe', '11122233344', '11999998888', 'admin@easymoto.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'ADMIN', (SELECT id_filial FROM filial LIMIT 1));

INSERT INTO funcionario (nome_func, cpf_func, telefone_func, email_func, password, cargo, filial_id)
VALUES ('User Comum', '55566677788', '11977776666', 'user@easymoto.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'USER', (SELECT id_filial FROM filial LIMIT 1));