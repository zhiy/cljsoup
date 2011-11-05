(ns cljsoup.core
  (:use [clojure.java.io])
  (:import [org.jsoup Jsoup]))

;; get document from url
(defn get-doc-by-url [url]
  (.get (Jsoup/connect url)))
;; get document from sting
(defn get-doc-from-string [str]
  (Jsoup/parse str))
;; get title of page
(defn get-title [doc]
  (.title doc))
;; get links with href
(defn get-links [doc]
  (.select doc "a[href]"))
;; get images with src
(defn get-images [doc]
  (.select doc "img[src]"))
;; get all elements
(defn get-all [doc]
  (.select doc "*"))
;; get styles
(defn get-styles [doc]
  (.select doc "style"))
;; get js
(defn get-js [doc]
  (.select doc "script"))
;; get internal js
(defn get-internal-js [doc]
  (.select doc "script:not([src])"))
;; get external js
(defn get-external-js [doc]
  (.select doc "script[src]"))
;; get external css
(defn get-external-css [doc]
  (.select doc "link[href]"))
;; get all sibiling elements
(defn get-sibling-elems [elem]
  (drop-last (.siblingElements elem)))
(defn get-url [elem]
  (if (.hasAttr elem "src")
    (.attr elem "abs:src")
    (.attr elem "abs:href")))
(defn get-domain [elem]
  (if (.hasAttr elem "src")
    (.getHost (as-url (.attr elem "abs:src")))
    (.getHost (as-url (.attr elem "abs:href")))))