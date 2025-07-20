CREATE TABLE faixa_horario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    hora_inicio TIME NOT NULL,
    hora_fim TIME NOT NULL,
    descricao VARCHAR(255)
);

CREATE TABLE tipo_caminhao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(80) NOT NULL
);

CREATE TABLE tipo_paletizacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(80) NOT NULL
);

CREATE TABLE pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    qtd_paletes INT NOT NULL
);

CREATE TABLE agendamento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data DATE NOT NULL,
    id_faixa_horario INT NOT NULL,
    id_pedido INT NOT NULL,
    fornecedor VARCHAR(80) NOT NULL,
    email_fornecedor VARCHAR(80) NOT NULL,
    id_tipo_caminhao INT NOT NULL,
    id_tipo_paletizacao INT NOT NULL,
    obs VARCHAR(200),
    CONSTRAINT fk_faixa_horario FOREIGN KEY (id_faixa_horario) REFERENCES faixa_horario(id),
    CONSTRAINT fk_tipo_caminhao FOREIGN KEY (id_tipo_caminhao) REFERENCES tipo_caminhao(id),
    CONSTRAINT fk_tipo_paletizacao FOREIGN KEY (id_tipo_paletizacao) REFERENCES tipo_paletizacao(id),
    CONSTRAINT fk_pedido FOREIGN KEY (id_pedido) REFERENCES pedido(id)
);



