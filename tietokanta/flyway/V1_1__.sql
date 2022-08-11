CREATE SCHEMA IF NOT EXISTS elukkaisto;

CREATE TABLE elukkaisto.elain
(
    id                      SERIAL  NOT NULL
        CONSTRAINT pk_elain
            PRIMARY KEY,
    nimi                    VARCHAR NOT NULL,
    "latinankielinen-nimi" VARCHAR
);
