(ns cljs-ratios.usage
  (:refer-clojure :exclude [+ - * / mod > < >= <= number? integer? float? = ratio?])
  (:require [cljs-ratios.ratios :refer [rat ratio? den numer value]]
            [cljs-ratios.core :refer [float? number? integer? + - * / = <= < > >= mod pow abs]]))

(+ 2 (/ 1 3))

(ratio? (/ 1 3))