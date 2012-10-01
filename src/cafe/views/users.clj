(ns cafe.views.users
  (:use [noir.core]
        [cafe.views.common])
  (:require [noir.response :as resp]
            [noir.request :as req]
            [noir.session :as session]
            [net.cgrand.enlive-html :as html]
            [cafe.data.user :as users]))

(defn register-signin [user]
  (session/put! :user user))

;; Temporary test page
(defpage "/shop" {}
  (layout-one-col
    (html/html-resource (script-path "users" "shop"))
    (session/flash-get)))

(defpage [:get "/register"] {}
  (layout-one-col (html/html-resource (script-path "users" "new"))))

(defpage [:get "/users/sign-in"] {}
  (layout-one-col
    (html/html-resource (script-path "users" "sign-in"))
    (session/flash-get)))

(defpage [:post "/users/validate"] {:keys [email password]}
  (if (users/authenticate email password "127.0.0.1")
    (do
      (register-signin (users/find-by-email email))
      (println (req/ring-request))
      (resp/redirect (url-for account)))
    (do
      (session/flash-put! {:error "Correo electrónico o contraseña incorrectos"})
      (session/put! :error "Correo electrónico o contraseña incorrectos")
      (resp/redirect "/users/sign-in"))))

(defpage [:post "/users"] {:keys [user]}
  (if (users/create user)
    (do
      (session/flash-put! {:success (format "¡Bienvenido %s! Tu cuenta ha sido creada" (:name user))})
      (register-signin user)
      (resp/redirect "/shop"))
    (do
      (session/flash-put! {:error "El correo que utilizaste ya esta registrado"})
      (resp/redirect "/users/register"))))

(defpage account "/account" {}
  (layout-one-col
    (html/html-resource (script-path "users" "account"))
    (session/flash-get)))
