(ns day1
  (:require [clojure.string :as str]))

(defn sum-next [seq]
  (let [start [0, 0]
        res (reduce
          (fn [res, prev]
            (let [partial (get res 0)]
              [(if (== (get res 1) prev) (+ partial prev) partial), prev]))
          start
          (concat seq [(first seq)]))]
    (get res 0)))

(defn sum-halfway [seq]
  (reduce
    (fn [res, couple]
      (let [first (get couple 0)
            second (get couple 1)]
        (if (== first second) (+ res first second) res)))
    0
    (let [whole (vec seq)
          half (/ (count whole) 2)
          first-half (subvec whole 0 half)
          second-half (subvec whole half)]
      (map vector first-half second-half))))

(defn -main []
  (let [text (str/trim (slurp "input/1.txt"))
        seq (map #(Integer/parseInt %) (str/split text #""))]
   (println (sum-next seq))
   (println (sum-halfway seq))))
