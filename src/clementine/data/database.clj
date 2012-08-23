(ns clementine.core
  (:use [korma.db]))

(if (= "dev" (System/getenv "DATABASE_URL"))
  (def postgres-conf (postgres {:db "clementine"
                                :user "eraad"
                                :password "pico2050"}))
  (def postgres-conf (postgres {:host "host=ec2-23-21-204-85.compute-1.amazonaws.com"
                                :port "5432"
                                :ssl "true"
                                :db "resource44215"
                                :user "zkaggjzjasmqzf"
                                :password "JfU9WXRLJZ1-duCGWdKZp3uOWE"})))

(defdb cafedb postgres-conf)
