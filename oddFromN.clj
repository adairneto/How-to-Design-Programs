;; Signature (data types): natural -> ListOfNatural
;; Purpose: produces a list of all odd numbers (n, n-1, ..., 1)
;; Stub (function definition that produces a dummy result): (defn odd-from-n [n] 1)

;; Examples
;; (is (= (empty '()) (odd-from-n 0)))
;; (is (= (cons 1 (empty '())) (odd-from-n 1)))
;; (is (= (cons 3 (cons 1 (empty '()))) (odd-from-n 3)))
;; (is (= (cons 3 (cons 1 (empty '()))) (odd-from-n 4)))

;; Template: From data definition

;; Code body
(ns test-problem
    (:require [clojure.test :refer :all]))

(defn odd-from-n [n]
  (cond (zero? n) (empty '())
        (odd? n) (cons n (odd-from-n (dec n)))
        :else (odd-from-n (dec n))))

;; Test and Debug
    
(deftest testOdd
    (is (= (empty '()) (odd-from-n 0)))
    (is (= (cons 1 (empty '())) (odd-from-n 1)))
    (is (= (cons 3 (cons 1 (empty '()))) (odd-from-n 3)))
    (is (= (cons 3 (cons 1 (empty '()))) (odd-from-n 4))))

(run-tests 'test-problem)