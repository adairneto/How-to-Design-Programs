;; Code body
(ns test-problem
    (:require [clojure.test :refer :all]))

(defrecord elt [name data subs])

(def F1 (elt. "F1" 1 (empty '())))
(def F2 (elt. "F2" 2 (empty '())))
(def F3 (elt. "F3" 3 (empty '())))
(def D4 (elt. "D4" 0 (list F1 F2)))
(def D5 (elt. "D5" 0 (list F3)))
(def D6 (elt. "D6" 0 (list D4 D5)))

;; First function

(declare sum-data--element)
(declare sum-data--loe)

(defn sum-data--element [e]
    (if (zero? (:data e))
        (sum-data--loe (:subs e))
        (:data e)))

(defn sum-data--loe [loe]
    (cond (empty? loe) 0
          :else (+ (sum-data--element (first loe))
                   (sum-data--loe (rest loe)))))

(deftest tester-sum
    (is (= 1 (sum-data--element F1)))
    (is (= 0 (sum-data--loe (empty '()))))
    (is (= 3 (sum-data--element D5)))
    (is (= (+ 1 2) (sum-data--element D4)))
    (is (= (+ 1 2 3) (sum-data--element D6))))

;; Second function

(declare all-names--element)
(declare all-names--loe)

(defn all-names--element [e]
    (cons (:name e)
          (all-names--loe (:subs e))))

(defn all-names--loe [loe]
    (cond (empty? loe) (empty '())
          :else (concat (all-names--element (first loe))
                        (all-names--loe (rest loe)))))

(deftest tester-all-names
    (is (= (empty '()) (all-names--loe (empty '()))))
    (is (= (list "F1") (all-names--element F1)))
    (is (= (list "D5" "F3") (all-names--element D5)))
    (is (= (list "D4" "F1" "F2") (all-names--element D4)))
    (is (= (concat (list "D4" "F1" "F2") (list "D5" "F3")) (all-names--loe (list D4 D5))))
    (is (= (list "D6" "D4" "F1" "F2" "D5" "F3") (all-names--element D6))))

;; Backtracking Search

(declare find--element)
(declare find--loe)

(defn find--element [n e]
    (if (= n (:name e))
        (:data e)
        (find--loe n (:subs e))))

(defn find--loe [n loe]
    (cond (empty? loe) false
          :else (if (not (false? (find--element n (first loe))))
                    (find--element n (first loe))
                    (find--loe n (rest loe)))))

(deftest finder
    (is (= false (find--loe "F3" (empty '()))))
    (is (= false (find--element "F3" F1)))
    (is (= 3 (find--element "F3" F3)))
    (is (= 2 (find--loe "F2" (cons F1 (cons F2 (empty '()))))))
    (is (= false (find--loe "F3" (cons F1 (cons F2 (empty '()))))))
    (is (= false (find--element "F3" D4)))
    (is (= 1 (find--element "F1" D4)))
    (is (= 2 (find--element "F2" D4)))
    (is (= 0 (find--element "D4" D4)))
    (is (= 3 (find--element "F3" D6))))

(run-tests 'test-problem)