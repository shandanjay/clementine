(ns clementine.store)

(def store {:title "Clementine - Personaliza lo que quieras"
            :meta-author "Juan Antonio Arguello, Valentina Ayala, Eduardo Raad y Cristina Villacres"
            :meta-description "Personaliza lo que quieras"
            :welcome-message "Hola Clementine"
            :sections [{:name "Baby Shower" :uri "/baby-shower" :order 1}
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

(defn get []
  '(store))
