(ns cljs-numbers.spec-helpers 
  (:require-macros 
          [cemerick.cljs.test :refer [is deftest with-test run-tests testing test-var]])
  (:require [cemerick.cljs.test :as t]))

  (defn is-nil [expr] (is (nil? expr) true))
  (defn is-true [expr] (is expr true))
  (defn truthy [expr] (is (not (nil? expr)) true))
  (defn falsey [expr] (is (or (nil? expr) (false? expr)) true))