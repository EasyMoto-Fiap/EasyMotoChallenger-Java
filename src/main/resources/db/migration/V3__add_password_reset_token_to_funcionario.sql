ALTER TABLE funcionario ADD COLUMN reset_password_token VARCHAR(255);
ALTER TABLE funcionario ADD COLUMN token_expiry_date TIMESTAMP;
