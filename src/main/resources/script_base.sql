DROP DATABASE IF EXISTS wildfly;
CREATE DATABASE wildfly;
USE wildfly;

CREATE TABLE agencia (
 id_agencia INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 razao_social VARCHAR(200) NOT NULL,
 fantasia VARCHAR(150) NOT NULL,
 cnpj VARCHAR(21) NOT NULL,
 insc_estadual VARCHAR(12) NOT NULL,
 responsavel VARCHAR(100),
 dominio VARCHAR(64) NOT NULL,
 logradouro VARCHAR(200) NOT NULL,
 bairro VARCHAR(150) NOT NULL,
 numero int(11) NOT NULL,
 cep VARCHAR(9) NOT NULL,
 cidade VARCHAR(100) NOT NULL,
 uf VARCHAR(2) NOT NULL,
 telefone VARCHAR(14),
 email VARCHAR(45),
 data_cadastro TIMESTAMP NOT NULL ,
 data_manutencao TIMESTAMP,
 ativo BIT(0) NOT NULL
);

CREATE TABLE cliente (
 id_cliente INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 nome VARCHAR(50) NOT NULL,
 data_nascimento TIMESTAMP NOT NULL,
 cpf VARCHAR(15) NOT NULL,
 rg VARCHAR(10),
 cnh VARCHAR(11) NOT NULL,
 validade_cnh TIMESTAMP NOT NULL,
 estado_emissor VARCHAR(45) NOT NULL,
 genero CHAR(1),
 logradouro VARCHAR(200) NOT NULL,
 bairro VARCHAR(150) NOT NULL,
 numero INT(11) NOT NULL,
 cep VARCHAR(9) NOT NULL,
 cidade VARCHAR(100) NOT NULL,
 uf VARCHAR(2) NOT NULL,
 telefone VARCHAR(14),
 email VARCHAR(45),
 id_agencia INT(11),
 id_funcionario	 INT(11) NOT NULL,
 data_cadastro TIMESTAMP NOT NULL ,
 data_manutencao DATETIME,
 ativo BIT(0) NOT NULL,

 FOREIGN KEY (id_agencia) REFERENCES agencia (id_agencia)
);

CREATE TABLE funcionario (
 id_funcionario INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 nome VARCHAR(50) NOT NULL,
 data_nascimento TIMESTAMP NOT NULL,
 cpf VARCHAR(15) NOT NULL,
 rg VARCHAR(10) NOT NULL,
 genero CHAR(1) NOT NULL,
 logradouro VARCHAR(200) NOT NULL,
 numero INT NOT NULL,
 bairro VARCHAR(150),
 cep VARCHAR(9) NOT NULL,
 cidade VARCHAR(100) NOT NULL,
 uf VARCHAR(2) NOT NULL,
 telefone VARCHAR(14),
 email VARCHAR(45),
 funcionario_supervisor INT(11),
 id_agencia INT(11),
 data_cadastro TIMESTAMP NOT NULL ,
 data_manutencao TIMESTAMP,
 ativo BIT(0) NOT NULL NOT NULL,

 FOREIGN KEY (funcionario_supervisor) REFERENCES funcionario (id_funcionario),
 FOREIGN KEY (id_agencia) REFERENCES agencia (id_agencia)
);

CREATE TABLE usuario (
	id_usuario INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome_usuario VARCHAR(64) UNIQUE NOT NULL,
    dominio VARcHAR(64) NOT NULL,
    senha  VARCHAR(64) NOT NULL,
    nivel INT NOT NULL,
    id_funcionario INT NOT NULL UNIQUE,
    data_cadastro TIMESTAMP NOT NULL ,
	data_manutencao TIMESTAMP,
	ativo BIT(0) NOT NULL NOT NULL,
    
    FOREIGN KEY (id_funcionario) REFERENCES funcionario (id_funcionario)
);

CREATE TABLE veiculo (
 id_veiculo INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 modelo VARCHAR(50) NOT NULL,
 fabricante VARCHAR(50) NOT NULL,
 imagem VARCHAR(100),
 ano INT(11) NOT NULL,
 grupo INT(11) NOT NULL,
 chassi VARCHAR(17),
 placa VARCHAR(8) NOT NULL,
 cidade VARCHAR(150) NOT NULL,
 uf VARCHAR(2) NOT NULL,
 km_rodado DOUBLE NOT NULL,
 preco_km_livre DOUBLE NOT NULL,
 km_preco_km_controlado DOUBLE NOT NULL,
 status INT(11) NOT NULL,
 id_agencia INT(11) NOT NULL,
 id_funcionario INT(11) NOT NULL,
 data_cadastro TIMESTAMP NOT NULL ,
 data_manutencao TIMESTAMP,
 ativo BIT(0) NOT NULL,

 FOREIGN KEY (id_agencia) REFERENCES agencia (id_agencia)
);

CREATE TABLE acessorio_veiculo (
	id_acessorio INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    codigo_acessorio INT NOT NULL,
    id_veiculo INT NOT NULL,
    data_cadastro TIMESTAMP NOT NULL ,
    
    FOREIGN KEY (id_veiculo) REFERENCES veiculo (id_veiculo)
);

CREATE TABLE locacao (
 id_locacao INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 data_hora_locacao DATETIME NOT NULL,
 data_hora_prevista_devolucao DATETIME NOT NULL,
 data_hora_devolucao DATETIME,
 agencia_devolucao INT(11) NOT NULL,
 tipo_tarifa INT(11) NOT NULL,
 km_locacao DOUBLE NOT NULL,
 km_devolucao INT(11) NOT NULL,
 valor DOUBLE NOT NULL,
 valor_acrescimo DOUBLE,
 status INT,	
 id_veiculo INT(11),
 id_cliente INT(11),
 id_pagamento INT NOT NULL,
 id_funcionario INT NOT NULL,
 id_agencia INT NOT NULL,

 FOREIGN KEY (id_veiculo) REFERENCES veiculo (id_veiculo)
);

CREATE TABLE pagamento (
 id_pagamento INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 tipo VARCHAR(50),
 nome_titular VARCHAR(100),
 cpf VARCHAR(14),
 data_pagamento DATETIME NOT NULL,
 valor DOUBLE,
 id_locacao INT NOT NULL,
 
 FOREIGN KEY (id_locacao) REFERENCES locacao (id_locacao)
);

CREATE TABLE uf (
 uf VARCHAR(2) NOT NULL,
 estado VARCHAR(20) NOT NULL,
 codigo_ibge INT(10) NOT NULL
);

ALTER TABLE uf ADD CONSTRAINT PK_uf PRIMARY KEY (uf);

CREATE TABLE cidade (
 id_cidade INT(10) NOT NULL,
 cidade VARCHAR(100) NOT NULL,
 codigo_ibge INT(10) NOT NULL,
 uf VARCHAR(2)
);

ALTER TABLE cidade ADD CONSTRAINT PK_cidade PRIMARY KEY (id_cidade);


CREATE TABLE bairro (
 id_bairro INT(10) NOT NULL,
 bairro VARCHAR(50) NOT NULL,
 id_cidade INT(10) NOT NULL
);

ALTER TABLE bairro ADD CONSTRAINT PK_bairro PRIMARY KEY (id_bairro);


CREATE TABLE endereco (
 cep VARCHAR(10) NOT NULL,
 endereco VARCHAR(200) NOT NULL,
 id_cidade INT(10) NOT NULL,
 id_bairro INT(10) NOT NULL
);

ALTER TABLE endereco ADD CONSTRAINT PK_endereco PRIMARY KEY (cep);

ALTER TABLE cidade ADD CONSTRAINT FK_tend_cidade_1 FOREIGN KEY (uf) REFERENCES uf (uf);

ALTER TABLE bairro ADD CONSTRAINT FK_bairro_0 FOREIGN KEY (id_cidade) REFERENCES cidade (id_cidade);

ALTER TABLE endereco ADD CONSTRAINT FK_endereco_0 FOREIGN KEY (id_cidade) REFERENCES cidade (id_cidade);
ALTER TABLE endereco ADD CONSTRAINT FK_endereco_1 FOREIGN KEY (id_bairro) REFERENCES bairro (id_bairro);
