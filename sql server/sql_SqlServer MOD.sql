-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP DATABASE Baloncesto;
CREATE DATABASE Baloncesto;
USE Baloncesto;

-- -----------------------------------------------------
-- Table EQUIPO
-- -----------------------------------------------------
CREATE TABLE EQUIPO (
  idEquipo INT NOT NULL,
  Nombre VARCHAR(45) NOT NULL,
  Año_Fundacion INT NOT NULL,
  Presidente VARCHAR(80) NULL,
  Pabellon VARCHAR(80) NULL,
  Patrocinador VARCHAR(80) NULL,
  PRIMARY KEY (idEquipo))
;


-- -----------------------------------------------------
-- Table JUGADOR
-- -----------------------------------------------------
DROP TABLE JUGADOR;
CREATE TABLE JUGADOR (
  idJUGADOR INT NOT NULL,
  Nombre VARCHAR(45) NOT NULL,
  Apellido1 VARCHAR(45) NOT NULL,
  Apellido2 VARCHAR(45) NULL,
  Altura FLOAT NULL,
  Peso FLOAT NULL,
  Posicion VARCHAR(45) NULL,
  Descripcion VARCHAR(200) NULL,
  EQUIPO_idEquipo INT NOT NULL,
  PRIMARY KEY (idJUGADOR),
  CONSTRAINT fk_JUGADOR_EQUIPO1
    FOREIGN KEY (EQUIPO_idEquipo)
    REFERENCES EQUIPO (idEquipo)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;


-- -----------------------------------------------------
-- Table INCIDENCIA
-- -----------------------------------------------------
CREATE TABLE INCIDENCIA (
  idINCIDENCIA INT NOT NULL,
  Tipo_Incidencia VARCHAR(45) NOT NULL,
  Sancion VARCHAR(45) NOT NULL,
  Descripcion VARCHAR(45) NULL,
  PRIMARY KEY (idINCIDENCIA))
;


-- -----------------------------------------------------
-- Table ENTRENAMIENTO
-- -----------------------------------------------------
CREATE TABLE  ENTRENAMIENTO (
  idENTRENAMIENTO INT NOT NULL,
  Tipo_Entrenamiento VARCHAR(45) NOT NULL,
  Descripcion VARCHAR(200) NULL,
  PRIMARY KEY (idENTRENAMIENTO))
;

-- -----------------------------------------------------
-- Table INCIDENCIA_has_JUGADOR
-- -----------------------------------------------------
use Baloncesto;
drop table INCIDENCIA_has_JUGADOR;
CREATE TABLE  INCIDENCIA_has_JUGADOR (
  INCIDENCIA_idINCIDENCIA INT NOT NULL,
  JUGADOR_idJUGADOR INT NOT NULL,
  Fecha DATETIME NOT NULL,
  PRIMARY KEY (INCIDENCIA_idINCIDENCIA, JUGADOR_idJUGADOR, Fecha),
  CONSTRAINT fk_INCIDENCIA_has_JUGADOR_INCIDENCIA1
    FOREIGN KEY (INCIDENCIA_idINCIDENCIA)
    REFERENCES INCIDENCIA (idINCIDENCIA)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;
ALTER TABLE INCIDENCIA_has_JUGADOR ADD CONSTRAINT fk_INCIDENCIA_has_JUGADOR_JUGADOR1
    FOREIGN KEY (JUGADOR_idJUGADOR)
    REFERENCES JUGADOR (idJUGADOR)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;


-- -----------------------------------------------------
-- Table JUGADOR_has_ENTRENAMIENTO
-- -----------------------------------------------------
DROP TABLE  JUGADOR_has_ENTRENAMIENTO;
CREATE TABLE  JUGADOR_has_ENTRENAMIENTO (
  JUGADOR_idJUGADOR INT NOT NULL,
  ENTRENAMIENTO_idENTRENAMIENTO INT NOT NULL,
  Fecha DATETIME NOT NULL,
  Duracion TIME NOT NULL,
  PRIMARY KEY (JUGADOR_idJUGADOR, ENTRENAMIENTO_idENTRENAMIENTO, Fecha),
  CONSTRAINT fk_JUGADOR_has_ENTRENAMIENTO_JUGADOR1
    FOREIGN KEY (JUGADOR_idJUGADOR)
    REFERENCES JUGADOR (idJUGADOR)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_JUGADOR_has_ENTRENAMIENTO_ENTRENAMIENTO1
    FOREIGN KEY (ENTRENAMIENTO_idENTRENAMIENTO)
    REFERENCES ENTRENAMIENTO (idENTRENAMIENTO)
    ON DELETE CASCADE
    ON UPDATE CASCADE)	
;

SELECT * FROM JUGADOR_has_ENTRENAMIENTO;