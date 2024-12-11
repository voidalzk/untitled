CREATE TABLE Empregado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE Projeto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE empregado_projeto (
    empregado_id BIGINT NOT NULL,
    projeto_id BIGINT NOT NULL,
    PRIMARY KEY (empregado_id, projeto_id),
    FOREIGN KEY (empregado_id) REFERENCES Empregado(id) ON DELETE CASCADE,
    FOREIGN KEY (projeto_id) REFERENCES Projeto(id) ON DELETE CASCADE
);

CREATE TABLE Equipamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(500)
);

CREATE TABLE Alocacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    equipamento_id BIGINT NOT NULL,
    projeto_id BIGINT NOT NULL,
    FOREIGN KEY (equipamento_id) REFERENCES Equipamento(id) ON DELETE CASCADE,
    FOREIGN KEY (projeto_id) REFERENCES Projeto(id) ON DELETE CASCADE,
    CHECK (data_inicio <= data_fim)
);

CREATE INDEX idx_alocacao_equipamento ON Alocacao(equipamento_id, data_inicio, data_fim);