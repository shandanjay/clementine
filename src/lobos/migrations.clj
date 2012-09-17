(ns lobos.migrations
  (:refer-clojure :exclude [alter drop bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema) lobos.config))

(defmigration properties
  (up []
      (create
       (table :properties
              (integer :id :primary-key :auto-inc)
              (varchar :name 100 :not-null)
              (varchar :description 100 :not-null)
              (varchar :value 100 :not-null))))
  (down []
        (drop
         (table :properties))))

(defmigration categories
  (up [] (create (table :categories
                        (integer :id :primary-key :auto-inc)
                        (varchar :slug 100 :not-null)
                        (varchar :name 100 :not-null)
                        (varchar :description 255 :not-null))))
  (down [] (drop (table :categories))))

(defmigration products
  (up [] (create (table :products
                        (integer :id :primary-key :auto-inc)
                        (varchar :sku 100)
                        (varchar :slug 100 :not-null)
                        (varchar :name 100 :not-null)
                        (varchar :description 255 :not-null)
                        (decimal :price))))
  (down [] (drop (table :products))))

;;(defmigration add-products-table
  ;; code be executed when migrating the schema "up" using "migrate"
;; (up [] (create
;; (table :products
;;       (integer :id :primary-key :auto-inc)
;;       (varchar :name 100 :not-null)
;;       (varchar :description 255 :not-null)
;;       (varchar :price 255)
;;)))

;; Code to be executed when migrating schema "down" using "rollback"
;;(down [] (drop (table :products ))))
