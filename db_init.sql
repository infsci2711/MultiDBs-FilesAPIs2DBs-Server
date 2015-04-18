CREATE DATABASE  IF NOT EXISTS `infsci2711_filesapi2dbs`;


CREATE USER 'dataverse'@'localhost' IDENTIFIED BY 'dataverse';
grant all privileges on *.* to 'dataverse'@'localhost';

CREATE USER 'dataverse'@'%' IDENTIFIED BY 'dataverse';
grant all privileges on *.* to 'dataverse'@'%';

FLUSH PRIVILEGES;