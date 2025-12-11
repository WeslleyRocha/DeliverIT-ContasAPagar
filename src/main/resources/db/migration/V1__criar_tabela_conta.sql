CREATE TABLE conta (
    id_conta BIGINT AUTO_INCREMENT PRIMARY KEY,

    nome VARCHAR(200) NOT NULL,
    valor_original DECIMAL(19, 2) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor_corrigido DECIMAL(19, 2),
    dias_atraso INTEGER,
);