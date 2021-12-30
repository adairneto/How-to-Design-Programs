(ns allTrue
    (:require [clojure.test :refer :all]))

;; DATA DEFINITIONS

;; Type comment: ListOfBoolean is one of:
;;  - empty
;;  - (cons Boolean ListOfBoolean)
;; Interpretation: each entry in the list represents a true/false

;; Examples
(def LOB1 (empty '()))
(def LOB2 (cons true (cons false (empty '()))))

;; Template function
;; (defn fnForLOB [lob]
;;   (cond (empty? lob) (...)
;;         :else (... (first lob)
;;               (fnForLOB (rest lob)))))

;; Template rules used:
;;  - one of: 2 cases
;;  - atomic distinct: empty
;;  - compound: (cons Boolean ListOfBoolean)
;;  - self-reference: (rest lob) is ListOfBoolean

;; FUNCTION DEFINITIONS

;; Signature (data types): ListOfBoolean -> Boolean
;; Purpose: produces true if every value in ListOfBoolean is true, if the list is empty, produce true

;; Stub (function definition that produces a dummy result):
(defn allTrue? [lob] true)

;; Examples
;; (is (= true (allTrue? (empty '()))))
;; (is (= true (allTrue? (cons true (cons true (empty '()))))))
;; (is (= false (allTrue? (cons false (cons true (empty '()))))))
;; (is (= false (allTrue? (cons true (cons false (empty '()))))))

;; Template: from data definition
;; Code body
(defn allTrue? [lob]
  (cond (empty? lob) true
        :else (and (first lob)
                   (allTrue? (rest lob)))))

;; Test and Debug
(deftest testAllTrue
    (is (= true (allTrue? (empty '()))))
    (is (= true (allTrue? (cons true (cons true (empty '()))))))
    (is (= false (allTrue? (cons false (cons true (empty '()))))))
    (is (= false (allTrue? (cons true (cons false (empty '())))))))

(run-tests 'allTrue)