(ns utils.ratios-spec
  (:require-macros 
    [cemerick.cljs.test :refer [is deftest with-test run-tests testing test-var]])
  (:refer-clojure :exclude [+ - / * < <= > >= mod int? ratio?])
  (:require [cemerick.cljs.test :as t]
            [cljs-numbers.ratios :as r :refer [rat ratio? den numer int? value + - / * mod abs to-array to-string negate ceil floor < > <= >= pow]]
            [cljs-numbers.spec-helpers :refer [is-true falsey truthy is-nil]]))

;;Test
;-----------------------------------------------------------------------

  (deftest rat-constructor
    (truthy (rat))
    (truthy (rat 1))
    (truthy (rat 4))
    (truthy (rat 1 2))
    (truthy (rat 0 1)))

  (deftest ratio?-tests
    (is-true (ratio? (rat 1 2))))
  
  (deftest den-tests
    (is 4 (den (rat 1 4))))
  
  (deftest numer-tests
    (is 4 (numer (rat 4 89))))
  
  (deftest value-tests
    (is 2 (value (rat 4 2)))
    (is 0.5 (value (rat 1 2))))
  
  (deftest int?-tests
    (is-true (int? (rat 4 2)))
    (falsey (int? (rat 1 2))))
  
  (deftest abs-tests
    (is (rat 1 2) (abs (rat -1 2))))

  (deftest abs-tests
    (is (rat 1 2) (negate (rat -1 2))))
  
  (deftest plus-tests
    (is (rat 1 2) (+ (rat -1 2) (rat 1)))
    (is (rat 1 2) (+ (rat 1 2)))
    (is (rat 1 2) (+ (rat -1 2) (rat 1) (rat -1 3) (rat 2 6))))
  
  (deftest substract-tests
    (is (rat -1 2) (- (rat 1 4) (rat 3 4)))
    (is (rat -1 2) (- (rat 1 2)))
    (is (rat 1 2) (- (rat 3 2) (rat 1) (rat -1 3) (rat 2 6))))

  (deftest div-tests
    (is (rat 1 2) (/ (rat 1 4) (rat 1 4)))
    (is (rat 1 2) (/ (rat 2)))
    (is (rat 1 2) (/ (rat 1 2) (rat 2) (rat 1 2))))
  
  (deftest mult-tests
    (is (rat 1 2) (* (rat 1 4) (rat 2)))
    (is (rat 1 2) (* (rat 1 2)))
    (is (rat 1 2) (* (rat 1) (rat 1 2) (rat -1 2) (rat -2))))
  
  (deftest eq-tests
    (is-true (r/= (rat 1 2) (rat 2 4)))
    (is-true (r/= (rat 2) (rat 4 2)))
    (falsey (r/= (rat 2) (rat 1 2)))
    (falsey (r/= (rat 1 2) (rat 2 4) (rat 4 6)))
    (is-true (r/= (rat 1 2) (rat 2 4) (rat 3 6))))
  
  (deftest gt-tests
    (is-true (> (rat 1 2) (rat 1 4)))
    (is-true (> (rat 2) (rat 1 2)))
    (is-true (> (rat 2) (rat 1 2) (rat 1 4) (rat 1 5)))
    (falsey (> (rat 2) (rat 1 2) (rat 1 4) (rat 1 4) (rat 1 5)))
    (falsey (> (rat 2) (rat 10 2)))
    (falsey (> (rat 2) (rat 1) (rat 10 2))))
  
  (deftest gte-tests
    (is-true (>= (rat 1 2) (rat 1 4)))
    (is-true (>= (rat 2) (rat 2)))
    (is-true (>= (rat 2) (rat 2) (rat 1 2) (rat 1 4) (rat 1 5)))
    (falsey (>= (rat 2) (rat 10 2)))
    (falsey (>= (rat 2) (rat 1) (rat 10 2))))
  
  (deftest lt-tests
    (is-true (< (rat 1 4) (rat 1 2)))
    (falsey (< (rat 1 4) (rat 1 4)))
    (is-true (< (rat 2) (rat 3) (rat 25 5)))
    (is-true (< (rat 2) (rat 7 3) (rat 9 3)))
    (falsey (< (rat 2) (rat 2) (rat 25 5))))
  
  (deftest lte-tests
    (is-true (<= (rat 1 4) (rat 1 2)))
    (is-true (<= (rat 1 4) (rat 1 4)))
    (is-true (<= (rat 1 4) (rat 3 4)))
    (is-true (<= (rat 2) (rat 3) (rat 25 5)))
    (falsey (<= (rat 2) (rat 3 4) (rat 25 5)))
    (falsey (<= (rat 2) (rat 5 4) (rat 25 5) (rat 0)))
    (is-true (<= (rat 2) (rat 2) (rat 25 5))))
  
  (deftest mod-tests
    (is (rat 1 2) (mod (rat 3 2) 1))
    )
  
  (deftest pow-tests
    (is (rat 1 4) (pow (rat 1 2) 2))
    )
  
  (deftest to-array-tests
    (is [3 4] (to-array (rat 3 4)))
    )
  
  (deftest to-string-tests
    (is "3/4" (to-string (rat 3 4)))
    )
  
  (deftest ceil-tests
    (is (rat 1 1) (ceil (rat 3 4)))
    )
  
  (deftest floor-tests
    (is (rat 0) (floor (rat 1 4)))
    )
  


