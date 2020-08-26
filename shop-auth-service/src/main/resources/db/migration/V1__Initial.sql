CREATE TABLE IF NOT EXISTS `AUTH_DB`.`user` (
  `userId` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `isAccountNonExpired` INT NOT NULL,
  `isAccountNonLocked` INT NOT NULL,
  `isCredentialsNonExpired` INT NULL,
  `isEnabled` INT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `userId_UNIQUE` (`userId` ASC) VISIBLE,
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
  `user_id` INT NOT NULL,
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
    REFERENCES `AUTH_DB`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;