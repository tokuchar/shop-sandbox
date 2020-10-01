CREATE TABLE IF NOT EXISTS `AUTH_DB`.`user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `is_account_non_expired` INT NOT NULL,
  `is_account_non_locked` INT NOT NULL,
  `is_credentials_non_expired` INT NULL,
  `is_enabled` INT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AUTH_DB`.`authority` (
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AUTH_DB`.`users_authorities` (
  `authority_name` VARCHAR(30) NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`authority_name`, `user_id`),
  INDEX `fk_Authority_has_User_User1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Authority_has_User_Authority_idx` (`authority_name` ASC) VISIBLE,
  CONSTRAINT `fk_Authority_has_User_Authority`
    FOREIGN KEY (`authority_name`)
    REFERENCES `AUTH_DB`.`authority` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Authority_has_User_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `AUTH_DB`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;