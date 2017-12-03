(ns day3)

(def n 361527)

(defn distance [n]
  (let [frontier-size-multiplier 8
        frame (atom 1)
        max-num-in-frame (atom 1)]
    (while (< @max-num-in-frame n)
      (do
        (reset! max-num-in-frame
                (reduce
                  +
                  1
                  (map #(* frontier-size-multiplier %) (range (+ @frame 1)))))
        (swap! frame inc)))
    (swap! frame dec)
    (let [min-num-in-frame (+ (- @max-num-in-frame (* frontier-size-multiplier @frame)) 1)
          idx (mod (- n min-num-in-frame) (* @frame 2))
          min-dis-in-frame-idx (- @frame 1)]
      (+ @frame (Math/abs (- idx min-dis-in-frame-idx))))))

;;
;; No solution for the second part of the problem in Clojure as I was unable
;; to work around (or just work with) the immutable implementation of maps
;; and the fact that, when transient, println does not show their content,
;; showing instead a cryptic object signature similar to the one generated
;; by Object.toString in Java.
;;
;; Solved everything in Python instead:
;; https://gist.github.com/gpiancastelli/ea2263bd76bb82faa79f2a00acfcd37f
;;

(defn -main []
  (println (distance n)))
