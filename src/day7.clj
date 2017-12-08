(ns day7
  (:require [clojure.string :as str])
  (:require [clojure.set :as s]))

(defn find-root [parent-with-children-list]
  (let [[nodes children-nodes]
        (reduce
          (fn [[nodes children-nodes] [parent children]]
            [(conj nodes parent)
             (if (nil? children)
               children-nodes
               (apply conj (concat [children-nodes] (str/split children #",\s"))))])
          [(set '()) (set '())]
          parent-with-children-list)]
    (first (s/difference nodes children-nodes))))

(defn -main []
  (let [rows (str/split (slurp "input/7.txt") #"\n")
        regexp #"([a-z]+)\s\(\d+\)(\s->\s([a-z]+(,\s[a-z]+)*))?"]
    (println (find-root
               (map (fn [row]
                      (let [[_ parent _ children] (re-matches regexp row)]
                        [parent children]))
                    rows)))))
