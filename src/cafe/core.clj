(ns cafe.core
  (:use [noir.core]
        [clojure.tools.nrepl.server :only (start-server stop-server)])
  (:require [noir.server :as server]
            [cafe.data.core :as data]))

;; Create repl server
(defonce repl-server (start-server :port 7888))

;; Initialize database connection
(data/init)

(defn -main [& m]
  (let [mode (or (first m) :dev)
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode (keyword mode)
                        :ns 'cafe})
    (server/load-views "src/cafe/views")))
