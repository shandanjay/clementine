(ns cafe.data.line-item
  (:use [korma.db]
        [korma.core]))

(defentity line-item
  (table :line_items)
	(belongs-to orders))

(defn add [item order-id]
  (insert line-item
          (values (merge item {:order_id order-id}))))