(ns clementine.store
  (:require [clementine.data.category :as category]))

(defn build-store []
  (let [categories (category/find-all)]
    (str categories)))
