CREATE DATABASE  IF NOT EXISTS `infsci2711_tutorial`
USE `infsci2711_tutorial`;


CREATE USER 'dataverse'@'localhost' IDENTIFIED BY 'dataverse';
grant all privileges on `infsci2711_tutorial`.* to 'dataverse'@'localhost';

CREATE USER 'dataverse'@'%' IDENTIFIED BY 'dataverse';
grant all privileges on `infsci2711_tutorial`.* to 'dataverse'@'%';

FLUSH PRIVILEGES;