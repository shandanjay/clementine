(ns clementine.core
  (:use [noir.core]
        [clojure.tools.nrepl.server :only (start-server stop-server)])
  (:require [noir.server :as server]
            [clementine.data.core :as data]))

(defonce repl-server (start-server :port 7888))
(data/init)

(defn -main [& m]
  (let [mode (or (first m) :dev)
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode (keyword mode)
                        :ns 'clementine})
    (server/load-views "src/clementine/views")))
