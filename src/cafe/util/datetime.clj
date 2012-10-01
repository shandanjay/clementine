(ns cafe.util.datetime
  (:import java.sql.Timestamp))

(defn now-sql []
  (new Timestamp (System/currentTimeMillis)))