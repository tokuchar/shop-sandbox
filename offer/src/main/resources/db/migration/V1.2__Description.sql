-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema OFFERS_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OFFERS_DB`;
USE `OFFERS_DB`;

-- -----------------------------------------------------
-- Table `OFFERS_DB`.`availability`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OFFERS_DB`.`availability`
(
    `availability_id` INT     NOT NULL,
    `day`             INT     NULL DEFAULT NULL,
    `start_time`      TIME(0) NULL DEFAULT NULL,
    `end_time`        TIME(0) NULL DEFAULT NULL,
    PRIMARY KEY (`availability_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OFFERS_DB`.`price`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OFFERS_DB`.`price`
(
    `price_id` INT         NOT NULL,
    `min`      VARCHAR(45) NULL DEFAULT NULL,
    `max`      VARCHAR(45) NULL DEFAULT NULL,
    `currency` CHAR(3)     NULL DEFAULT 'PLN',
    PRIMARY KEY (`price_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OFFERS_DB`.`offer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OFFERS_DB`.`offer`
(
    `offer_id`        INT         NOT NULL,
    `title`           VARCHAR(45) NULL DEFAULT NULL,
    `description`     VARCHAR(45) NULL DEFAULT NULL,
    `start_date`      DATE        NULL DEFAULT NULL,
    `end_date`        DATE        NULL DEFAULT NULL,
    `price_id`        INT         NULL DEFAULT NULL,
    `availability_id` INT         NULL DEFAULT NULL,
    PRIMARY KEY (`offer_id`),
    INDEX `fk_avaiablility_id_idx` (`availability_id` ASC),
    INDEX `fk_price__id_idx` (`price_id` ASC),
    CONSTRAINT `fk_avaiablility_id`
        FOREIGN KEY (`availability_id`)
            REFERENCES `OFFERS_DB`.`availability` (`availability_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_price__id`
        FOREIGN KEY (`price_id`)
            REFERENCES `OFFERS_DB`.`price` (`price_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
