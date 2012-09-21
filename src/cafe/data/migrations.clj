(ns cafe.data.migrations
  (:use [korma.core]))

(defn add-categories-table []
  (exec-raw "CREATE TABLE categories (
      id serial PRIMARY KEY,
      slug varchar NOT NULL,
      name varchar NOT NULL,
      description text NOT NULL)"))

(defn delete-categories-table []
  (exec-raw "DROP TABLE categories"))

(defn add-products-table []
  (exec-raw "CREATE TABLE products (
       id serial PRIMARY KEY,
       name varchar NOT NULL,
       description text NOT NULL,
       date_added timestamp default NULL,
       images text[],
       categories text[])"))

(defn delete-products-table []
  (exec-raw "DROP TABLE products"))
