(ns elukkaisto.kyselyt.elaimet_test
  (:require [clojure.test :refer :all]
            [elukkaisto.fikstuuri :as fikstuuri]
            [elukkaisto.kyselyt.elaimet :as elaimet]))

(use-fixtures :each fikstuuri/tietokanta-fikstuuri)

(deftest hae-kaikki-elaimet
  (println "--->>> kaikki eläimet" (elaimet/hae-kaikki-elaimet @fikstuuri/db)))

(deftest lisaa-elain
  (println "--->>> kaikki eläimet" (elaimet/hae-kaikki-elaimet @fikstuuri/db))
  (elaimet/lisaa-uusi-elain @fikstuuri/db {:nimi "Vompatti" :latinankielinen-nimi "Vombatidae"})
  (println "--->>> kaikki eläimet" (elaimet/hae-kaikki-elaimet @fikstuuri/db)))

(deftest paivita-elain
  (println "--->>> kaikki eläimet" (elaimet/hae-kaikki-elaimet @fikstuuri/db))
  (let [paivitettava-id (:id (first (filter #(= "Kettu" (:nimi %)) (elaimet/hae-kaikki-elaimet @fikstuuri/db))))]
    (elaimet/paivita-elain @fikstuuri/db {:id paivitettava-id
                                           :nimi "Viiksisiippa"
                                           :latinankielinen-nimi "Myotis mystacinus"}))
  (println "--->>> kaikki eläimet" (elaimet/hae-kaikki-elaimet @fikstuuri/db)))
