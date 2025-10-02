INSERT INTO filial (nome_filial, cidade, estado, pais, endereco, empresa_id) VALUES
('Matriz SP', 'São Paulo', 'SP', 'Brasil', 'Avenida Paulista, 1000', 1),
('Filial RJ', 'Rio de Janeiro', 'RJ', 'Brasil', 'Avenida Rio Branco, 200', 1);

INSERT INTO funcionario (nome_func, cpf_func, telefone_func, email_func, password, cargo, filial_id) VALUES
('Ana Administradora', '85590920033', '11988887777', 'admin@easymoto.com', '$2a$10$upnHeELHX/hGNZiWw3Wum.1vpq9DeIZxoafRj5kywxfzlhYG74ely', 1, 1),
('Carlos Usuário', '83313837059', '11955554444', 'user@easymoto.com', '$2a$10$upnHeELHX/hGNZiWw3Wum.1vpq9DeIZxoafRj5kywxfzlhYG74ely', 0, 2);

INSERT INTO operador (nome_opr, cpf_opr, telefone_opr, email_opr, filial_id) VALUES
('Marcos Operador SP', '70633131031', '11977776666', 'operador.sp@easymoto.com', 1),
('Julia Operadora RJ', '50374353005', '21988885555', 'operador.rj@easymoto.com', 2);

INSERT INTO patio (nome_patio, tamanho_patio, andar, filial_id) VALUES
('Pátio Principal SP', 'Grande', '1', 1),
('Garagem Subsolo SP', 'Médio', 'S', 1),
('Pátio Principal RJ', 'Grande', '1', 2);

INSERT INTO cliente_locacao (data_inicio, data_fim, status_locacao, cliente_id) VALUES
('2025-09-20', '2025-09-27', 0, 1),
('2025-09-22', '2025-09-29', 0, 2),
('2025-08-10', '2025-08-17', 1, 3);

INSERT INTO moto (placa, modelo, ano_fabricacao, status_moto, locacao_id, localizacao_id) VALUES
('ABC1D23', 'Honda CG 160', 2023, 0, 3, 1),
('DEF4E56', 'Yamaha Fazer 250', 2022, 1, 2, 2),
('GHI7F89', 'Honda PCX', 2024, 2, 1, 3);

INSERT INTO vaga (status_vaga, patio_id, moto_id, fileira, coluna) VALUES
(0, 1, 1, 'A', '1'),
(0, 3, 3, 'B', '5');

INSERT INTO auditoria_moto (user_name, operacao, data_hora, old_values, new_values) VALUES
('admin@easymoto.com', 'INSERT', '2025-09-25 10:00:00', NULL, 'placa=XYZ1A23, modelo=Honda Biz, ano=2024, status=0'),
('user@easymoto.com', 'UPDATE', '2025-09-25 11:30:00', 'status=0', 'status=1'),
('admin@easymoto.com', 'DELETE', '2025-09-26 15:00:00', 'placa=DEF4E56, modelo=Yamaha Fazer 250, ano=2022, status=2', NULL);