(ns lobos.config
  (:use lobos.connectivity))

(defn open-global-when-necessary
  "Open a global connection only when necessary, that is, when no previous connection exist or when db-spec is different to the current global connection."
  [db-spec]
  ;; If connection credentials have changed, close the connection
  (when (and (@lobos.connectivity/global-connections :default-connection)
             (not= (:db-spec (@lobos.connectivity/global-connections :default-connections)) db-spec))
    (lobos.connectivity/close-global))
  ;; Open a new connection or return the existing one
  (if (nil? (@lobos.connectivity/global-connections :default-connection))
    ((lobos.connectivity/open-global db-spec) :default-connection)
    (@lobos.connectivity/global-connections :default-connection)))

(def db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :user "eraad"
   :password "pico2050"
   :subname "//localhost:5432/clementine"})

(open-global-when-necessary db)
