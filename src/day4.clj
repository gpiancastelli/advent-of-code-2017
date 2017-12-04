(ns day4
  (:require [clojure.string :as str]))

(defn no-duplicate-policy [chunked-phrase]
  (if (= (count (set chunked-phrase))
         (count chunked-phrase))
    1
    0))

(defn no-anagram-policy [chunked-phrase]
  (if (= (count (set (map #(sort (str/split % #"")) chunked-phrase)))
         (count chunked-phrase))
    1
    0))

(defn count-valid [phrases policy]
  (let [chunked-phrases (map #(str/split % #"\s") phrases)]
    (reduce + 0 (map policy chunked-phrases))))

(defn -main []
  (let [phrases (str/split (slurp "input/4.txt") #"\n")]
    (println (count-valid phrases no-duplicate-policy))
    (println (count-valid phrases no-anagram-policy))))