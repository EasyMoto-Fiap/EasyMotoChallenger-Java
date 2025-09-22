-- Adiciona as colunas de segurança na tabela de funcionários
ALTER TABLE funcionario ADD password VARCHAR2(255);
ALTER TABLE funcionario ADD cargo VARCHAR2(20); -- Usaremos a coluna 'cargo' para o perfil

-- Atualiza funcionários existentes para não terem valores nulos
UPDATE funcionario SET password = 'default_password_change_me', cargo = 'USER' WHERE password IS NULL;

-- Garante que as colunas não podem ser nulas daqui pra frente
ALTER TABLE funcionario MODIFY (password NOT NULL);
ALTER TABLE funcionario MODIFY (cargo NOT NULL);

-- Insere um funcionário ADMIN e um USER para teste
-- A senha para ambos é "password"
INSERT INTO funcionario (nome_func, cpf_func, telefone_func, email_func, password, cargo, filial_id)
VALUES ('Admin Chefe', '11122233344', '11999998888', 'admin@easymoto.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'ADMIN', (SELECT id_filial FROM filial WHERE ROWNUM = 1));

INSERT INTO funcionario (nome_func, cpf_func, telefone_func, email_func, password, cargo, filial_id)
VALUES ('User Comum', '55566677788', '11977776666', 'user@easymoto.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'USER', (SELECT id_filial FROM filial WHERE ROWNUM = 1));