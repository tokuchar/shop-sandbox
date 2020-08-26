-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CLIENT_DB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CLIENT_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CLIENT_DB` DEFAULT CHARACTER SET utf8 ;
USE `CLIENT_DB` ;

-- -----------------------------------------------------
-- Table `CLIENT_DB`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENT_DB`.`company` (
  `company_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`company_id`),
  UNIQUE INDEX `company_id_UNIQUE` (`company_id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CLIENT_DB`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENT_DB`.`contact` (
  `contact_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(15) NULL,
  PRIMARY KEY (`contact_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CLIENT_DB`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENT_DB`.`address` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NULL,
  `district` VARCHAR(45) NULL,
    UNIQUE INDEX `address_id_UNIQUE` (`address_id` ASC) VISIBLE,
  PRIMARY KEY (`address_id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `CLIENT_DB`.`personal_data` (
  `personal_data_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `birth_date` DATE,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`personal_data_id`),
  UNIQUE INDEX `personal_data_id_UNIQUE` (`personal_data_id` ASC) VISIBLE,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE
  )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `CLIENT_DB`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENT_DB`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `company_id` INT NULL,
  `address_id` INT NULL,
  `contact_id` INT NULL,
  `personal_data_id` INT NULL,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`),
  INDEX `company_id_idx` (`company_id` ASC) VISIBLE,
  INDEX `contact_id_idx` (`contact_id` ASC) VISIBLE,
  INDEX `address_id_idx` (`address_id` ASC) VISIBLE,
  INDEX `personal_data_id_idx` (`personal_data_id` ASC) VISIBLE,
  CONSTRAINT `company_id`
    FOREIGN KEY (`company_id`)
    REFERENCES `CLIENT_DB`.`company` (`company_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `contact_id_fk`
    FOREIGN KEY (`contact_id`)
    REFERENCES `CLIENT_DB`.`contact` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `address_id_fk`
    FOREIGN KEY (`address_id`)
    REFERENCES `CLIENT_DB`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `personal_data_id_fk`
    FOREIGN KEY (`personal_data_id`)
    REFERENCES `CLIENT_DB`.`personal_data` (`personal_data_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CLIENT_DB`.`personal_data`
-- -----------------------------------------------------



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
