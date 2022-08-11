(ns elukkaisto.tietokanta
  (:require [hikari-cp.core :as hikari]))

(def konfiguraatio
  {:database-name      "elukkaisto"
   :username           "elukkaisto"
   :password           "elukkaisto"
   :server-name        "localhost"
   :port-number        5432
   :connection-timeout 3000
   :idle-timeout       600000
   :max-lifetime       1800000
   :minimum-idle       1
   :maximum-pool-size  20
   :adapter            "postgresql"})

(defonce db (atom nil))

(defn kaynnista-datasource []
  (reset! db {:datasource (hikari/make-datasource konfiguraatio)}))

(defn sammuta-datasource []
  (when @db
    (:datasource (hikari/close-datasource @db))))
