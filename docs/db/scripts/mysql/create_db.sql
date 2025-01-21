DROP DATABASE IF EXISTS faredb;
DROP USER IF EXISTS faredbusr@localhost;

CREATE DATABASE faredb CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON faredb.* TO 'faredbusr'@'localhost' IDENTIFIED BY 'himitu';

