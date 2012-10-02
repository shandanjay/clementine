(ns cafe.data.product
  (:use [korma.db]
        [korma.core]
        [cafe.data.category]))

(defentity images)
(defentity products
  (has-many images))
(defentity products_categories)

(defn add-product [product]
  "Adds a product record. Sample product map:
    { :id 1
      :sku \"122ABC\"
      :name \"Marshmellow\"
      :description \"Delicious treat made from mellow deadly sugar.\"
      :uri \"/products/marshmellow\"
      :price \"1.00\"
      :}"
  (insert products
          (values {:sku (:sku product)
                   :name (:name product)
                   :description (:description product)
                   :uri (:uri product)
                   :price (:price product)})))

(defn add-products [product-list]
  (insert products
          (values product-list)))

(defn update-product [product-id fields]
  (update products
          (set-fields fields)
          (where {:id product-id})))

(defn add-image [product-id image]
  (insert images
          (values {:name (:name image)
                   :description (:description image)
                   :url (:url image)
                   :product_id product-id})))

(defn delete-image [id]
  (delete images
          (where {:id id})))

(defn get-images [product-id]
  (select images
          (where {:product_id product-id})))

(defn add-product-to-category [product-id category-id]
  (insert products_categories
          (values {:product_id product-id
                   :category-id category-id})))

(defn find-products []
  (select products
          (limit 100)))

(defn get-product [id]
  (select products
          (where {:id id})))

(defn delete-product [id]
  (delete products
          (where {:id id})))
