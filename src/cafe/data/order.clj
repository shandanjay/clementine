(ns cafe.data.order
  (:use [korma.db]
        [korma.core]))

(defentity orders)

(defn add [order]
  (println "@TODO: Missing function implementation"))

(defn find-all []
  (select orders))

(defn delete! [id]
  (delete orders
          (where {:id id})))
