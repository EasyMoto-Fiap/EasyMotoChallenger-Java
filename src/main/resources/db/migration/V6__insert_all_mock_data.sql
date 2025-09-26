-- Remove dados existentes para garantir que o script possa ser executado novamente
DELETE FROM vaga;
DELETE FROM moto;
DELETE FROM localizacao;
DELETE FROM cliente_locacao;
DELETE FROM patio;
DELETE FROM operador;
DELETE FROM funcionario;
DELETE FROM filial;
DELETE FROM empresa;
DELETE FROM cliente;

-- INSERINDO DADOS BÁSICOS
INSERT INTO empresa (id_empresa, nome_empresa, cnpj) VALUES (1, 'EasyMoto S.A.', '11222333000144');

INSERT INTO filial (id_filial, nome_filial, cidade, estado, pais, endereco, empresa_id) VALUES
(1, 'Matriz SP', 'São Paulo', 'SP', 'Brasil', 'Avenida Paulista, 1000', 1),
(2, 'Filial RJ', 'Rio de Janeiro', 'RJ', 'Brasil', 'Avenida Rio Branco, 200', 1);

INSERT INTO funcionario (id_func, nome_func, cpf_func, telefone_func, email_func, password, cargo, filial_id) VALUES
(1, 'Ana Administradora', '11122233344', '11988887777', 'admin@easymoto.com', '$2a$10$upnHeELHX/hGNZiWw3Wum.1vpq9DeIZxoafRj5kywxfzlhYG74ely', 'ADMIN', 1),
(2, 'Carlos Usuário', '55566677788', '11955554444', 'user@easymoto.com', '$2a$10$upnHeELHX/hGNZiWw3Wum.1vpq9DeIZxoafRj5kywxfzlhYG74ely', 'USER', 2);

INSERT INTO operador (id_operador, nome_opr, cpf_opr, telefone_opr, email_opr, filial_id) VALUES
(1, 'Marcos Operador SP', '12121212121', '11977776666', 'operador.sp@easymoto.com', 1),
(2, 'Julia Operadora RJ', '34343434343', '21988885555', 'operador.rj@easymoto.com', 2);

INSERT INTO patio (id_patio, nome_patio, tamanho_patio, andar, filial_id) VALUES
(1, 'Pátio Principal SP', 'Grande', '1', 1),
(2, 'Garagem Subsolo SP', 'Médio', 'S', 1),
(3, 'Pátio Principal RJ', 'Grande', '1', 2);

INSERT INTO cliente (id_cliente, nome_cliente, cpf_cliente, telefone_cliente, email_cliente) VALUES
(1, 'Bruno Alves', '11111111111', '11911111111', 'bruno.alves@email.com'),
(2, 'Carla Dias', '22222222222', '21922222222', 'carla.dias@email.com'),
(3, 'Fernando Costa', '33333333333', '11933333333', 'fernando.costa@email.com');

-- INSERINDO DADOS DE RELACIONAMENTO
INSERT INTO localizacao (id_localizacao, status_loc, data_hora, zona_virtual, latitude, longitude) VALUES
(1, 'Disponível', '2025-09-24 10:00:00', 'Pátio SP', -23.5613, -46.6565),
(2, 'Em Uso', '2025-09-24 10:05:00', 'Zona Sul SP', -23.6000, -46.6800),
(3, 'Manutenção', '2025-09-24 10:10:00', 'Pátio RJ', -22.9068, -43.1729);

-- Locações (STATUS CORRIGIDOS PARA O ENUM)
INSERT INTO cliente_locacao (id_locacao, data_inicio, data_fim, status_locacao, cliente_id) VALUES
(1, '2025-09-20', '2025-09-27', 'ATIVA', 1),
(2, '2025-09-22', '2025-09-29', 'ATIVA', 2),
(3, '2025-08-10', '2025-08-17', 'FINALIZADA', 3);

-- Motos (STATUS CORRIGIDOS PARA O ENUM)
INSERT INTO moto (id_moto, placa, modelo, ano_fabricacao, status_moto, locacao_id, localizacao_id) VALUES
(1, 'ABC1D23', 'Honda CG 160', 2023, 'DISPONIVEL', 3, 1),
(2, 'DEF4E56', 'Yamaha Fazer 250', 2022, 'EM_LOCACAO', 2, 2),
(3, 'GHI7F89', 'Honda PCX', 2024, 'EM_MANUTENCAO', 1, 3);

-- Vagas (STATUS CORRIGIDOS PARA O ENUM)
INSERT INTO vaga (id_vaga, status_vaga, patio_id, moto_id, fileira, coluna) VALUES
(1, 'OCUPADA', 1, 1, 'A', '1'),
(2, 'OCUPADA', 3, 3, 'B', '5');