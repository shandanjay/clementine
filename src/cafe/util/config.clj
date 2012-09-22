(ns cafe.util.config
        (:require [clojure.string :as str])
  (:import (java.net URI)))

(defn parse-db-url
  "Generate a map with the required data to open a db connection"
  [env-variable-name]
  (let [url (URI. (System/getenv env-variable-name))
        host (.getHost url)
        port (if (pos? (.getPort url)) (.getPort url) 5432)
        path (.getPath url)]
    (merge
     {:subname (str "//" host ":" port path)}
     (when-let [user-info (.getUserInfo url)]
       {:user (first (str/split user-info #":"))
        :password (second (str/split user-info #":"))}))))

;; @TODO: Should validate existence of DATABASE_URL environment variable
(defn get-db-config "Builds a JDBC driver config map" []
        (merge {:classname "org.postgresql.Driver"
          :subprotocol "postgresql"}
         (parse-db-url "DATABASE_URL")))
