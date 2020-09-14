-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema OFFER_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OFFER_DB`;
USE `OFFER_DB`;

-- -----------------------------------------------------
-- Table `OFFER_DB`.`availability`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OFFER_DB`.`availability`
(
    `availability_id` INT  NOT NULL,
    `day`             INT  NULL DEFAULT NULL,
    `start_time`      TIME NULL DEFAULT NULL,
    `end_time`        TIME NULL DEFAULT NULL,
    PRIMARY KEY (`availability_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OFFER_DB`.`offer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OFFER_DB`.`offer`
(
    `offer_id`        INT         NOT NULL,
    `title`           VARCHAR(45) NULL DEFAULT NULL,
    `description`     VARCHAR(45) NULL DEFAULT NULL,
    `start_date`      DATE        NULL DEFAULT NULL,
    `end_date`        DATE        NULL DEFAULT NULL,
    `price`           INT         NULL DEFAULT NULL,
    `availability_id` INT         NULL DEFAULT NULL,
    PRIMARY KEY (`offer_id`),
    INDEX `fk_avaiablility_id_idx` (`availability_id` ASC),
    CONSTRAINT `fk_avaiablility_id`
        FOREIGN KEY (`availability_id`)
            REFERENCES `OFFER_DB`.`availability` (`availability_id`)
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
