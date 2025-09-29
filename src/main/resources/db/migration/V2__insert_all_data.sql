-- Inserir dados na tabela 'empresa'
INSERT INTO empresa (id_empresa, nome_empresa, cnpj) VALUES (1, 'EasyMoto S.A.', '11222333000144');

-- Inserir dados na tabela 'filial'
INSERT INTO filial (id_filial, nome_filial, cidade, estado, pais, endereco, empresa_id) VALUES
(1, 'Matriz SP', 'São Paulo', 'SP', 'Brasil', 'Avenida Paulista, 1000', 1),
(2, 'Filial RJ', 'Rio de Janeiro', 'RJ', 'Brasil', 'Avenida Rio Branco, 200', 1);

-- Inserir dados na tabela 'funcionario' (senha para todos é "password")
-- cargo: 0=USER, 1=ADMIN
INSERT INTO funcionario (id_func, nome_func, cpf_func, telefone_func, email_func, password, cargo, filial_id) VALUES
(1, 'Ana Administradora', '85590920033', '11988887777', 'admin@easymoto.com', '$2a$10$upnHeELHX/hGNZiWw3Wum.1vpq9DeIZxoafRj5kywxfzlhYG74ely', 1, 1),
(2, 'Carlos Usuário', '83313837059', '11955554444', 'user@easymoto.com', '$2a$10$upnHeELHX/hGNZiWw3Wum.1vpq9DeIZxoafRj5kywxfzlhYG74ely', 0, 2),
(3, 'Luiz Eduardo Da Silva Pinto', '46780407062', '12981813015', 'ledu64816@gmail.com', '$2a$10$upnHeELHX/hGNZiWw3Wum.1vpq9DeIZxoafRj5kywxfzlhYG74ely', 1, 1);

-- Inserir dados na tabela 'operador'
INSERT INTO operador (id_operador, nome_opr, cpf_opr, telefone_opr, email_opr, filial_id) VALUES
(1, 'Marcos Operador SP', '70633131031', '11977776666', 'operador.sp@easymoto.com', 1),
(2, 'Julia Operadora RJ', '50374353005', '21988885555', 'operador.rj@easymoto.com', 2);

-- Inserir dados na tabela 'patio'
INSERT INTO patio (id_patio, nome_patio, tamanho_patio, andar, filial_id) VALUES
(1, 'Pátio Principal SP', 'Grande', '1', 1),
(2, 'Garagem Subsolo SP', 'Médio', 'S', 1),
(3, 'Pátio Principal RJ', 'Grande', '1', 2);

-- Inserir dados na tabela 'cliente'
INSERT INTO cliente (id_cliente, nome_cliente, cpf_cliente, telefone_cliente, email_cliente) VALUES
(1, 'Bruno Alves', '84406286098', '11911111111', 'bruno.alves@email.com'),
(2, 'Carla Dias', '58556870074', '21922222222', 'carla.dias@email.com'),
(3, 'Fernando Costa', '27183062031', '11933333333', 'fernando.costa@email.com');

-- Inserir dados na tabela 'localizacao' (assumindo 0='Disponível', 1='Em Uso', 2='Manutenção')
INSERT INTO localizacao (id_localizacao, status_loc, data_hora, zona_virtual, latitude, longitude) VALUES
(1, 0, '2025-09-24 10:00:00', 'Pátio SP', -23.5613, -46.6565),
(2, 1, '2025-09-24 10:05:00', 'Zona Sul SP', -23.6000, -46.6800),
(3, 2, '2025-09-24 10:10:00', 'Pátio RJ', -22.9068, -43.1729);

-- status_locacao: 0=ABERTA, 1=FINALIZADA, 2=CANCELADA
INSERT INTO cliente_locacao (id_locacao, data_inicio, data_fim, status_locacao, cliente_id) VALUES
(1, '2025-09-20', '2025-09-27', 0, 1),
(2, '2025-09-22', '2025-09-29', 0, 2),
(3, '2025-08-10', '2025-08-17', 1, 3);

-- status_moto: 0=DISPONIVEL, 1=EM_USO, 2=MANUTENCAO
INSERT INTO moto (id_moto, placa, modelo, ano_fabricacao, status_moto, locacao_id, localizacao_id) VALUES
(1, 'ABC1D23', 'Honda CG 160', 2023, 0, 3, 1),
(2, 'DEF4E56', 'Yamaha Fazer 250', 2022, 1, 2, 2),
(3, 'GHI7F89', 'Honda PCX', 2024, 2, 1, 3);

-- status_vaga: 0=OCUPADA, 1=INDEFINIDA, 2=LIVRE
INSERT INTO vaga (id_vaga, status_vaga, patio_id, moto_id, fileira, coluna) VALUES
(1, 0, 1, 1, 'A', '1'),
(2, 0, 3, 3, 'B', '5');

-- As demais tabelas não precisam de alteração
INSERT INTO auditoria_moto (user_name, operacao, data_hora, old_values, new_values) VALUES
('admin@easymoto.com', 'INSERT', '2025-09-25 10:00:00', NULL, 'placa=XYZ1A23, modelo=Honda Biz, ano=2024, status=0'),
('user@easymoto.com', 'UPDATE', '2025-09-25 11:30:00', 'status=0', 'status=1'),
('admin@easymoto.com', 'UPDATE', '2025-09-26 09:05:00', 'placa=ABC1D23, status=1','placa=ABC1D23, status=2'),
('admin@easymoto.com', 'DELETE', '2025-09-26 15:00:00', 'placa=DEF4E56, modelo=Yamaha Fazer 250, ano=2022, status=2', NULL),
('user@easymoto.com', 'INSERT', '2025-09-27 08:45:00', NULL, 'placa=MNO3P45, modelo=Suzuki GSX-S750, ano=2023, status=0');

INSERT INTO noticia (titulo, conteudo, data_publicacao, autor, categoria) VALUES
('Nova Geração de Motos Elétricas', 'Conteúdo sobre as novas motos elétricas...', '2025-09-26', 'Equipe EasyMoto', 'TECNOLOGIA'),
('Dicas de Segurança para Entregas', 'Conteúdo sobre dicas de segurança...', '2025-09-25', 'João Especialista', 'SEGURANCA');