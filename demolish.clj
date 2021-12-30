;; Data Definition
;; Type comment: BuildingStatus is one of:
;; - "New"
;; - "Old"
;; - "Heritage"
;; Interpretation: classification of a building according to city guidelines

;; Examples: are redundant for enumerations

;; Functions
;; Template function
(defn fnForBuildingStatus [status]
    (cond (= status "New") (... status)
          (= status "Old") (... status)
          (= status "Heritage") (... status)))
;; Template rules used:
;; - one of: 3 cases
;; - atomic distinct value: "New"
;; - atomic distinct value: "Old"
;; - atomic distinct value: "Heritage"

;; Signature (data types): BuildingStatus -> Boolean
;; Purpose: determine whether a building should be demolished 

;; Stub (function definition that produces a dummy result):
;; (defn demolish? [status]
;;     true)

;; Examples
;; (is (= false (demolish? "New")))
;; (is (= true (demolish? "Old")))
;; (is (= false (demolish? "Heritage")))

;; Template: from data definition

;; Code body
(ns test-problem
    (:require [clojure.test :refer :all]))

(defn demolish? [status]
    (cond (= status "New") false
          (= status "Old") true
          (= status "Heritage") false))

;; Test and Debug
(deftest testDemolish
    (is (= false (demolish? "New")))
    (is (= true (demolish? "Old")))
    (is (= false (demolish? "Heritage"))))
(run-tests 'test-problem)