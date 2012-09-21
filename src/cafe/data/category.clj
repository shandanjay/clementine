(ns cafe.data.category
  (:use [korma.db]
        [korma.core]))

(defentity categories)

(defn add [name slug description]
  (insert categories
    (values {:name name :slug slug :description description})))

(defn find-all []
  (select categories))

(defn find [slug]
  (select categories
    (where {:slug slug})))

(defn delete! [id]
  (delete categories
          (where {:id id})))
