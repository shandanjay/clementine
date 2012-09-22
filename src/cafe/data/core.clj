(ns cafe.data.core
  (:require [cafe.util.config :as util])
  (:use [korma.db]))

(defn init []
  (defdb cafedb (util/get-db-config)))
