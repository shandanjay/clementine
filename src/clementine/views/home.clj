(ns clementine.views.home
  (:use [noir.core])
  (:require [net.cgrand.enlive-html :as html]))

(def store {:title "Clementine"
	    :meta-author "Juan Antonio Arguello, Valentina Ayala, Eduardo Raad y Cristina Villacres"
	    :meta-description "Personaliza lo que quieras"
	    :welcome-message "Hola Clementine"
	    :sections [{:name "Baby Shower" :uri "/baby-shower" :order 1}
		       {:name "Aniversarios" :uri "/aniversarios" :order 2}
		       {:name "Bautizo" :uri "/bautizo" :order 3}
		       {:name "Cumpleanos" :uri "cumpleanos" :order 4}
		       {:name "Despedida de Soltera" :uri "despedida-de-soltera" :order 5}]})
	    
(html/deftemplate layout "clementine/views/layout.html" [store]
  [:title]
  (html/content (:title store))
  [:meta (html/attr= (:name "author"))]
  (html/set-attr :content (:meta-author store))
  [:meta (html/attr= (:name "description"))]
  (html/set-attr :content (:meta-description store))
  [:div.sections-menu :ul :li]
  (html/clone-for [i (range 4)]
		  [:li :a]
		  (html/do->
		   (html/content (:name ((:sections store) i)))
		   (html/set-attr "href" (:uri ((:sections store) i)))))
  [:div.content]
  (html/content (:welcome-message store)))

(defpage "/" []
  (layout store))