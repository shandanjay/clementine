(ns cafe.fixtures
  (:use [clj-yaml.core])
  (:require [cafe.data.core :as data]
            [cafe.data.category :as category]
            [cafe.data.product :as product]))

(defn save-fixtures []
  (product/add-products (:products (parse-string (slurp "./resources/fixtures.yml"))))
  (category/add-categories (:categories (parse-string (slurp "./resources/fixtures.yml")))))

(defn -main []
  (data/init)
  (save-fixtures)
  (println "Fixtures saved."))
