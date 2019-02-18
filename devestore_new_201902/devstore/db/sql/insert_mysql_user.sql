DROP DATABASE IF EXISTS ninsyo_account;
CREATE DATABASE ninsyo_account;
DROP USER 'openAM_user'@'localhost';
CREATE USER 'openAM_user'@'localhost' IDENTIFIED BY 'R-8wAknZr|8D';
GRANT ALL PRIVILEGES ON ninsyo_account.* TO 'openAM_user'@'localhost';
