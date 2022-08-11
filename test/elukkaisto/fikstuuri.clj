(ns elukkaisto.fikstuuri
  (:require [clojure.test :refer :all]
            [elukkaisto.tietokanta :as tietokanta]
            [hikari-cp.core :as hikari]
            [taoensso.timbre :as log])
  (:import (org.postgresql.util PSQLException)))

(def testitietokannan-nimi "elukkaisto_test")
(def template-tietokannan-nimi "elukkaisto_template")

(def testitietokannan-konfiguraatio (assoc tietokanta/konfiguraatio :database-name testitietokannan-nimi))

(defonce db (atom nil))

(defn sammuta-datasource []
  (when @db
    (reset! db (hikari/close-datasource (:datasource @db)))))

(defn lopeta-yhteydet-tietokannasta [prepared-statement tietokanta]
  (.executeQuery prepared-statement
                 (format "SELECT pg_terminate_backend(pg_stat_activity.pid)
                          FROM pg_stat_activity
                          WHERE pg_stat_activity.datname = '%s' AND pid <> pg_backend_pid()"
                         tietokanta)))

(defn pudota-ja-luo-testitietokanta-templatesta []
  (let [datasource (hikari/make-datasource tietokanta/konfiguraatio)]
    (with-open [yhteys             (.getConnection datasource)
                prepared-statement (.createStatement yhteys)]
      (lopeta-yhteydet-tietokannasta prepared-statement testitietokannan-nimi)
      (lopeta-yhteydet-tietokannasta prepared-statement template-tietokannan-nimi)

      (dotimes [n 5]
        (try
          (.executeUpdate prepared-statement (format "DROP DATABASE IF EXISTS %s" testitietokannan-nimi))
          (catch PSQLException e
            (Thread/sleep 500)
            (log/warn e (format "- yritetään uudelleen pudottaa tietokanta: %s, yritys: %s" testitietokannan-nimi n)))))
      (.executeUpdate prepared-statement (format "CREATE DATABASE %s TEMPLATE %s" testitietokannan-nimi template-tietokannan-nimi))
      (hikari/close-datasource datasource))
    (reset! db {:datasource (hikari/make-datasource testitietokannan-konfiguraatio)})))

(defn alusta-fikstuuri []
  (sammuta-datasource)
  (pudota-ja-luo-testitietokanta-templatesta))

(defn sammuta-fikstuuri []
  (sammuta-datasource))

(defn tietokanta-fikstuuri [testit]
  (alusta-fikstuuri)
  (testit)
  (sammuta-fikstuuri))
