
INSERT INTO empresa (id_empresa, nome_empresa, cnpj) VALUES (1, 'EasyMoto S.A.', '11222333000144');

INSERT INTO filial (id_filial, nome_filial, cidade, estado, pais, endereco, empresa_id) VALUES
(1, 'Matriz SP', 'São Paulo', 'SP', 'Brasil', 'Avenida Paulista, 1000', 1),
(2, 'Filial RJ', 'Rio de Janeiro', 'RJ', 'Brasil', 'Avenida Rio Branco, 200', 1);

INSERT INTO funcionario (id_func, nome_func, cpf_func, telefone_func, email_func, password, cargo, filial_id) VALUES
(1, 'Ana Administradora', '78933804054', '64257864001', 'admin@easymoto.com', '$2a$10$upnHeELHX/hGNZiWw3Wum.1vpq9DeIZxoafRj5kywxfzlhYG74ely', 'ADMIN', 1),
(2, 'Carlos Usuário', '01917713079', '53347754042', 'user@easymoto.com', '$2a$10$upnHeELHX/hGNZiWw3Wum.1vpq9DeIZxoafRj5kywxfzlhYG74ely', 'USER', 2);

INSERT INTO operador (id_operador, nome_opr, cpf_opr, telefone_opr, email_opr, filial_id) VALUES
(1, 'Marcos Operador SP', '70633131031', '11977776666', 'operador.sp@easymoto.com', 1),
(2, 'Julia Operadora RJ', '50374353005', '21988885555', 'operador.rj@easymoto.com', 2);

INSERT INTO patio (id_patio, nome_patio, tamanho_patio, andar, filial_id) VALUES
(1, 'Pátio Principal SP', 'Grande', '1', 1),
(2, 'Garagem Subsolo SP', 'Médio', 'S', 1),
(3, 'Pátio Principal RJ', 'Grande', '1', 2);

INSERT INTO cliente (id_cliente, nome_cliente, cpf_cliente, telefone_cliente, email_cliente) VALUES
(1, 'Bruno Alves', '14801127003', '11911111111', 'bruno.alves@email.com'),
(2, 'Carla Dias', '02422737033', '21922222222', 'carla.dias@email.com'),
(3, 'Fernando Costa', '32561574044', '11933333333', 'fernando.costa@email.com');

INSERT INTO localizacao (id_localizacao, status_loc, data_hora, zona_virtual, latitude, longitude) VALUES
(1, 'Disponível', '2025-09-24 10:00:00', 'Pátio SP', -23.5613, -46.6565),
(2, 'Em Uso', '2025-09-24 10:05:00', 'Zona Sul SP', -23.6000, -46.6800),
(3, 'Manutenção', '2025-09-24 10:10:00', 'Pátio RJ', -22.9068, -43.1729);

INSERT INTO cliente_locacao (id_locacao, data_inicio, data_fim, status_locacao, cliente_id) VALUES
(1, '2025-09-20', '2025-09-27', 'ABERTA', 1),
(2, '2025-09-22', '2025-09-29', 'ABERTA', 2),
(3, '2025-08-10', '2025-08-17', 'FINALIZADA', 3);

INSERT INTO moto (id_moto, placa, modelo, ano_fabricacao, status_moto, locacao_id, localizacao_id) VALUES
(1, 'ABC1D23', 'Honda CG 160', 2023, 'DISPONIVEL', 3, 1),
(2, 'DEF4E56', 'Yamaha Fazer 250', 2022, 'EM_USO', 2, 2),
(3, 'GHI7F89', 'Honda PCX', 2024, 'MANUTENCAO', 1, 3);

INSERT INTO vaga (id_vaga, status_vaga, patio_id, moto_id, fileira, coluna) VALUES
(1, 'OCUPADA', 1, 1, 'A', '1'),
(2, 'OCUPADA', 3, 3, 'B', '5');

INSERT INTO auditoria_moto (user_name, operacao, data_hora, old_values, new_values) VALUES
('admin@easymoto.com', 'INSERT', '2025-09-25 10:00:00', NULL, 'placa=XYZ1A23, modelo=Honda Biz, ano=2024, status=DISPONIVEL'),
('user@easymoto.com', 'UPDATE', '2025-09-25 11:30:00', 'status=DISPONIVEL', 'status=EM_USO'),
('admin@easymoto.com', 'UPDATE', '2025-09-26 09:05:00', 'placa=ABC1D23, status=EM_USO','placa=ABC1D23, status=MANUTENCAO'),
('admin@easymoto.com', 'DELETE', '2025-09-26 15:00:00', 'placa=DEF4E56, modelo=Yamaha Fazer 250, ano=2022, status=MANUTENCAO', NULL),
('user@easymoto.com', 'INSERT', '2025-09-27 08:45:00', NULL, 'placa=MNO3P45, modelo=Suzuki GSX-S750, ano=2023, status=DISPONIVEL');

INSERT INTO noticia (titulo, conteudo, data_publicacao, autor, categoria) VALUES
('Nova Geração de Motos Elétricas', 'Conteúdo sobre as novas motos elétricas...', '2025-09-26', 'Equipe EasyMoto', 'TECNOLOGIA'),
('Dicas de Segurança para Entregas', 'Conteúdo sobre dicas de segurança...', '2025-09-25', 'João Especialista', 'SEGURANCA');