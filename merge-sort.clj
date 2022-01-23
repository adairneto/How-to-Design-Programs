(ns test-problem
  (:require [clojure.test :refer :all]))

;; (listof Number) -> (listof Number)
;; produces sorted list in ascending order using merge sort
;; (defn merge-sort [lon] lon)

;; template according to generative recursion

;; (listof Number), (listof Number) -> (listof Number)
;; merge two lists into a single in ascending order
;; ASSUME: both lists are already sorted
(defn merge [lon1 lon2]
  (cond (empty? lon1) lon2
        (empty? lon2) lon1
        :else (if (< (first lon1) (first lon2))
                  (cons (first lon1) (merge (rest lon1) lon2))
                  (cons (first lon2) (merge lon1 (rest lon2))))))

(deftest merger
  (is (= '() (merge '() '())))
  (is (= (list 1) (merge (list 1) '())))
  (is (= (list 2) (merge '() (list 2))))
  (is (= (list 1 2 3 4 5 6 7 8) (merge (list 1 3 5 6) (list 2 4 7 8)))))

(defn merge-sort [lon]
  (cond (empty? lon) '()
        (empty? (rest lon)) lon
        :else (merge (merge-sort (take (quot (count lon) 2) lon))
                      (merge-sort (drop (quot (count lon) 2) lon)))))

(deftest sorter
  (is (= '() (merge-sort '())))
  (is (= (list 2) (merge-sort (list 2))))
  (is (= (list 1 2) (merge-sort (list 1 2))))
  (is (= (list 3 4) (merge-sort (list 4 3))))
  (is (= (list 1 2 3 4 5 6 7 8) (merge-sort (list 6 5 3 1 8 7 2 4)))))

(run-tests 'test-problem)