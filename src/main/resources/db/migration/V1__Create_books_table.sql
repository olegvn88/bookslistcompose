create table books if not exist
(
    id     BIGINT      NOT NULL AUTO_INCREMENT,
    name   VARCHAR(20) NOT NULL UNIQUE,
    author VARCHAR(50) NOT NULL,
    price  FLOAT       NOT NULL DEFAULT,
    CONSTRAINT PK_BOOK PRIMARY KEY (id)
);