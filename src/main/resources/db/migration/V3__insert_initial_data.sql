INSERT INTO empresa (nome_empresa, cnpj)
VALUES ('EasyMoto S.A.', '11222333000144');

INSERT INTO cliente (nome_cliente, cpf_cliente, telefone_cliente, email_cliente)
VALUES ('Bruno Alves', '84406286098', '11911111111', 'bruno.alves@email.com');

INSERT INTO cliente (nome_cliente, cpf_cliente, telefone_cliente, email_cliente)
VALUES ('Carla Dias', '58556870074', '21922222222', 'carla.dias@email.com');

INSERT INTO cliente (nome_cliente, cpf_cliente, telefone_cliente, email_cliente)
VALUES ('Fernando Costa', '27183062031', '11933333333', 'fernando.costa@email.com');

INSERT INTO localizacao (status_loc, data_hora, zona_virtual, latitude, longitude)
VALUES (0, TO_TIMESTAMP('2025-09-24 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Pátio SP', -23.5613, -46.6565);

INSERT INTO localizacao (status_loc, data_hora, zona_virtual, latitude, longitude)
VALUES (1, TO_TIMESTAMP('2025-09-24 10:05:00', 'YYYY-MM-DD HH24:MI:SS'), 'Zona Sul SP', -23.6000, -46.6800);

INSERT INTO localizacao (status_loc, data_hora, zona_virtual, latitude, longitude)
VALUES (2, TO_TIMESTAMP('2025-09-24 10:10:00', 'YYYY-MM-DD HH24:MI:SS'), 'Pátio RJ', -22.9068, -43.1729);
