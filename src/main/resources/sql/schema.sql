CREATE TABLE IF NOT EXISTS users
(
    id        INTEGER     NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname  VARCHAR(50) NOT NULL,
    email     VARCHAR(50) NULL,
    age       INTEGER     NOT NULL,
    gender    ENUM ('Masculino', 'Feminino'),

    PRIMARY KEY (id)
);
