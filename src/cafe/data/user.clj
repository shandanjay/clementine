(ns cafe.data.user
  (:use [korma.db]
        [korma.core])
  (:require [noir.util.crypt :as crypt]
            [cafe.util.datetime :as date]))

(declare generate-salt log-signin log-signin-attempt log-signout)

(defentity users)
(defentity roles)
(defentity roles_users)

(defn create [new-user]
  (let [pass (:password new-user)
        salt (str "$2$12$" (generate-salt 32))]
    (insert users
      (values (merge new-user { :password_salt salt
                                :password (crypt/encrypt salt pass)})))))

(defn find-by-id [id]
  (select users
    (where {:id id})))

(defn update-attribute [user attribute new-value]
  (update users
    (set-fields {attribute new-value})
    (where {:id [= (:id user)]})))

(defn update-attributes [user attributes]
  (update users
    (set-fields attributes)
    (where {:id [= (:id user)]})))

(defn find-by-email [email]
  (let [user-list
          (select users
            (where {:email email}))]
    (if (> (count user-list) 0)
      (first user-list))))

(defn authenticate [email password ip-address]
  (if-let [user (find-by-email email)]
    (let [salt (:password_salt user)
          stored-pass (:password user)]
      (if (= stored-pass (crypt/encrypt salt password))
        (do
          (log-signin user ip-address)
          true)
        (do
          (log-signin-attempt user ip-address)
          false)))
    false))

(defn generate-salt [n] 
  (let [charseq (map char (concat
         (range 48 58)     ; 0-9
         (range 97 123)))] ; 0-z
    (apply str
      (take n
        (repeatedly #(rand-nth charseq))))))

(defn log-signin [user ip-address]
  (let [now (date/now-sql)]
    (update-attributes user { :current_sign_in_ip ip-address
                              :last_sign_in_ip ip-address
                              :sign_in_count (inc (:sign_in_count user))
                              :failed_attempts 0
                              :current_sign_in_at now
                              :last_sign_in_at now
                              :last_request_at now})))

(defn log-signin-attempt [user ip-address]
  (update-attributes user { :failed_attempts (inc (:failed_attempts user))
                            :last_request_at (date/now-sql)}))

(defn log-signout [user]
  (update-attributes user { :current_sign_in_ip nil
                            :current_sign_in_at nil}))

(defn add-roles [new-roles]
  (insert roles
    (values new-roles)))

; (varchar :name 80)
; (varchar :lastname 80)
; (varchar :email 200)
; (varchar :password 128)
; (varchar :password_salt 128)
; (nchar :authentication_token)
; (varchar :current_sign_in_ip 39)
; (varchar :last_sign_in_ip 39)
; (varchar :password_salt 128)
; (integer :sign_in_count :not-null (default 0))
; (integer :failed_attempts :not-null (default 0))
; (timestamp :current_sign_in_at)
; (timestamp :last_sign_in_at)
; (timestamp :last_request_at)
; (timestamp :reset_password_sent_at (default (now)))
; (timestamp :created_at (default (now)))
; (timestamp :updated_at (default (now))))))