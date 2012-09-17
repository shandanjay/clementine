(ns clementine.data.core
  (:require [clementine.util.config :as util])
  (:use [korma.db]))

(defn init []
  (defdb cafedb (util/get-db-config)))