-- CREATE DATABASE examen;
-- USE examen;

-- CREATE TABLE user (
--     id INT PRIMARY KEY AUTO_INCREMENT,
--     nom VARCHAR(50) NOT NULL,
--     password VARCHAR(50) NOT NULL
-- );

-- CREATE TABLE crédit (
--     idCredit INT PRIMARY KEY AUTO AUTO_INCREMENT,
--     libélé VARCHAR(50) NOT NULL,
--     montant INT
-- );

-- CREATE TABLE dépense (
--     idDépense INT PRIMARY KEY AUTO AUTO_INCREMENT,
--     libélé VARCHAR(50) NOT NULL,
--     idCredit INT 
-- );

CREATE DATABASE examen;
USE examen;

CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE credit (
    id INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(50) NOT NULL,
    montant DECIMAL(10, 2) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE depense (
    id INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(50) NOT NULL,
    montant DECIMAL(10, 2) NOT NULL,
    credit_id INT,
    FOREIGN KEY (credit_id) REFERENCES credit(id)
);