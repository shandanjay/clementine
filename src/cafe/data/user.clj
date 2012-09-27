(ns cafe.data.user
  (:use [korma.db]
        [korma.core]))

(defentity users)

(defn create [new-customer]
	(insert users
		(values new-customer)))

(defn update-field [user field new-value]
	(update users
		(set-fields {field new-value})
		(where {:id [= (:id user)]})))