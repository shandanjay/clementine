;; ## Orders
;;
;; Orders business logic. An order groups all the information related to a
;; purchase in the store. Products (line items), billing address, payments,
;; shipping address, shipments.
;;
(ns cafe.data.order
  (:use [korma.db]
        [korma.core])
  (:require [cafe.data.user :as users]
            [cafe.data.address :as address]
            [cafe.data.status :as status]))

(declare prepare-input)

(defentity line_items)
(defentity payments)
(defentity shipments)

;; ### Orders table fields:
;; * id *integer*
;; * customer_id *integer*
;; * billing_address_id _integer_
;; * shipping_address_id _integer_
;; * date_purchased _datetime_
;; * last_modified _datetime_
;; * total _decimal_
;; * status_id _integer_
;; * special_instructions _varchar_
;; * shipping_method_id _integer_

(defentity orders
  (table :orders)
  (has-many line_items {:fk :order_id})
  (has-many payments {:fk :order_id})
  (has-many shipments {:fk :order_id})
  (belongs-to status/status)
  (belongs-to users/users {:fk :user_id}))

;; ## Data store functions

(defn insert-items [order-id items]
  (insert line_items
    (values (map #(assoc % :order_id order-id) items))))

(defn create [new-order]
  (->
    (:id (insert orders
      (values (prepare-input new-order))))
    (insert-items (:line-items new-order))))

(defn update-total [order]
  (update orders
          (set-fields { :total (:total order)
                        :updated_at "NOW()"})
          (where {:id (:id order)})))

(defn update-status [order status-id]
  (update orders
          (set-fields {:status_id status-id})
          (where {:id (:id order)})))

(defn find-all []
  (select orders))

(defn find-order [order-id]
  (select orders
    (with line_items)
    (where {:id [= order-id]})))

(defn delete! [id]
  (delete orders
          (where {:id id})))

;; ## Private functions.

(defn- prepare-input
  "Removes related entities and prepares the order map to be inserted into the database"
  [order]
  (dissoc order :line-items))
