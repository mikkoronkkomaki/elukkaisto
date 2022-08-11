(defproject elukkaisto "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[com.layerware/hugsql "0.5.1"]
                 [hikari-cp/hikari-cp "2.13.0"]
                 [com.taoensso/timbre "5.1.2"]
                 [org.clojure/clojure "1.10.0"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.postgresql/postgresql "42.3.1"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler elukkaisto.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
