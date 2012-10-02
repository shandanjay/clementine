(ns cafe.store
  (:use [cafe.data.category]
        [cafe.data.product]))

(defn build-store []
  {:title "Clementine - Regalos personalizados"
   :meta-author "Cafe"
   :meta-description "Regalos personalizados"
   :categories (find-categories)
   :products (find-products)})
