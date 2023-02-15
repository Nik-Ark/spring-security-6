CREATE USER IF NOT EXISTS 'custom_auth_user'@'localhost' IDENTIFIED BY 'custom_auth_pasS1415:00$';

CREATE DATABASE IF NOT EXISTS custom_auth_db;

GRANT ALL PRIVILEGES ON custom_auth_db.* TO 'custom_auth_user'@'localhost';