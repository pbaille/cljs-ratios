(ns cljs-numbers.ratios
  (:refer-clojure :exclude [= + - * / to-array < > <= >= mod ratio?]))

(defn rat [& args]
  (let [cnt (count args)]
    (case cnt
      0 (js/Ratio. 0 1 true) 
      1 (js/Ratio. (first args) 1 true)
      2 (js/Ratio (first args) (second args) true))))

(defn ratio? [r] (instance? js/Ratio r))


(defn den [r] (.denominator r))

(defn numer [r] (.numerator r))


(defn value [r] (.valueOf r))

(defn int? [x] 
  (if (ratio? x) (cljs.core/= (den x) 1) (integer? x)))

(defn abs [r] (.abs r))

(defn negate [r] (.negate r))


(defn + [& args] 
  (reduce #(.add %1 %2) args))

(defn - 
  ([x] (.negate x))
  ([f & n] 
  (reduce #(.subtract %1 %2) f n)))

(defn / [& args] (reduce #(.divide %1 %2) args))

(defn * [& args]  (reduce #(.multiply %1 %2) args))

(defn = [& args] (every? #(.equals (first %) (second %)) (partition 2 1 args)))

(defn >  [& args] (apply cljs.core/>  (map value args)))
(defn >= [& args] (apply cljs.core/>= (map value args)))
(defn <  [& args] (apply cljs.core/<  (map value args)))
(defn <= [& args] (apply cljs.core/<= (map value args)))

(defn mod [a b]
  (if (> b a) a (mod (- a b) b)))

(defn pow [r n] (.pow r n))

(defn to-array [r] (.toArray r))

(defn to-string [r] (.toString r))

(defn ceil [r] (.ceil r))

(defn floor [r] (.floor r))
