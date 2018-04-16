(ns matryoshka.core
  (:require [matryoshka.parser :as parser])
  (:require [matryoshka.packer :as packer])
  (:gen-class))

(defn -main
  "Main entry point to packing program"
  [& args]
  (if (= (count args) 0)
    (println "You must specify a maximum weight")
    (let [weight (Integer/parseInt (get (into [] args) 0))]
      (let [values (parser/parse (slurp *in*))]
        (let [dolls (map (fn [val] (apply packer/new-doll val)) (rest values))]
          (packer/pack weight (into [] dolls))
        )
      )
  )))
