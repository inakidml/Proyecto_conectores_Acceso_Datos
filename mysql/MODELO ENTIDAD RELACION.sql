-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`EQUIPO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`EQUIPO` (
  `idEquipo` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Año_Fundacion` INT NOT NULL,
  `Presidente` VARCHAR(80) NULL,
  `Pabellon` VARCHAR(80) NULL,
  `Patrocinador` VARCHAR(80) NULL,
  PRIMARY KEY (`idEquipo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`JUGADOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`JUGADOR` (
  `idJUGADOR` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellido1` VARCHAR(45) NOT NULL,
  `Apellido2` VARCHAR(45) NULL,
  `Altura` FLOAT NULL,
  `Peso` FLOAT NULL,
  `Posicion` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(200) NULL,
  `EQUIPO_idEquipo` INT NOT NULL,
  PRIMARY KEY (`idJUGADOR`),
  INDEX `fk_JUGADOR_EQUIPO1_idx` (`EQUIPO_idEquipo` ASC),
  CONSTRAINT `fk_JUGADOR_EQUIPO1`
    FOREIGN KEY (`EQUIPO_idEquipo`)
    REFERENCES `mydb`.`EQUIPO` (`idEquipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`INCIDENCIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`INCIDENCIA` (
  `idINCIDENCIA` INT NOT NULL,
  `Tipo_Incidencia` VARCHAR(45) NOT NULL,
  `Sancion` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`idINCIDENCIA`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ENTRENAMIENTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ENTRENAMIENTO` (
  `idENTRENAMIENTO` INT NOT NULL,
  `Tipo_Entrenamiento` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(200) NULL,
  PRIMARY KEY (`idENTRENAMIENTO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`INCIDENCIA_has_JUGADOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`INCIDENCIA_has_JUGADOR` (
  `INCIDENCIA_idINCIDENCIA` INT NOT NULL,
  `JUGADOR_idJUGADOR` INT NOT NULL,
  `Fecha` DATETIME NOT NULL,
  PRIMARY KEY (`INCIDENCIA_idINCIDENCIA`, `JUGADOR_idJUGADOR`, `Fecha`),
  INDEX `fk_INCIDENCIA_has_JUGADOR_JUGADOR1_idx` (`JUGADOR_idJUGADOR` ASC),
  INDEX `fk_INCIDENCIA_has_JUGADOR_INCIDENCIA1_idx` (`INCIDENCIA_idINCIDENCIA` ASC),
  CONSTRAINT `fk_INCIDENCIA_has_JUGADOR_INCIDENCIA1`
    FOREIGN KEY (`INCIDENCIA_idINCIDENCIA`)
    REFERENCES `mydb`.`INCIDENCIA` (`idINCIDENCIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_INCIDENCIA_has_JUGADOR_JUGADOR1`
    FOREIGN KEY (`JUGADOR_idJUGADOR`)
    REFERENCES `mydb`.`JUGADOR` (`idJUGADOR`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`JUGADOR_has_ENTRENAMIENTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`JUGADOR_has_ENTRENAMIENTO` (
  `JUGADOR_idJUGADOR` INT NOT NULL,
  `ENTRENAMIENTO_idENTRENAMIENTO` INT NOT NULL,
  `Fecha` DATETIME NOT NULL,
  `Duracion` TIME NOT NULL,
  PRIMARY KEY (`JUGADOR_idJUGADOR`, `ENTRENAMIENTO_idENTRENAMIENTO`, `Fecha`),
  INDEX `fk_JUGADOR_has_ENTRENAMIENTO_ENTRENAMIENTO1_idx` (`ENTRENAMIENTO_idENTRENAMIENTO` ASC),
  INDEX `fk_JUGADOR_has_ENTRENAMIENTO_JUGADOR1_idx` (`JUGADOR_idJUGADOR` ASC),
  CONSTRAINT `fk_JUGADOR_has_ENTRENAMIENTO_JUGADOR1`
    FOREIGN KEY (`JUGADOR_idJUGADOR`)
    REFERENCES `mydb`.`JUGADOR` (`idJUGADOR`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_JUGADOR_has_ENTRENAMIENTO_ENTRENAMIENTO1`
    FOREIGN KEY (`ENTRENAMIENTO_idENTRENAMIENTO`)
    REFERENCES `mydb`.`ENTRENAMIENTO` (`idENTRENAMIENTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
