(ns test-problem
    (:require [clojure.test :refer :all]))

;; Structure definition
(defrecord node [key val l r])

;; Defining the tree
(def BST0 false)
(def BST1 (node. 1 "abc" false false))
(def BST4 (node. 4 "dcj" false (node. 7 "ruf" false false)))
(def BST3 (node. 3 "ilk" BST1 BST4))
(def BST42
    (node. 42 "ily"
               (node. 27 "wit" (node. 14 "olp" false false) false)
               (node. 50 "dug" false false)))
(def BST10
    (node. 10 "why" BST3 BST42))

;; Signature (data types): BST, Natural -> String or false
;; Purpose: try to find node with given key, if found produce value; if not found produce false.
(defn lookup-key [t k]
    (cond (false? t) false
          :else (cond (= k (:key t)) (:val t)
                      (< k (:key t)) (lookup-key (:l t) k)  ; go left
                      (> k (:key t)) (lookup-key (:r t) k)  ; go right
                      )))

(deftest tester-lookup
    (is (= false (lookup-key BST0 99)))
    (is (= "abc" (lookup-key BST1 1)))
    (is (= false (lookup-key BST1 0)))      ; L fail
    (is (= false (lookup-key BST1 99)))     ; R fail
    (is (= "abc" (lookup-key BST10 1)))     ; L, L succeed
    (is (= "dcj" (lookup-key BST10 4)))     ; L, R succeed
    (is (= "wit" (lookup-key BST10 27)))    ; R, L succeed
    (is (= "dug" (lookup-key BST10 50))))    ; R, R succeed

;; Signature (data types): BST -> Number
;; Purpose: produces the sum of all keys in the BST
(defn sum-of-keys [t]
    (cond (false? t) 0
          :else (+ (:key t)
                   (sum-of-keys (:l t))
                   (sum-of-keys (:r t)))))

(deftest tester-sum
    (is (= 0 (sum-of-keys BST0)))
    (is (= 1 (sum-of-keys BST1)))
    (is (= 15 (sum-of-keys BST3))))

;; Signature (data types): Integer, String, BST -> BST
;; Purpose: adds a node with key : Integer and value : String to BST, inserting it into the proper place
;; ASSUMES: the entry is not already in the tree
(defn add-node [key value t]
    (cond (false? t) (node. key value false false)
          :else (if (< key (:key t))
                    (node. (:key t) (:val t) (add-node key value (:l t)) (:r t))
                    (node. (:key t) (:val t) (:l t) (add-node key value (:r t))))))

(deftest tester-add
    (is (= (node. 2 "def" false false) (add-node 2 "def" BST0)))
    (is (= (node. 4 "dcj" (node. 2 "def" false false) (node. 7 "ruf" false false)) (add-node 2 "def" BST4)))
    (is (= (node. 4 "dcj" false (node. 7 "ruf" (node. 5 "def" false false) false)) (add-node 5 "def" BST4)))
    (is (= (node. 42 "ily"
                    (node. 27 "wit" (node. 14 "olp" false false) false)
                    (node. 50 "dug" false (node. 51 "obg" false false))))
        (add-node 51 "obg" BST42))
    (is (= (node. 42 "ily"
                    (node. 27 "wit" (node. 14 "olp" false false) false)
                    (node. 50 "dug" (node. 49 "obg" false false) false))
        (add-node 49 "obg" BST42))))

(run-tests 'test-problem)