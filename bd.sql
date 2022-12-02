SELECT 'CREATE DATABASE appreservas'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'appreservas')\gexec;

\c appreservas;

CREATE SCHEMA IF NOT EXISTS auth;

SET search_path TO auth;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
  appuserid SERIAL PRIMARY KEY,
  username VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  firstname VARCHAR(45) ,
  lastname VARCHAR(45) ,
  roles VARCHAR(45) NOT NULL,
  password VARCHAR(255) NOT NULL
);

CREATE SCHEMA IF NOT EXISTS reservas;

SET search_path TO reservas;

DROP TABLE IF EXISTS Utilizador;
CREATE TABLE IF NOT EXISTS Utilizador (
  UtilizadorId SERIAL PRIMARY KEY,
  Role VARCHAR(45) NOT NULL
);
-- -----------------------------------------------------
DROP TABLE IF EXISTS Organizador;

CREATE TABLE IF NOT EXISTS Organizador (
  OrganizadorId SERIAL PRIMARY KEY NOT NULL,
  Nome VARCHAR(45) NOT NULL,
  Contacto VARCHAR(45) NULL,
  UserId INTEGER NOT NULL references Utilizador(UtilizadorId) 
);
-- -----------------------------------------------------
DROP TABLE IF EXISTS Cliente;

CREATE TABLE IF NOT EXISTS Cliente(
  ClienteId SERIAL PRIMARY KEY NOT NULL,
  UserId INTEGER NOT NULL references Utilizador(UtilizadorId),
  Nome VARCHAR(45) NOT NULL,
  Telemovel INTEGER NULL,
  Email VARCHAR(45) NULL,
  NIF INT NULL
);

-- -----------------------------------------------------
DROP TABLE IF EXISTS Sala ;

CREATE TABLE IF NOT EXISTS Sala (
  SalaId SERIAL PRIMARY KEY NOT NULL,
  Nome VARCHAR(45) NULL,
  Localizacao VARCHAR(45) NULL,
  LugaresMarcados BOOLEAN NOT NULL,
  Lotacao INT NULL
);

-- -----------------------------------------------------
DROP TABLE IF EXISTS Evento ;

CREATE TABLE IF NOT EXISTS Evento (
  EventoId SERIAL PRIMARY KEY,
  SalaId INTEGER NOT NULL references Sala(SalaId),
  OrganizadorId INTEGER NOT NULL references Organizador(OrganizadorId),
  Nome VARCHAR(45) NULL,
  Descricao VARCHAR(45) NULL,
  Categoria VARCHAR(45) NULL,
  IMDBId VARCHAR(45) NULL
);

-- -----------------------------------------------------
DROP TABLE IF EXISTS Sessao ;

CREATE TABLE IF NOT EXISTS Sessao (
  SessaoId SERIAL PRIMARY KEY NOT NULL,
  EventoId INTEGER NOT NULL references Evento(EventoId),
  SalaId INTEGER NOT NULL references Sala(SalaId),
  Duracao INT NULL,
  Data timestamp NULL
);

-- -----------------------------------------------------
DROP TABLE IF EXISTS Reserva ;

CREATE TABLE IF NOT EXISTS Reserva (
  ReservaId SERIAL PRIMARY KEY NOT NULL,
  SessaoId INTEGER NOT NULL references Sessao(SessaoId),
  ClienteId INTEGER NOT NULL references Cliente(ClienteId),
  Custo DECIMAL NULL
);

-- -----------------------------------------------------
DROP TABLE IF EXISTS Lugar ;

CREATE TABLE IF NOT EXISTS Lugar (
  LugarId SERIAL PRIMARY KEY NOT NULL,
  SalaId INTEGER NOT NULL references Sala(SalaId),
  Nome VARCHAR(45) NULL,
  Quantidade INT NULL,
  Valor DECIMAL NULL
);

-- -----------------------------------------------------
DROP TABLE IF EXISTS ReservaLugar ;

CREATE TABLE IF NOT EXISTS ReservaLugar  (
  ReservaId INTEGER NOT NULL references Reserva(ReservaId),
  LugarId INTEGER NOT NULL references Lugar(LugarId),
  CONSTRAINT ReservaLugarKey PRIMARY KEY (ReservaId, LugarId)
);
