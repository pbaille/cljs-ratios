(ns cljs-ratios.core 
  (:refer-clojure :exclude [+ - * / mod > < >= <= number? integer? = ratio? float])
  (:require [cljs-ratios.ratios :as r :refer [rat ratio? value int?]]))


;;Casters, Identifiers, Helpers
;------------------------------------------------

  (defn float? [x] 
    (and (cljs.core/number? x) (not= 0 (cljs.core/mod x 1))))

  (defn number? [x] 
    (or (cljs.core/number? x) (ratio? x)))

  (defn integer? [x] 
    (or (cljs.core/integer? x) 
        (and (ratio? x) (int? x))))
  
  (defn float [x]
    (if (ratio? x) (value x) (clojure.core/float x)))
  
  (defn rats-int-cast 
    "convert rat to int when possible"
    [args] 
    (map #(if (and (ratio? %1) (int? %1)) (value %1) %1) args))

  (defn some-rats? [args] 
    (not (every? #(not (ratio? %1)) args)))
  
  (defn all-rats? [args] 
    (every? ratio? args))
    
  (defn some-floats? [args] 
    (if (seq (filter float? args)) true false))

  (defn cast-rats [args]
    (map #(if (ratio? %1) (value %1) %1) args))

  (defn map-rat [args] 
    (map rat args))
  
  (defn rat-auto-cast [x]
    (if (int? x) (value x) x))

;;Basic operators
;------------------------------------------------

  (defn + [& args] 
    (let [args (rats-int-cast args)]
      (if-not (some-rats? args)
        (apply cljs.core/+ args)
        (if-not (some-floats? args)
          (rat-auto-cast (apply r/+ (map-rat args)))
          (apply cljs.core/+ (cast-rats args))))))

  (defn - [& args] 
    (let [args (rats-int-cast args)]
      (if-not (some-rats? args)
        (apply cljs.core/- args)
        (if-not (some-floats? args)
          (rat-auto-cast (apply r/- (map-rat args)))
          (apply cljs.core/- (cast-rats args))))))
  
  (defn * [& args] 
    (let [args (rats-int-cast args)]
      (if-not (some-rats? args)
        (apply cljs.core/* args)
        (if-not (some-floats? args)
          (rat-auto-cast (apply r/* (map-rat args)))
          (apply cljs.core/* (cast-rats args))))))

  (defn / [& args] 
    (if (some-floats? args)
      (apply cljs.core// (cast-rats args))
      (let [res (apply r// (map-rat args))]
        (if (r/int? res) (r/value res) res))))
  
  (defn = [& args] 
    (apply cljs.core/= (cast-rats args)))
  
  (defn mod [a b] 
    (let [args (rats-int-cast [a b])]
      (if-not (some-rats? args)
        (apply cljs.core/mod args)
        (if-not (some-floats? args)
          (rat-auto-cast (apply r/mod (map-rat args)))
          (apply cljs.core/mod (cast-rats args))))))

  (defn >  [& args] 
    (if-not (some-rats? args)
      (apply cljs.core/> args)
      (apply cljs.core/>  (cast-rats args))))
  (defn >= [& args] 
    (if-not (some-rats? args)
      (apply cljs.core/>= args)
      (apply cljs.core/>= (cast-rats args))))
  (defn <  [& args] 
    (if-not (some-rats? args)
      (apply cljs.core/< args)
      (apply cljs.core/<  (cast-rats args))))
  (defn <= [& args] 
    (if-not (some-rats? args)
      (apply cljs.core/<= args)
      (apply cljs.core/<= (cast-rats args))))
  
  (defn pow [a b] 
    (let [args (rats-int-cast [a b])]
      (if-not (some-rats? args)
        (apply (.-pow js/Math) args)
        (if-not (some-floats? args)
          (rat-auto-cast (apply r/pow (map-rat args)))
          (apply (.-pow js/Math) (cast-rats args))))))
  
  (defn abs [x] 
    (cond 
      (cljs.core/number? x) (.abs js/Math x) 
      (ratio? x) (rat-auto-cast (r/abs x)) 
      :else (throw (js/Error. "arg must be a number or rational"))))
  