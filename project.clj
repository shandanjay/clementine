(defproject cafe "0.0.1-SNAPSHOT"
  :description "E-commerce platform powered by Clojure"
  :url "http://cafecommerce.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/tools.nrepl "0.2.0-beta9"]
                 [noir "1.3.0-beta1"]
                 [enlive "1.0.1"]
                 [korma "0.3.0-beta7"]
                 [postgresql "9.0-801.jdbc4"]
                 [lobos "1.0.0-SNAPSHOT"]
                 [clj-yaml "0.4.0"]]
  :plugins [[lein-marginalia "0.7.1"]]
  :main cafe.core)
