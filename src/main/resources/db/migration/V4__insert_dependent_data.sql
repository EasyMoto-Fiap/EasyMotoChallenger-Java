INSERT INTO filial (nome_filial, cidade, estado, pais, endereco, empresa_id)
VALUES (
           'Filial São Paulo',
           'São Paulo',
           'SP',
           'Brasil',
           'Avenida Paulista, 1000',
           (SELECT id_empresa FROM empresa WHERE cnpj = '11222333000144')
       );

INSERT INTO filial (nome_filial, cidade, estado, pais, endereco, empresa_id)
VALUES (
           'Filial Rio de Janeiro',
           'Rio de Janeiro',
           'RJ',
           'Brasil',
           'Avenida Rio Branco, 200',
           (SELECT id_empresa FROM empresa WHERE cnpj = '11222333000144')
       );

INSERT INTO funcionario (nome_func, cpf_func, telefone_func, email_func, password, cargo, filial_id)
VALUES (
           'Administrador SP',
           '11111111111',
           '11999990000',
           'admin@easymoto.com',
           '$2a$10$upnHeELHX/hGNZiWw3Wum.1vpq9DeIZxoafRj5kywxfzlhYG74ely',
           1,
           (SELECT id_filial FROM filial WHERE nome_filial = 'Filial São Paulo')
       );

INSERT INTO funcionario (nome_func, cpf_func, telefone_func, email_func, password, cargo, filial_id)
VALUES (
           'Operador SP',
           '22222222222',
           '11988880000',
           'operador@easymoto.com',
           '$2a$10$upnHeELHX/hGNZiWw3Wum.1vpq9DeIZxoafRj5kywxfzlhYG74ely',
           0,
           (SELECT id_filial FROM filial WHERE nome_filial = 'Filial São Paulo')
       );

INSERT INTO operador (nome_opr, cpf_opr, telefone_opr, email_opr, filial_id)
VALUES (
           'Carlos Admin',
           '33333333333',
           '11999990000',
           'carlos.admin@easymoto.com',
           (SELECT id_filial FROM filial WHERE nome_filial = 'Filial São Paulo')
       );

INSERT INTO operador (nome_opr, cpf_opr, telefone_opr, email_opr, filial_id)
VALUES (
           'Fernanda Operadora',
           '44444444444',
           '11988880000',
           'fernanda.op@easymoto.com',
           (SELECT id_filial FROM filial WHERE nome_filial = 'Filial São Paulo')
       );

INSERT INTO patio (nome_patio, tamanho_patio, andar, filial_id)
VALUES (
           'Pátio Principal',
           'Grande',
           '1',
           (SELECT id_filial FROM filial WHERE nome_filial = 'Filial São Paulo')
       );

INSERT INTO vaga (status_vaga, patio_id, moto_id, fileira, coluna)
VALUES (
           1,
           (SELECT id_patio FROM patio WHERE nome_patio = 'Pátio Principal'),
           NULL,
           'A',
           '1'
       );

INSERT INTO vaga (status_vaga, patio_id, moto_id, fileira, coluna)
VALUES (
           1,
           (SELECT id_patio FROM patio WHERE nome_patio = 'Pátio Principal'),
           NULL,
           'A',
           '2'
       );

INSERT INTO vaga (status_vaga, patio_id, moto_id, fileira, coluna)
VALUES (
           1,
           (SELECT id_patio FROM patio WHERE nome_patio = 'Pátio Principal'),
           NULL,
           'B',
           '1'
       );

INSERT INTO vaga (status_vaga, patio_id, moto_id, fileira, coluna)
VALUES (
           1,
           (SELECT id_patio FROM patio WHERE nome_patio = 'Pátio Principal'),
           NULL,
           'B',
           '2'
       );

INSERT INTO locacao (data_inicio, data_fim, status_locacao, cliente_id)
VALUES (
           TO_DATE('2024-01-10', 'YYYY-MM-DD'),
           TO_DATE('2024-01-17', 'YYYY-MM-DD'),
           0,
           (SELECT id_cliente FROM cliente WHERE cpf_cliente = '84406286098')
       );

INSERT INTO locacao (data_inicio, data_fim, status_locacao, cliente_id)
VALUES (
           TO_DATE('2024-01-11', 'YYYY-MM-DD'),
           TO_DATE('2024-01-18', 'YYYY-MM-DD'),
           1,
           (SELECT id_cliente FROM cliente WHERE cpf_cliente = '58556870074')
       );

INSERT INTO moto (placa, modelo, ano_fabricacao, status_moto, locacao_id, localizacao_id)
VALUES (
           'ABC1D23',
           'Honda CG 160',
           2022,
           1,
           (SELECT id_locacao
            FROM locacao
            WHERE data_inicio = TO_DATE('2024-01-10', 'YYYY-MM-DD')
              AND cliente_id = (SELECT id_cliente FROM cliente WHERE cpf_cliente = '84406286098')),
           (SELECT id_localizacao FROM localizacao WHERE zona_virtual = 'Pátio SP')
       );

INSERT INTO moto (placa, modelo, ano_fabricacao, status_moto, locacao_id, localizacao_id)
VALUES (
           'DEF4G56',
           'Yamaha Fazer 250',
           2021,
           1,
           (SELECT id_locacao
            FROM locacao
            WHERE data_inicio = TO_DATE('2024-01-11', 'YYYY-MM-DD')
              AND cliente_id = (SELECT id_cliente FROM cliente WHERE cpf_cliente = '58556870074')),
           (SELECT id_localizacao FROM localizacao WHERE zona_virtual = 'Zona Sul SP')
       );

INSERT INTO moto (placa, modelo, ano_fabricacao, status_moto, locacao_id, localizacao_id)
VALUES (
           'GHI7J89',
           'Honda PCX 150',
           2020,
           0,
           NULL,
           (SELECT id_localizacao FROM localizacao WHERE zona_virtual = 'Pátio RJ')
       );
