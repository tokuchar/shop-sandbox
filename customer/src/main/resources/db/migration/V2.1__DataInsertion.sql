
-- -----------------------------------------------------
-- Data for table `CLIENT_DB`.`company`
-- -----------------------------------------------------
START TRANSACTION;
USE `CLIENT_DB`;
INSERT INTO `CLIENT_DB`.`company` (`company_id`, `name`) VALUES (1, 'Corus Servicus');
INSERT INTO `CLIENT_DB`.`company` (`company_id`, `name`) VALUES (2, 'Janusze Biznesu');
INSERT INTO `CLIENT_DB`.`company` (`company_id`, `name`) VALUES (3, 'Ziemniak');
INSERT INTO `CLIENT_DB`.`company` (`company_id`, `name`) VALUES (4, 'Twarde Pierniki');
INSERT INTO `CLIENT_DB`.`company` (`company_id`, `name`) VALUES (5, 'Głupia Gąska');

COMMIT;


-- -----------------------------------------------------
-- Data for table `CLIENT_DB`.`contact`
-- -----------------------------------------------------
START TRANSACTION;
USE `CLIENT_DB`;
INSERT INTO `CLIENT_DB`.`contact` (`contact_id`, `email`, `phone`) VALUES (1, 'phassan_fare@xxbqgstore.com', '(385) 212-8652');
INSERT INTO `CLIENT_DB`.`contact` (`contact_id`, `email`, `phone`) VALUES (2, '8laithtamimf@maymovo.com', '(841) 724-6026');
INSERT INTO `CLIENT_DB`.`contact` (`contact_id`, `email`, `phone`) VALUES (3, 'neloco@greatergoodpoker.org', '+48-515-5568-56');
INSERT INTO `CLIENT_DB`.`contact` (`contact_id`, `email`, `phone`) VALUES (4, '3brid@pealuh.com', '+48-505-5518-82');
INSERT INTO `CLIENT_DB`.`contact` (`contact_id`, `email`, `phone`) VALUES (5, 'aahmedeinsteine@catalogimunity.com', '+48-525-5518-82');
INSERT INTO `CLIENT_DB`.`contact` (`contact_id`, `email`, `phone`) VALUES (6, '9rod@tapiitudulu.com', NULL);
INSERT INTO `CLIENT_DB`.`contact` (`contact_id`, `email`, `phone`) VALUES (7, 'okatyosha13n@dzaf.net', NULL);
INSERT INTO `CLIENT_DB`.`contact` (`contact_id`, `email`, `phone`) VALUES (8, 'officialjess@museumplanet.com', '+48-505-5532-66');
INSERT INTO `CLIENT_DB`.`contact` (`contact_id`, `email`, `phone`) VALUES (9, 'jyase@newbornart.uk', NULL);
INSERT INTO `CLIENT_DB`.`contact` (`contact_id`, `email`, `phone`) VALUES (10, 'galex.santana.52w@oceansofwaves.com', '+48-535-5548-24');

COMMIT;


-- -----------------------------------------------------
-- Data for table `CLIENT_DB`.`address`
-- -----------------------------------------------------
START TRANSACTION;
USE `CLIENT_DB`;
INSERT INTO `CLIENT_DB`.`address` (`address_id`, `city`, `country`, `district`) VALUES (1, 'Wrocław', 'PL', ' Dolnośląskie');
INSERT INTO `CLIENT_DB`.`address` (`address_id`, `city`, `country`, `district`) VALUES (2, 'Koluszki', 'PL', 'Łódzkie');
INSERT INTO `CLIENT_DB`.`address` (`address_id`, `city`, `country`, `district`) VALUES (3, 'Gdynia', 'PL', 'Pomorskie');
INSERT INTO `CLIENT_DB`.`address` (`address_id`, `city`, `country`, `district`) VALUES (4, 'Wieruszów', 'PL', 'Łódzkie');
INSERT INTO `CLIENT_DB`.`address` (`address_id`, `city`, `country`, `district`) VALUES (5, 'Stolec', 'PL', 'Dolnośląskie');
INSERT INTO `CLIENT_DB`.`address` (`address_id`, `city`, `country`, `district`) VALUES (6, 'Kielce', 'PL', 'Świętokrzyskie');
INSERT INTO `CLIENT_DB`.`address` (`address_id`, `city`, `country`, `district`) VALUES (7, NULL, 'PL', 'Podlaskie');
INSERT INTO `CLIENT_DB`.`address` (`address_id`, `city`, `country`, `district`) VALUES (8, 'Poznań', 'PL', 'Wielkopolskie');
INSERT INTO `CLIENT_DB`.`address` (`address_id`, `city`, `country`, `district`) VALUES (9, NULL, 'PL', NULL);
INSERT INTO `CLIENT_DB`.`address` (`address_id`, `city`, `country`, `district`) VALUES (10, 'Lublin', 'PL', 'Lubelskie');

COMMIT;

-- -----------------------------------------------------
-- Data for table `CLIENT_DB`.`personal_data`
-- -----------------------------------------------------
START TRANSACTION;
USE `CLIENT_DB`;
INSERT INTO `CLIENT_DB`.`personal_data` (`personal_data_id`, `name`, `surname`, `birth_date`) VALUES (1, 'Pepe', 'Dziobak', '1999-09-17');
INSERT INTO `CLIENT_DB`.`personal_data` (`personal_data_id`, `name`, `surname`, `birth_date`) VALUES (2, 'Jerzy', 'Jeżowski', '1969-03-13');
INSERT INTO `CLIENT_DB`.`personal_data` (`personal_data_id`, `name`, `surname`, `birth_date`) VALUES (3, 'Edwina', 'Sadowka', '1989-01-12');
INSERT INTO `CLIENT_DB`.`personal_data` (`personal_data_id`, `name`, `surname`, `birth_date`) VALUES (4, 'Kordian', 'Mróz', '1977-11-15');
INSERT INTO `CLIENT_DB`.`personal_data` (`personal_data_id`, `name`, `surname`, `birth_date`) VALUES (5, 'Patryk', 'Walczak', '1989-06-18');
INSERT INTO `CLIENT_DB`.`personal_data` (`personal_data_id`, `name`, `surname`, `birth_date`) VALUES (6, 'Heronim', 'Górksi', '1991-08-22');
INSERT INTO `CLIENT_DB`.`personal_data` (`personal_data_id`, `name`, `surname`, `birth_date`) VALUES (7, 'Adriana', 'Krupa', '1993-06-15');
INSERT INTO `CLIENT_DB`.`personal_data` (`personal_data_id`, `name`, `surname`, `birth_date`) VALUES (8, 'Elwira', 'Stępień', '2002-09-20');
INSERT INTO `CLIENT_DB`.`personal_data` (`personal_data_id`, `name`, `surname`, `birth_date`) VALUES (9, 'Andrzej', 'Dupa', '1909-01-04');
INSERT INTO `CLIENT_DB`.`personal_data` (`personal_data_id`, `name`, `surname`, `birth_date`) VALUES (10, 'Jerzy', 'Pietraszko', '1989-06-27');

COMMIT;


-- -----------------------------------------------------
-- Data for table `CLIENT_DB`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `CLIENT_DB`;
INSERT INTO `CLIENT_DB`.`user` (`user_id`, `company_id`, `address_id`, `contact_id`,`personal_data_id`) VALUES (1, 5, 1, 3, 1);
INSERT INTO `CLIENT_DB`.`user` (`user_id`, `company_id`, `address_id`, `contact_id`,`personal_data_id`) VALUES (2, NULL, 2, 2, 2);
INSERT INTO `CLIENT_DB`.`user` (`user_id`, `company_id`, `address_id`, `contact_id`,`personal_data_id`) VALUES (3, 1, 3, 1, 3);
INSERT INTO `CLIENT_DB`.`user` (`user_id`, `company_id`, `address_id`, `contact_id`,`personal_data_id`) VALUES (4, NULL, 4, 5, 4);
INSERT INTO `CLIENT_DB`.`user` (`user_id`, `company_id`, `address_id`, `contact_id`,`personal_data_id`) VALUES (5, 2, 5, 4, 5);
INSERT INTO `CLIENT_DB`.`user` (`user_id`, `company_id`, `address_id`, `contact_id`,`personal_data_id`) VALUES (6, NULL, 6, 7, 6);
INSERT INTO `CLIENT_DB`.`user` (`user_id`, `company_id`, `address_id`, `contact_id`,`personal_data_id`) VALUES (7, 4, 7, 6, 7);
INSERT INTO `CLIENT_DB`.`user` (`user_id`, `company_id`, `address_id`, `contact_id`,`personal_data_id`) VALUES (8, NULL, 8, 9, 8);
INSERT INTO `CLIENT_DB`.`user` (`user_id`, `company_id`, `address_id`, `contact_id`,`personal_data_id`) VALUES (9, 1, 9, 10, 9);
INSERT INTO `CLIENT_DB`.`user` (`user_id`, `company_id`, `address_id`, `contact_id`,`personal_data_id`) VALUES (10, NULL, 10, 8, 10);

COMMIT;

