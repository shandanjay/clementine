(ns cafe.views.home
  (:use [noir.core]
        [cafe.views.common]
        [cafe.store])
  (:require [net.cgrand.enlive-html :as html]))

(defpage "/" []
  (store-layout build-store))
