;; PROBLEM:
;; Design a data definition to help a teacher organize their next field trip. On the trip, lunch must be provided for all students. For each student, track their name, their grade (from 1 to 12), and whether or not they have allergies.
;; To plan for the field trip, if students are in grade 6 or below, the teacher is responsible for keeping track of their allergies. If a student has allergies, and is in a qualifying grade, their name should be added to a special list. Design a function to produce true if a student name should be added to this list.

(ns allergies
    (:require [clojure.test :refer :all]))

;; # DATA DEFINITION

;; Type comment: Student is (name grade allergies)
;; Interpretation: (name grade allergies) records the name of the student, their grade and whether or not they have allergies

;; Examples
(defrecord student [name grade allergies])
(def Stu1 (student. "Claire" 9 true))
(def Stu2 (student. "Leon" 12 false))

;; Template function
;; (defn fnForStudent [student]
;;     (... (fnName student))      ; String
;;     (... (fnGrade student))     ; Natural
;;     (... (fnAllergies student)) ; Boolean
;; )
;; Rules used:
;; - Compound rule: 3 fields

;; # FUNCTIONS

;; Signature (data types): Student -> Boolean
;; Purpose: produces true if the student is in grade 6 or below and has allergies

;; Stub (function definition that produces a dummy result):
; (defn keepTrack? [student] false)

;; Examples
;; (is (= true (keepTrack? (student. "Ana" 6 true))))
;; (is (= false (keepTrack? (student. "Chris" 6 false))))
;; (is (= false (keepTrack? (student. "Mary" 5 false))))
;; (is (= true (keepTrack? (student. "Joseph" 1 true))))
;; (is (= false (keepTrack? (student. "John" 7 true))))
;; (is (= false (keepTrack? (student. "Christie" 12 false))))

;; Template: from data definition
;; Code body
(defn keepTrack? [student]
    (and (<= (:grade student) 6) (= true (:allergies student))))

;; Test and Debug
(deftest testKeepTrack
    (is (= true (keepTrack? (student. "Ana" 6 true))))
    (is (= false (keepTrack? (student. "Chris" 6 false))))
    (is (= false (keepTrack? (student. "Mary" 5 false))))
    (is (= true (keepTrack? (student. "Joseph" 1 true))))
    (is (= false (keepTrack? (student. "John" 7 true))))
    (is (= false (keepTrack? (student. "Christie" 12 false)))))

(run-tests 'allergies)