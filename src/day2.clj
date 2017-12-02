(ns day2
  (:require [clojure.string :as str]))

(defn row-diff [row]
  (let [row-max (apply max row)
        row-min (apply min row)]
    (- row-max row-min)))

(defn row-division [row]
  (let [pivot (first row)
        v (vec (rest row))
        i (atom 0)
        res (atom 0)]
    (while (and (< @i (count v)) (zero? @res))
      (do
        (let [el (get v @i)
              mx (max el pivot)
              mn (min el pivot)]
          (when (zero? (mod mx mn))
            (reset! res (/ mx mn))))
        (swap! i inc)))
    ;; this never results in an infinite recursion because we
    ;; are guaranteed that a divisible couple always exists
    (if (pos? @res) @res (row-division (rest row)))))

(defn checksum [f rows]
  (apply + (map f rows)))

(defn -main []
  (let [text (str/trim (slurp "input/2.txt"))
        text-rows (str/split text #"\n")
        rows (map (fn [row] (map #(Integer/parseInt %) (str/split row #"\s+"))) text-rows)]
    (println (checksum row-diff rows))
    (println (checksum row-division rows))))