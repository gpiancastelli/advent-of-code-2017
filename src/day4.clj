(ns day4
  (:require [clojure.string :as str]))

(defn has-no-duplicate [chunked-phrase]
  (= (count (set chunked-phrase))
     (count chunked-phrase)))

(defn has-no-anagram [chunked-phrase]
  (= (count (set (map #(sort (str/split % #"")) chunked-phrase)))
     (count chunked-phrase)))

(defn count-valid [phrases policy]
  (let [chunked-phrases (map #(str/split % #"\s") phrases)]
    (reduce + 0 (map #(if (policy %) 1 0) chunked-phrases))))

(defn -main []
  (let [phrases (str/split (slurp "input/4.txt") #"\n")]
    (println (count-valid phrases has-no-duplicate))
    (println (count-valid phrases has-no-anagram))))