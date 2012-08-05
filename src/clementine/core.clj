(ns clementine.core
  (:use [noir.core])
  (:require [noir.server :as server]
	    [ring.middleware.reload :as rl]))

(server/load-views "src/clementine/views")

(server/add-middleware rl/wrap-reload)

(defn -main [& m]
  (let [mode (or (first m) :dev)
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode (keyword mode)
                        :ns 'clementine})))