-- :name hae-kaikki-elaimet :? :*
SELECT id, nimi, "latinankielinen-nimi"
FROM elukkaisto.elain;

-- :name lisaa-uusi-elain :<!
INSERT INTO elukkaisto.elain(nimi, "latinankielinen-nimi")
VALUES (:nimi, :latinankielinen-nimi)
RETURNING id;

-- :name paivita-elain :! :n
UPDATE elukkaisto.elain
SET nimi = :nimi,
    "latinankielinen-nimi" = :latinankielinen-nimi
WHERE id = :id;
