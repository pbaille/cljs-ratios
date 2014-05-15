(ns cljs-numbers.core-spec
  (:require-macros 
    [cemerick.cljs.test :refer [is deftest with-test run-tests testing test-var]])
  (:refer-clojure :exclude [+ - * / mod > < >= <= number? integer? =])
  (:require [cljs-numbers.core :refer [float? number? integer? + - / * rat-auto-cast some-rats? some-floats? cast-rats map-rat < <= > >= = mod pow abs all-rats?]]
            [cljs-numbers.ratios :refer [value rat]]
            [cemerick.cljs.test :as t]
            [cljs-numbers.spec-helpers :refer [is-true falsey truthy is-nil]]))


  ;;Casters, Identifiers, Helpers
;------------------------------------------------

  (deftest float?-test 
    (is-true (float? 0.1))
    (is-true (float? (value (rat 1 3))))
    (falsey (float? (rat 1 3)))
    (falsey (float? 2)))

  (deftest number?-test 
    (is-true (number? 1))
    (is-true (number? 2.2))
    (is-true (number? (rat 1 2)))
    (falsey (number? []))
    (falsey (number? {}))
    (falsey (number? inc)))

  (deftest integer?-test 
    (is-true (integer? 1))
    (is-true (integer? (rat 3 1)))
    (falsey (integer? 1.2))
    (falsey (integer? (rat 1 2))))
  
  (deftest rats-int-cast 
    (is [1 2 3 4] [(rat 1) (rat 2 1) 3 (rat 16 4)]))

  (deftest some-rats?-test
    (is-true (some-rats? [1 2.2 (rat 1 2)]))
    (falsey (some-rats? [1 2.2 3])))
  
  (deftest all-rats?-test
    (is-true (all-rats? [(rat 1) (rat 24 25)(rat 1 2)]))
    (falsey (all-rats? [(rat 1 2) 2.2])))
    
  (deftest some-floats?-test
    (is-true (some-floats? [1 2.2 (rat 1 2)]))
    (falsey (some-floats? [1 (rat 1 2) 3])))

  (deftest cast-rat-test
    (is [1 0.5 0.25] (cast-rats [1 (rat 1 2) (rat 1 4)])))

  (deftest map-rat-test
    (is [(rat 1) (rat 1 2) (rat 1 4)] (map-rat [1 0.5 0.25])))

;;Basic operators
;------------------------------------------------

  (deftest plus-tests 
    (is 1 (+ (rat 1 2)(rat 1 2)))
    (is (rat 3 2) (+ (rat 1 2) 1))
    (is 1.2 (+ (rat 1 2) (rat 1 2) 0.2)))

  (deftest sub-tests 
    (is 0 (- (rat 1 2)(rat 1 2)))
    (is (rat -1 2) (- (rat 1 2) 1))
    (is -0.2 (- (rat 1 2) (rat 1 2) 0.2)))
  
  (deftest mult-tests 
    (is 1 (* (rat 1 2) 2))
    (is (rat 1 2) (* (rat 1 2) 1))
    (is 0.05 (* (rat 1 2) (rat 1 2) 0.2)))

  (deftest div-tests 
    (is 2 (/ (rat 1 2) 2))
    (is (rat 1 2) (/ (rat 1 2) 1))
    (is 5 (/ (rat 1 2) (rat 1 2) 0.2)))
  
  (deftest eq-tests 
    (is-true (= (rat 1 2) 0.5 (rat 2 4)))
    (is-true (= 2 2.0 (rat 2))))
  
  (deftest mod-tests
    (is (rat 1 2) (mod (rat 3 2) 1))
    (is 2 (mod 14 12)))

  (deftest gt-tests  
    (is-true (> 2 1))
    (is-true (> 1 (rat 1 2) 0.1))
    (is-true (> 2.2 2 (rat 1 9)))
    (falsey (> 1 (rat 1)))
    (falsey (> 1 (rat 2)))
    (falsey (> 1 1.0))
    (falsey (> 1 (rat 1 2) 3.3)))
  
  (deftest gte-tests 
    (is-true (>= 2 1))
    (is-true (>= 1 (rat 1 2) 0.1))
    (is-true (>= 2.2 2 (rat 1 9)))
    (is-true (>= 1 (rat 1)))
    (falsey (>= 1 (rat 2)))
    (is-true (>= 1 1.0 (rat 1 2)))
    (falsey (>= 1 (rat 1 2) 3.3)))
  
  (deftest lt-tests  
    (falsey (< 2 1))
    (falsey (< 1 (rat 1 2) 0.1))
    (falsey (< 2.2 2 (rat 1 9)))
    (falsey (< 1 (rat 1)))
    (is-true (< 1 (rat 2)))
    (falsey (< 1 1.0 (rat 1 2)))
    (falsey (< 1 (rat 1 2) 3.3)))
  
  (deftest lte-tests 
    (falsey (<= 2 1))
    (falsey (<= 1 (rat 1 2) 0.1))
    (falsey (<= 2.2 2 (rat 1 9)))
    (is-true (<= 1 (rat 1)))
    (is-true (<= 1 (rat 2)))
    (is-true (<= 1 1.0))
    (falsey (<= 1 (rat 1 2) 3.3)))
  
  (deftest pow-tests
    (is 4 (pow 2 2))
    (is 4 (pow (rat 2) 2))
    (is 4 (pow 2.0 2)))
  
  (deftest abs-tests
    (is 2 (abs (rat -2)))
    (is 2 (abs -2))
    (is 2 (abs -2.0))
    (is (rat 1 2) (abs (rat -1 2)))
    (is 3.3 (abs -3.3))
    )
  