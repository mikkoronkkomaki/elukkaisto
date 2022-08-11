(ns elukkaisto.kyselyt.elaimet
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "elukkaisto/kyselyt/elaimet.sql")
(hugsql/def-sqlvec-fns "elukkaisto/kyselyt/elaimet.sql")

(comment
  (elukkaisto.tietokanta/kaynnista-datasource)
  (hae-kaikki-elaimet @elukkaisto.tietokanta/db)
  (lisaa-uusi-elain @elukkaisto.tietokanta/db {:nimi "Vompatti" :latinankielinen-nimi "Vombatidae"})
  (paivita-elain @elukkaisto.tietokanta/db {:id 1
                                            :nimi "ADSF"
                                            :latinankielinen-nimi "asdfd"}))
