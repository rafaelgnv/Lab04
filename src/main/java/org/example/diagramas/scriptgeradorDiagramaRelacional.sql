-- Criação do Banco de Dados
CREATE DATABASE IF NOT EXISTS imobiliaria_db;
USE imobiliaria_db;

-- 1. Tabela Tipo de Imóvel (Independente)
CREATE TABLE tipo_imovel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100)
);

-- 2. Tabela Clientes (Independente)
-- Baseado em @Table(name = "clientes") na classe Cliente.java
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telefone1 VARCHAR(255),
    telefone2 VARCHAR(255),
    dt_nascimento DATE
);

-- 3. Tabela Imóveis
-- Baseado em @Table(name = "imoveis") na classe Imovel.java
CREATE TABLE imoveis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    endereco VARCHAR(255),
    cep VARCHAR(255),
    dormitorios INT,
    banheiros INT,
    suites INT,
    metragem INT,
    valor_aluguel_sugerido DECIMAL(10, 2),
    obs TEXT,
    tipo_imovel_id INT, -- FK para TipoImovel
    proprietario_id INT, -- FK para Cliente
    
    -- Definição das Chaves Estrangeiras
    CONSTRAINT fk_imovel_tipo FOREIGN KEY (tipo_imovel_id) REFERENCES tipo_imovel(id),
    CONSTRAINT fk_imovel_proprietario FOREIGN KEY (proprietario_id) REFERENCES clientes(id)
);

-- 4. Tabela Locação
-- Baseado em @Table(name = "locacao") na classe Locacao.java
CREATE TABLE locacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ativo TINYINT(1), -- Representa Boolean
    data_inicio DATE,
    data_fim DATE,
    dia_vencimento INT,
    perc_multa DECIMAL(10, 2),
    valor_aluguel DECIMAL(10, 2),
    obs TEXT,
    id_imovel INT, -- FK para Imovel
    id_inquilino INT, -- FK para Cliente
    
    -- Definição das Chaves Estrangeiras
    CONSTRAINT fk_locacao_imovel FOREIGN KEY (id_imovel) REFERENCES imoveis(id),
    CONSTRAINT fk_locacao_inquilino FOREIGN KEY (id_inquilino) REFERENCES clientes(id)
);

-- 5. Tabela Aluguéis (Mensalidades)
-- Baseado em @Table(name = "alugueis") na classe Aluguel.java
CREATE TABLE alugueis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dt_vencimento DATE,
    valor_pago DECIMAL(10, 2),
    obs TEXT,
    id_locacao INT, -- FK para Locacao
    
    -- Definição da Chave Estrangeira
    CONSTRAINT fk_aluguel_locacao FOREIGN KEY (id_locacao) REFERENCES locacao(id)
);