(ns day8
  (:require [clojure.string :as str]))

(def registers {})

(def operators
  {
   ">" #(> %1 %2)
   "<" #(< %1 %2)
   ">=" #(>= %1 %2)
   "<=" #(<= %1 %2)
   "==" #(= %1 %2)
   "!=" #(not= %1 %2)
   "inc" #(+ %1 %2)
   "dec" #(- %1 %2)
   })

(defn verify [register operator value]
  (let [register-value (get registers register 0)]
    (apply (get operators operator) [register-value (Integer/parseInt value)])))

(defn execute [register operator value]
  (let [register-value (get registers register 0)
        result (apply (get operators operator) [register-value (Integer/parseInt value)])]
    (alter-var-root #'registers assoc register result)))

(defn run-instructions [instructions]
  (reduce
    (fn [max-register-value [operation condition]]
      (when (apply verify condition) (apply execute operation))
      (let [current-max-register-value
            (if (pos? (count registers)) (apply max (vals registers)) 0)]
        (if (> current-max-register-value max-register-value)
          current-max-register-value
          max-register-value)))
    0
    instructions))

(defn -main []
  (let [rows (str/split (str/trim (slurp "input/8.txt")) #"\n")
        instructions (map (fn [row]
                            (let [[operation condition] (str/split row #"\sif\s")]
                              [(str/split operation #"\s") (str/split condition #"\s")]))
                          rows)]
    (println (run-instructions instructions))
    ;(loop [instr instructions]
    ;  (let [[operation condition] (first instr)]
    ;    (when (apply verify condition) (apply execute operation)))
    ;  (when (> (count instr) 1)
    ;    (recur (rest instr))))
    (println (apply max (vals registers)))))
