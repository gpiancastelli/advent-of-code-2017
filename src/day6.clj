(ns day6
  (:require [clojure.string :as str]))

(defn find-max-blocks-index [banks]
  (let [max-num-blocks (atom 0)
        index (atom 0)]
    (doseq [[i num-blocks] (map-indexed vector banks)]
      (when (> num-blocks @max-num-blocks)
        (reset! max-num-blocks num-blocks)
        (reset! index i)))
    @index))

(defn redistribute [banks]
  (let [index (atom (find-max-blocks-index banks))]
    (loop [num-blocks (get banks @index)
           banks (assoc banks @index 0)]
      (swap! index inc)
      (when (>= @index (count banks))
        (reset! index 0))
      (if (zero? num-blocks)
        banks
        (recur (dec num-blocks)
               (assoc banks @index (inc (get banks @index))))))))

(defn find-repetition [cycles configurations banks]
  (let [configuration banks]
    (if (contains? configurations configuration)
      [cycles (get configurations configuration)]
      (recur (inc cycles)
             (assoc configurations configuration cycles)
             (redistribute banks)))))

(defn -main []
  (let [banks (map #(Integer/parseInt %) (str/split (slurp "input/6.txt") #"\s"))
        [total-cycles repeated-configuration-cycles] (find-repetition 0 {} (vec banks))]
    (println total-cycles)
    (println (- total-cycles repeated-configuration-cycles))))
