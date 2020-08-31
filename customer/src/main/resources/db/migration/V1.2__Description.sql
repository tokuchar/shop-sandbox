-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema CLIENT_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CLIENT_DB`;
-- -----------------------------------------------------
-- Schema user_db
-- -----------------------------------------------------
USE `CLIENT_DB`;

-- -----------------------------------------------------
-- Table `CLIENT_DB`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENT_DB`.`address`
(
    `address_id` INT         NOT NULL,
    `city`       VARCHAR(45) NULL DEFAULT NULL,
    `country`    VARCHAR(45) NULL DEFAULT 'PL',
    `district`   VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`address_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CLIENT_DB`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENT_DB`.`company`
(
    `company_id` INT         NOT NULL,
    `name`       VARCHAR(45) NOT NULL,
    PRIMARY KEY (`company_id`),
    UNIQUE INDEX `company_id_UNIQUE` (`company_id` ASC),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CLIENT_DB`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENT_DB`.`contact`
(
    `contact_id` INT         NOT NULL,
    `email`      VARCHAR(45) NOT NULL,
    `phone`      VARCHAR(15) NULL DEFAULT NULL,
    PRIMARY KEY (`contact_id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CLIENT_DB`.`personal_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENT_DB`.`personal_data`
(
    `personal_data_id` INT         NOT NULL,
    `name`             VARCHAR(45) NOT NULL,
    `surname`          VARCHAR(45) NULL DEFAULT NULL,
    `birth_date`       DATE        NULL,
    PRIMARY KEY (`personal_data_id`),
    UNIQUE INDEX `personal_data_id_UNIQUE` (`personal_data_id` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CLIENT_DB`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENT_DB`.`user`
(
    `user_id`          INT NOT NULL,
    `company_id`       INT NULL DEFAULT NULL,
    `address_id`       INT NULL DEFAULT NULL,
    `contact_id`       INT NULL DEFAULT NULL,
    `personal_data_id` INT NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
    INDEX `company_id_idx` (`company_id` ASC),
    INDEX `contact_id_idx` (`contact_id` ASC),
    INDEX `address_id_idx` (`address_id` ASC),
    INDEX `personal_data_idx` (`personal_data_id` ASC),
    UNIQUE INDEX `contact_id_UNIQUE` (`contact_id` ASC),
    UNIQUE INDEX `personal_data_id_UNIQUE` (`personal_data_id` ASC),
    UNIQUE INDEX `address_id_UNIQUE` (`address_id` ASC),
    CONSTRAINT `address_id_fk`
        FOREIGN KEY (`address_id`)
            REFERENCES `CLIENT_DB`.`address` (`address_id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `company_id`
        FOREIGN KEY (`company_id`)
            REFERENCES `CLIENT_DB`.`company` (`company_id`),
    CONSTRAINT `contact_id_fk`
        FOREIGN KEY (`contact_id`)
            REFERENCES `CLIENT_DB`.`contact` (`contact_id`),
    CONSTRAINT `personal_data_id_fk`
        FOREIGN KEY (`personal_data_id`)
            REFERENCES `CLIENT_DB`.`personal_data` (`personal_data_id`)
)
    ENGINE = InnoDB;



SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;