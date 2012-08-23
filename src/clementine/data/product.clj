(ns clementine.data.product
  (:use [korma.db]
        [korma.core]))

(defentity products)

(defn add [product]
  (insert products
          (values {:name (:name product)
                   :sku (:sku product)
                   :description (:description product)
                   :slug (:slug product)
                   :price (:price product)})))

(defn find-all []
  (select products))

(defn find-by-slug [slug]
  (select products
    (where {:slug slug})))

(defn delete! [id]
  (delete products
          (where {:id id})))
