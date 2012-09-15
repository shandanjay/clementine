(ns clementine.views.admin
  (:use [noir.core])
  (:require	[net.cgrand.enlive-html :as enlive]
  					[noir.response :as resp]
            [clementine.data.product :as product]))

(def admin-layout-template-url (new java.net.URL "http://localhost:8080/templates/layout/admin.html"))

(enlive/deftemplate admin-layout admin-layout-template-url [body]
	;[:title] (html/content (:title store))
  ;[:meta (html/attr= (:name "author"))] (html/set-attr :content (:meta-author store))
  ;[:meta (html/attr= (:name "description"))] (html/set-attr :content (:meta-description store))
  [:div#body] (enlive/content body))

(enlive/defsnippet products-list "clementine/views/admin/products/list.html" [:#product-list] []
  [:table :tbody :tr]
    (enlive/clone-for [product (product/find-all)]
      [:tr :td.product-name] (enlive/content (:name product))
      [:tr :td.product-description] (enlive/content (:description product))
      [:tr :td.product-price] (enlive/content (format "$%.2f" (:price product)))
      [:tr :td.product-action :a.edit] (enlive/set-attr :href (str "/admin/products/" (:id product) "/edit") )))

;; loads a Product into a HTML form
(def product-edit 
  (enlive/snippet* (enlive/html-resource "clementine/views/admin/products/edit.html") [product]
    [:input#name] (enlive/set-attr :value (:name product))
    [:form :input#slug] (enlive/set-attr :value (:slug product))
    [:form :input#sku] (enlive/set-attr :value (:sku product))
    [:form :textarea] (enlive/content (:description product))))

(pre-route "/admin*" {})

(def admin-prefix "admin")

(defpage "/admin/" {:keys [name]}
	(admin-layout "Actividad reciente"))

(defpage [:get "/admin/products/new"] {}
	(admin-layout (enlive/html-resource "clementine/views/admin/products/new.html")))

(defpage [:get "/admin/products/:id/edit"] {:keys [id]}
  (admin-layout (product-edit (product/find-by-id id))))
  ; (let [product (product/find-all)]
  ;   (admin-layout (enlive/snippet* (enlive/html-resource "clementine/views/admin/products/edit.html") [product]
  ;     [:input#name] (enlive/set-attr :value (:name product))
  ;     [:form :input#slug] (enlive/set-attr :value (:slug product))
  ;     [:form :input#sku] (enlive/set-attr :value (:sku product))
  ;     [:form :textarea] (enlive/content (:description product)))) ))

(defpage [:get "/admin/products"] {}
  (admin-layout (products-list)))

(defpage [:post "/admin/products/"] {:keys [product]}
	(product/add product)
	(resp/redirect "/admin/products"))