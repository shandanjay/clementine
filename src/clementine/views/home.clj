(ns clementine.views.home
  (:use [noir.core])
  (:require [net.cgrand.enlive-html :as html]))

(def store {:title "Clementine - Personaliza lo que quieras"
            :meta-author "Juan Antonio Arguello, Valentina Ayala, Eduardo Raad y Cristina Villacres"
            :meta-description "Personaliza lo que quieras"
            :welcome-message "Hola Clementine"
            :categories [{:name "Baby Shower" :uri "/baby-shower" :order 1}
                         {:name "Aniversarios" :uri "/aniversarios" :order 2}
                         {:name "Bautizo" :uri "/bautizo" :order 3}
                         {:name "Cumpleanos" :uri "cumpleanos" :order 4}
                         {:name "Despedida de Soltera" :uri "despedida-de-soltera" :order 5}]
            :featured [{:name "Kits de Terror"
                        :description "Una seleccion de papeleria y accesorios para matar del miedo a tus invitados"
                        :image "/img/terror.png"}
                       {:name "Experiencias Picantes"
                        :description "Kits de supervivencia para despedidas de soltera."
                        :image "/img/picantes.png"}]
            :products [{}]})

(def layout-template-url (new java.net.URL "http://localhost:8080/templates/layout.html"))

(html/deftemplate layout layout-template-url [store]
  [:title]
  (html/content (:title store))
  [:meta (html/attr= (:name "author"))]
  (html/set-attr :content (:meta-author store))
  [:meta (html/attr= (:name "description"))]
  (html/set-attr :content (:meta-description store))
  [:.cafe-category]
  (html/clone-for [i (range 4)]
                  [:.cafe-category :a]
                  (html/do->
                   (html/content (:name ((:categories store) i)))
                   (html/set-attr "href" (:uri ((:categories store) i)))))
  [:div.featured1 #{:.name}]
  (html/content (:name ((:featured store) 0)))
  [:div.featured1 #{:.description}]
  (html/content (:description ((:featured store) 0)))
  [:div.featured1 :img]
  (html/set-attr :src (:image ((:featured store) 0)))
  [:div.featured2 #{:.name}]
  (html/content (:name ((:featured store) 1)))
  [:div.featured2 #{:.description}]
  (html/content (:description ((:featured store) 1)))
  [:div.featured2 :img]
  (html/set-attr :src (:image ((:featured store) 1)))
  [:div.content]
  (html/content (:welcome-message store)))

(defpage "/" []
  (layout store))
