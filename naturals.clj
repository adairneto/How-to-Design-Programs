(ns test-problem
    (:require [clojure.test :refer :all]))

(def N0 (empty '()))    ; 0
(def N1 (cons "!" N0))  ; 1
(def N2 (cons "!" N1))  ; 2
(def N3 (cons "!" N2))  ; 3
(def N4 (cons "!" N3))  ; 4
(def N5 (cons "!" N4))  ; 5
(def N6 (cons "!" N5))  ; 6
(def N7 (cons "!" N6))  ; 7
(def N8 (cons "!" N7))  ; 8
(def N9 (cons "!" N8))  ; 9

;; Primitives that operate NATURAL:
(defn ZERO? [n] (empty? n))     ; Anything      -> Boolean
(defn ADD1 [n] (cons "!" n))    ; NATURAL       -> NATURAL
(defn SUB1 [n] (rest n))        ; NATURAL[>0]   -> NATURAL

(defn ADD [a b]
    (cond (ZERO? b) a
          :else (ADD (ADD1 a) (SUB1 b))))

(deftest testADD
    (is (= N2 (ADD N2 N0)))
    (is (= N3 (ADD N0 N3)))
    (is (= N7 (ADD N3 N4))))

(defn SUBTRACT [a b]
    (cond (ZERO? b) a
          :else (SUBTRACT (SUB1 a) (SUB1 b))))

(deftest testSUB
    (is (= N2 (SUBTRACT N2 N0)))
    (is (= N4 (SUBTRACT N6 N2))))

(run-tests 'test-problem)