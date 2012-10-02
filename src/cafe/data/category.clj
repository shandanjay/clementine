(ns cafe.data.category
  (:use [korma.db]
        [korma.core]))

(defentity categories)

(defn add-category [category]
  (insert categories
          (values {:name (:name category)
                   :description (:description category)
                   :uri (:uri category)
                   :parent_id (:parent_id category)})))

(defn add-categories [category-list]
  (insert categories
          (values category-list)))

(defn find-categories []
  (select categories
          (limit 10)))

(defn get-category [id]
  (select categories
          (where {:id id})))

(defn get-children-categories [id]
  (select categories
           (where {:parent_id id})))

(defn delete-category [id]
  (delete categories
          (where {:id id})))
