(ns cafe.data.status
  (:use [korma.db]
        [korma.core]))

(defentity status)

(defn add [new-status]
  (insert status
    (values new-status)))