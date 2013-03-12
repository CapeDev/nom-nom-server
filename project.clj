(defproject nomnom-server "0.1.0-SNAPSHOT"
 :description "NomNom data service"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.5"]
                 [ring-middleware-format "0.2.4"]
                 [org.clojure/data.json "0.2.1"]]
  :plugins [[lein-ring "0.8.2"]]
  :ring {:handler nomnom-server.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
