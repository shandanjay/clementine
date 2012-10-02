(ns cafe.views.common
  (:use [noir.util.test])
  (:require [net.cgrand.enlive-html :as html]
            [noir.session :as session]))

(defn script-path [view script]
  (str "cafe/views/scripts/" view "/" script ".html"))

;; @TODO: This function can be implemented as a recursive function in favor of a
;;        cleaner implementation
(defn insert-flash-message [html-nodes type messages]
  (if (get messages type)
    (do
      (html/at html-nodes
      [(keyword (str "div.alert-" (name type)))]
        (html/do->
          (html/content (type messages))
          (html/remove-class "hiden"))))
    html-nodes))

;; ## Default (one column) layout
;; Injects the body and the title of the page using the provided parameters
(html/deftemplate layout-one-col "cafe/views/scripts/layout/default.html" [body & [messages]]
  [:div#body]
    (html/content
      (html/at body
        [:div#content-header]
        (html/content
          (-> (insert-flash-message
                (html/html-resource (script-path "layout" "_flash_messages")) :error messages)
              (insert-flash-message :warning messages)
              (insert-flash-message :success messages))))))

(def layout-template-url (new java.net.URL "http://localhost:8080/templates/layout.html"))

(html/deftemplate store-layout layout-template-url [store]
  ;; Meta data
  [:title]
  (html/content (:title store))
  [:meta (html/attr= (:name "author"))]
  (html/set-attr :content (:meta-author store))
  [:meta (html/attr= (:name "description"))]
  (html/set-attr :content (:meta-description store))

  ;; Categories
  [:.cafe-category]
  (html/clone-for [category (map vector (iterate inc 0) (:categories store))]
                  [:.cafe-category :a]
                  (html/do->
                   (html/content (category :name))
                   (html/set-attr "href" (category :uri))))

  ;; Products
  [:.cafe-product]
  (html/clone-for [product (:products store)]
                  [:.cafe-product :a]
                  (html/do->
                   (html/content (:name product))
                   (html/set-attr "href" (:uri product))))
  [:.content]
  (html/content "Prueba"))
