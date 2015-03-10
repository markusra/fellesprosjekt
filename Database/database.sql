
CREATE SCHEMA IF NOT EXISTS `kalenderdatabase`;


CREATE TABLE IF NOT EXISTS `kalenderdatabase`.`BRUKER` (
  `brukerID` INT NOT NULL AUTO_INCREMENT,
  `brukernavn` VARCHAR(45) NOT NULL UNIQUE,
  `passord` VARCHAR(100) NOT NULL,
  `fornavn` VARCHAR(45) NULL,
  `etternavn` VARCHAR(45) NULL,
  `epost` VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (`brukerID`);



CREATE TABLE IF NOT EXISTS `kalenderdatabase`.`MOTEROM` (
  `moteromID` INT NOT NULL AUTO_INCREMENT,
  `sted` VARCHAR(45) NULL,
  `storrelse` INT NOT NULL,
  `navn` VARCHAR(45) NULL,
  PRIMARY KEY (`moteromID`));



CREATE TABLE IF NOT EXISTS `kalenderdatabase`.`AVTALE` (
  `avtaleID` INT NOT NULL AUTO_INCREMENT,
  `start` DOUBLE NOT NULL,
  `slutt` DOUBLE NOT NULL,
  `navn` VARCHAR(45) NOT NULL,
  `beskrivelse` VARCHAR(255) NULL,
  `sted` VARCHAR(45) NULL,
  `moteromID` INT NULL,
  PRIMARY KEY (`avtaleID`),
  FOREIGN KEY (`moteromID`)
  REFERENCES `kalenderdatabase`.`MOTEROM` (`moteromID`)
  ON DELETE SET NULL
  ON UPDATE CASCADE);



CREATE TABLE IF NOT EXISTS `kalenderdatabase`.`GRUPPE` (
  `gruppeID` INT NOT NULL AUTO_INCREMENT,
  `supergruppeID` INT NULL,
  `navn` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`gruppeID`),
  FOREIGN KEY (`supergruppeID`)
  REFERENCES `kalenderdatabase`.`GRUPPE` (`gruppeID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE);



CREATE TABLE IF NOT EXISTS `kalenderdatabase`.`GRUPPEBRUKER` (
  `brukerID` INT NOT NULL,
  `gruppeID` INT NOT NULL,
  `admin` TINYINT(1) NULL,
  PRIMARY KEY (`gruppeID`, `brukerID`),
  FOREIGN KEY (`brukerID`)
  REFERENCES `kalenderdatabase`.`BRUKER` (`brukerID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (`gruppeID`)
  REFERENCES `kalenderdatabase`.`GRUPPE` (`gruppeID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE);



CREATE TABLE IF NOT EXISTS `kalenderdatabase`.`AVTALEBRUKER` (
  `deltar` TINYINT(1) NULL,
  `brukerID` INT NOT NULL,
  `avtaleID` INT NOT NULL,
  `admin` TINYINT(1) NULL,
  PRIMARY KEY (`brukerID`, `avtaleID`),
  FOREIGN KEY (`brukerID`)
  REFERENCES `kalenderdatabase`.`BRUKER` (`brukerID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (`avtaleID`)
  REFERENCES `kalenderdatabase`.`AVTALE` (`avtaleID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE);

