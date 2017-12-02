(ns day1
  (:require [clojure.string :as str]))

(defn sum-next [seq]
  (let [res (reduce
          (fn [res, prev]
            (let [partial (get res 0)]
              [(if (== (get res 1) prev) (+ partial prev) partial), prev]))
          [0, 0]
          (conj seq (first seq)))]
    (get res 0)))

(defn sum-halfway [seq]
  (reduce
    (fn [res, couple]
      (let [first (get couple 0)
            second (get couple 1)]
        (if (== first second) (+ res first second) res)))
    0
    (let [half-count (/ (count seq) 2)
          first-half (subvec seq 0 half-count)
          second-half (subvec seq half-count)]
      (map vector first-half second-half))))

(defn -main []
  (let [text (str/trim (slurp "input/1.txt"))
        seq (vec (map #(Integer/parseInt %) (str/split text #"")))]
    (println (sum-next seq))
    (println (sum-halfway seq))))
