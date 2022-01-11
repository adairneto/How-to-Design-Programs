;; Data Definition

;; ListOfInt is one of:
;; - empty
;; - (cons Int ListOfInt)
;; interp. a list of integers
(ns test-problem
  (:require [clojure.test :refer :all]))

(def LS0 (empty '()))
(def LS1 (cons 1 (empty '())))
(def LS2 (cons 1 (cons 2 (empty '()))))
(def LS3 (cons 3 (cons 2 (cons 1 (empty '())))))

;; Functions:

;; ListOfInt, ListOfInt -> ListOfInt
;; Assumption: lists are in ascending order
;; produces a sorted single list
(defn merge [lsta lstb]
  (cond (empty? lsta) lstb
        (empty? lstb) lsta
        :else (if (< (first lsta) (first lstb))
                  (cons (first lsta) (merge (rest lsta) lstb))
                  (cons (first lstb) (merge lsta (rest lstb))))))

(deftest test-merge
  (is (= (empty '()) (merge (empty '()) (empty '()))))
  (is (= (list 1) (merge (empty '()) (list 1))))
  (is (= (list 1) (merge (list 1) (empty '()))))
  (is (= (list 1 2) (merge (list 1) (list 2))))
  (is (= (list 1 2 3 4) (merge (list 1 2) (list 3 4))))
  (is (= (list 1 2 3 4 5 6) (merge (list 1 3 5) (list 2 4 6)))))

(run-tests 'test-problem)
