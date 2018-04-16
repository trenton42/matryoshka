(ns matryoshka.packer
  (:require [clojure.pprint])
  (:gen-class))

(defrecord doll [name weight value])

(defn to-int
  "Takes an input that could possibly be a number and casts it to an integer"
  [val]
  (if (number? val)
    val
    (Integer/parseInt val)))

(defn new-doll
  "Creates a new doll record from a name, weight, and value"
  ([name weight value]
   (doll. name (to-int weight) (to-int value))))

(defn buildtable
  "Creates an initial lookup table based on the number of items and the total max weight"
  [numitems maxweight]
  (into []
        (for [x (range (inc numitems))]
          (into []
                (for [y (range (inc maxweight))]
                  0)))))

(defn update-bag
  "Update the bag for the current item and weight"
  [bag items id w]
  (if (or (= id 0) (= w 0))
        ; If the bag cannot hold anything or we don't have an item, then the value is 0
    (assoc-in bag [id w] 0)
    (if (<= (:weight (get items (dec id))) w)
      (assoc-in bag [id w]
                ; Choose the optimal case: if the value is more with the current item or not
                (max
                 (+ (:value (get items (dec id))) (get-in bag [(dec id) (- w (:weight (get items (dec id))))]))
                 (get-in bag [(dec id) w])))
      (assoc-in bag [id w] (get-in bag [(dec id) w])))))

(defn get-bag
  [items bag i w res]
  (if (or (<= i 0) (<= w 0))
    res
    (if (not= (get-in bag [i w]) (get-in bag [(dec i) w]))
      (get-bag items bag (dec i) (- w (:weight (get items (dec i)))) (conj res (get items (dec i))))
      (get-bag items bag (dec i) w res))))

(defn get-max-values
    "Update the lookup table for each combination of item and weight. Has side effects"
  [total items]
  (let [bag (atom (buildtable (count items) total))]
    (doseq [i (range (inc (count items)))
            w (range (inc total))]
      (swap! bag (fn [current] (update-bag current items i w))))
    @bag))

(defn optimal-pack
    "The optimal packing algorithm"
  [total items]
  (let [bag (get-max-values total items)]
    (get-bag items bag (count items) total [])))

(defn pack
    "Packs items and prints the resulting table"
  [total items]
  (println (str "Max weight: " total))
  (let [finished (optimal-pack total items)]
    (println "We can pack the following dolls:")
    (clojure.pprint/print-table [:name :weight :value] finished)))

