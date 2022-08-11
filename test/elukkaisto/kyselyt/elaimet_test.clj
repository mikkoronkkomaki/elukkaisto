(ns elukkaisto.kyselyt.elaimet_test
  (:require [clojure.test :refer :all]
            [elukkaisto.fikstuuri :as fikstuuri]
            [elukkaisto.kyselyt.elaimet :as elaimet]))

(use-fixtures :each fikstuuri/tietokanta-fikstuuri)

(deftest hae-kaikki-elaimet
  (def kaikki-elamiet-haussa (elaimet/hae-kaikki-elaimet @fikstuuri/db)))

(deftest lisaa-elain
  (def ennen-lisaysta (elaimet/hae-kaikki-elaimet @fikstuuri/db))
  (elaimet/lisaa-uusi-elain @fikstuuri/db {:nimi "Vompatti" :latinankielinen-nimi "Vombatidae"})
  (def lisayksen-jalkeen (elaimet/hae-kaikki-elaimet @fikstuuri/db)))

(deftest paivita-elain
  (def ennen-paivitysta (elaimet/hae-kaikki-elaimet @fikstuuri/db))
  (let [paivitettava-id (:id (first (filter #(= "Kettu" (:nimi %)) (elaimet/hae-kaikki-elaimet @fikstuuri/db))))]
    (elaimet/paivita-elain @fikstuuri/db {:id paivitettava-id
                                           :nimi "Viiksisiippa"
                                           :latinankielinen-nimi "Myotis mystacinus"}))
  (def paivityksen-jalkeen (elaimet/hae-kaikki-elaimet @fikstuuri/db)))
