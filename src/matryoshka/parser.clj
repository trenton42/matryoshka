(ns matryoshka.parser
    (:require [clojure.string :as str])
    (:gen-class))

(defn parse
    "Parses a table from stdin to use as an input"
    [data]
    (map (fn [line] (str/split (str/trim line) #"\s+")) (str/split-lines data)
    ))