CREATE TABLE auditoria_moto (
    id_audit BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255),
    operacao VARCHAR(10),
    data_hora TIMESTAMP NOT NULL,
    old_values CLOB,
    new_values CLOB
);

INSERT INTO auditoria_moto (user_name, operacao, data_hora, old_values, new_values)
VALUES ('admin@easymoto.com', 'INSERT', '2025-09-25 10:00:00',
        NULL,
        'placa=XYZ1A23, modelo=Honda Biz, ano=2024, status=DISPONIVEL');

INSERT INTO auditoria_moto (user_name, operacao, data_hora, old_values, new_values)
VALUES ('user@easymoto.com', 'UPDATE', '2025-09-25 11:30:00',
        'status=DISPONIVEL',
        'status=EM_LOCACAO');

INSERT INTO auditoria_moto (user_name, operacao, data_hora, old_values, new_values)
VALUES ('admin@easymoto.com', 'UPDATE', '2025-09-26 09:05:00',
        'placa=ABC1D23, status=EM_LOCACAO',
        'placa=ABC1D23, status=EM_MANUTENCAO');

INSERT INTO auditoria_moto (user_name, operacao, data_hora, old_values, new_values)
VALUES ('admin@easymoto.com', 'DELETE', '2025-09-26 15:00:00',
        'placa=DEF4E56, modelo=Yamaha Fazer 250, ano=2022, status=EM_MANUTENCAO',
        NULL);

INSERT INTO auditoria_moto (user_name, operacao, data_hora, old_values, new_values)
VALUES ('user@easymoto.com', 'INSERT', '2025-09-27 08:45:00',
        NULL,
        'placa=MNO3P45, modelo=Suzuki GSX-S750, ano=2023, status=DISPONIVEL');