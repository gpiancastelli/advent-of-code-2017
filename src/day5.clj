(ns day5
  (:require [clojure.string :as str]))

(defn find-exit
  [pos limit step-count maze offset-fn]
  (if (>= pos limit)
    step-count
    (recur
      (+ pos (get maze pos))
      limit
      (inc step-count)
      (update-in maze [pos] offset-fn)
      offset-fn)))

(defn -main []
  (let [maze (map #(Integer/parseInt %) (str/split (slurp "input/5.txt") #"\n"))
        args [0 (count maze) 0 (vec maze)]]
    (println (apply find-exit (conj args inc)))
    (println (apply find-exit (conj args #(if (>= % 3) (dec %) (inc %)))))))
