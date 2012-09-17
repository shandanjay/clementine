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

(defmigration add-customers-table
  (up [] (create (table :customers
                        (integer :id :primary-key :auto-inc)
                        (varchar :name 80)
                        (varchar :lastname 80)
                        (varchar :email 200)
                        (integer :role))))
  (down [] (drop (table :customers))))

(defmigration add-orders-table
  (up [] (create (table :orders
                        (integer :id :primary-key :auto-inc)
                        (varchar :number 50)
                        (integer :customer_id :not-null [:refer :customers :id])
                        (timestamp :date_purchased (default (now)))
                        (timestamp :last_modified :not-null)
                        (integer :payment_method))))
  (down [] (drop (table :orders))))

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

(defn -main []
  (println "Migrating database...") (flush)
  (migrate)
  (println "done!"))
