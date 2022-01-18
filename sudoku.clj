(ns test-problem
  (:require [clojure.test :refer :all]))

(defn r-c->pos [r c]
    (+ (* r 9) c))  ;helpful for writing tests

;; Unit is (listof Pos) of length 9
;; interp. 
;;  The position of every square in a unit. There are
;;  27 of these for the 9 rows, 9 columns and 9 boxes.


;; =================
;; Constants:

(def ALL-VALS (list 1 2 3 4 5 6 7 8 9))

(def B false) ;B stands for blank


(def BD1 
  (list B B B B B B B B B
        B B B B B B B B B
        B B B B B B B B B
        B B B B B B B B B
        B B B B B B B B B
        B B B B B B B B B
        B B B B B B B B B
        B B B B B B B B B
        B B B B B B B B B))

(def BD2 
  (list 1 2 3 4 5 6 7 8 9 
        B B B B B B B B B 
        B B B B B B B B B 
        B B B B B B B B B 
        B B B B B B B B B
        B B B B B B B B B
        B B B B B B B B B
        B B B B B B B B B
        B B B B B B B B B))

(def BD3 
  (list 1 B B B B B B B B
        2 B B B B B B B B
        3 B B B B B B B B
        4 B B B B B B B B
        5 B B B B B B B B
        6 B B B B B B B B
        7 B B B B B B B B
        8 B B B B B B B B
        9 B B B B B B B B))

(def BD4                ;easy
  (list 2 7 4 B 9 1 B B 5
        1 B B 5 B B B 9 B
        6 B B B B 3 2 8 B
        B B 1 9 B B B B 8
        B B 5 1 B B 6 B B
        7 B B B 8 B B B 3
        4 B 2 B B B B B 9
        B B B B B B B 7 B
        8 B B 3 4 9 B B B))

(def BD4s               ;solution to 4
  (list 2 7 4 8 9 1 3 6 5
        1 3 8 5 2 6 4 9 7
        6 5 9 4 7 3 2 8 1
        3 2 1 9 6 4 7 5 8
        9 8 5 1 3 7 6 4 2
        7 4 6 2 8 5 9 1 3
        4 6 2 7 5 8 1 3 9
        5 9 3 6 1 2 8 7 4
        8 1 7 3 4 9 5 2 6))

(def BD5                ;hard
  (list 5 B B B B 4 B 7 B
        B 1 B B 5 B 6 B B
        B B 4 9 B B B B B
        B 9 B B B 7 5 B B
        1 8 B 2 B B B B B 
        B B B B B 6 B B B 
        B B 3 B B B B B 8
        B 6 B B 8 B B B 9
        B B 8 B 7 B B 3 1))

(def BD5s               ;solution to 5
  (list 5 3 9 1 6 4 8 7 2
        8 1 2 7 5 3 6 9 4
        6 7 4 9 2 8 3 1 5
        2 9 6 4 1 7 5 8 3
        1 8 7 2 3 5 9 4 6
        3 4 5 8 9 6 1 2 7
        9 2 3 5 4 1 7 6 8
        7 6 1 3 8 2 4 5 9
        4 5 8 6 7 9 2 3 1))

(def BD6                ;hardest ever? (Dr Arto Inkala)
  (list B B 5 3 B B B B B 
        8 B B B B B B 2 B
        B 7 B B 1 B 5 B B 
        4 B B B B 5 3 B B
        B 1 B B 7 B B B 6
        B B 3 2 B B B 8 B
        B 6 B 5 B B B B 9
        B B 4 B B B B 3 B
        B B B B B 9 7 B B))

(def BD7                 ; no solution 
  (list 1 2 3 4 5 6 7 8 B 
        B B B B B B B B 2 
        B B B B B B B B 3 
        B B B B B B B B 4 
        B B B B B B B B 5
        B B B B B B B B 6
        B B B B B B B B 7
        B B B B B B B B 8
        B B B B B B B B 9))

;; Positions of all the rows, columns and boxes:

(def ROWS
  (list (list  0  1  2  3  4  5  6  7  8)
        (list  9 10 11 12 13 14 15 16 17)
        (list 18 19 20 21 22 23 24 25 26)
        (list 27 28 29 30 31 32 33 34 35)
        (list 36 37 38 39 40 41 42 43 44)
        (list 45 46 47 48 49 50 51 52 53)
        (list 54 55 56 57 58 59 60 61 62)
        (list 63 64 65 66 67 68 69 70 71)
        (list 72 73 74 75 76 77 78 79 80)))

(def COLS
  (list (list 0  9 18 27 36 45 54 63 72)
        (list 1 10 19 28 37 46 55 64 73)
        (list 2 11 20 29 38 47 56 65 74)
        (list 3 12 21 30 39 48 57 66 75)
        (list 4 13 22 31 40 49 58 67 76)
        (list 5 14 23 32 41 50 59 68 77)
        (list 6 15 24 33 42 51 60 69 78)
        (list 7 16 25 34 43 52 61 70 79)
        (list 8 17 26 35 44 53 62 71 80)))

(def BOXES
  (list (list  0  1  2  9 10 11 18 19 20)
        (list  3  4  5 12 13 14 21 22 23)
        (list  6  7  8 15 16 17 24 25 26)
        (list 27 28 29 36 37 38 45 46 47)
        (list 30 31 32 39 40 41 48 49 50)
        (list 33 34 35 42 43 44 51 52 53)
        (list 54 55 56 63 64 65 72 73 74)
        (list 57 58 59 66 67 68 75 76 77)
        (list 60 61 62 69 70 71 78 79 80)))

(def UNITS (concat ROWS COLS BOXES))

;; =================
;; Functions:

;; Board -> Boolean
;; produce true if board is solved
;; Assume board is valid, so it is solved it is full
;; (defn solved? [bd] false) ;stub
(defn solved? [bd]
    (every? number? bd))

(deftest solved
    (is (= (solved? BD4s) true))
    (is (= (solved? BD1) false))
    (is (= (solved? BD2) false)))

;; Board Pos -> Val or false
;; Produce value at given position on board.
(defn read-square [bd p]
    (nth bd p))         

(deftest square-reader
    (is (= (read-square BD2 (r-c->pos 0 5)) 6))
    (is (= (read-square BD3 (r-c->pos 7 0)) 8)))      

;; Board Pos Val -> Board
;; produce new board with val at given position
(defn fill-square [bd p nv]
    (concat (take p bd)
            (list nv)
            (drop (+ 1 p) bd)))

(deftest filler
    (is (= (fill-square BD1 (r-c->pos 0 0) 1)
        (cons 1 (rest BD1)))))

;; Board -> Pos
;; produces the position of the first blank square
;; Assume: the board has at least one blank square
;; (defn find-blank [bd] 0) ;stub

(defn find-blank [bd]
  (cond (empty? bd) (println "The board didn't have a blank space.")
        :else (if (false? (first bd))
                  0
                  (+ 1 (find-blank (rest bd))))))

(deftest blanks
  (is (= (find-blank BD1) 0))
  (is (= (find-blank (cons 2 (rest BD1))) 1))
  (is (= (find-blank (cons 2 (cons 4 (rest (rest BD1))))) 2)))

;; Pos, Board -> listof Board
;; produce 9 board with blank filled with Natural[1, 9]
;; (defn fill-with-1-9 [p bd] empty) ;stub

(defn fill-with-1-9 [p bd]
  (letfn [(build-one [n]
            (fill-square bd p (+ n 1)))]
          (map build-one (range 9))))

(deftest filler-with
  (is (= (fill-with-1-9 0 BD1)
         (list (cons 1 (rest BD1))
               (cons 2 (rest BD1))
               (cons 3 (rest BD1))
               (cons 4 (rest BD1))
               (cons 5 (rest BD1))
               (cons 6 (rest BD1))
               (cons 7 (rest BD1))
               (cons 8 (rest BD1))
               (cons 9 (rest BD1))))))

;; Board -> Boolean
;; produce true if no unit on the board has the same value twice; false otherwise
;; (defn valid-board? [bd] false) ;stub

(defn valid-board? [bd]
  (letfn [(valid-units? [lou]             ; listof Unit -> Boolean
            (every? valid-unit? lou))     ; andmap in Racket
          (valid-unit? [u]                ; Unit -> Boolean
            (no-duplicates?
              (keep-only-values
                (read-unit u))))
          (read-unit [u]                  ; Unit -> listof Val|false
            (map read-pos u))
          (read-pos [p]                   ; Pos -> Val|false
            (read-square bd p))           ; produce contents of bd at p
          (keep-only-values [lovf]        ; listof Val|false -> listof Val
            (filter number? lovf))
          (no-duplicates? [lov]           ; listof Val -> Boolean
            (cond (empty? lov) true       ; produce true if no value in lov appears twice
                  :else (if (some #(= (first lov) %) (rest lov))    ; contains?
                            false
                            (no-duplicates? (rest lov)))))]                  
  (valid-units? UNITS)))

(deftest valid?
  (is (= (valid-board? BD1) true))
  (is (= (valid-board? BD2) true))
  (is (= (valid-board? BD3) true))
  (is (= (valid-board? BD4) true))
  (is (= (valid-board? BD5) true))
  (is (= (valid-board? (cons 2 (rest BD2))) false))
  (is (= (valid-board? (cons 2 (rest BD3))) false))
  (is (= (valid-board? (fill-square BD4 1 6)) false)))

;; (listof Board) -> (listof Board)
;; produce list containing only valid boards
;; (defn keep-only-valid [lobd] '()) ;stub

(defn keep-only-valid [lobd]
  (filter valid-board? lobd))

(deftest keep-valid
  (is (= (keep-only-valid (list (cons 1 (cons 1 (rest (rest BD1)))))) '())))

;; Board -> listof Board
;; produce list of valid next boards from board
;; finds first empty square, fills it with Natural[1,9], keeps only valid boards
;; (defn next-boards [bd] ) ;stub

(defn next-boards [bd]
    (keep-only-valid (fill-with-1-9 (find-blank bd) bd)))

(deftest nexter
    (is (= (next-boards (cons 1 (rest BD1)))
           (list (cons 1 (cons 2 (rest (rest BD1))))
                 (cons 1 (cons 3 (rest (rest BD1))))
                 (cons 1 (cons 4 (rest (rest BD1))))
                 (cons 1 (cons 5 (rest (rest BD1))))
                 (cons 1 (cons 6 (rest (rest BD1))))
                 (cons 1 (cons 7 (rest (rest BD1))))
                 (cons 1 (cons 8 (rest (rest BD1))))
                 (cons 1 (cons 9 (rest (rest BD1))))))))

;; Board -> Board or false
;; produce a solution for bd; or false if bd is unsolvable
;; Assume: bd is valid
;; (defn solve [bd] false) ;stub

(defn solve [bd]
    (letfn [(solve--bd [bd]
                (if (solved? bd)
                    bd
                    (solve--lobd (next-boards bd))))
            (solve--lobd [lobd]
                (cond (empty? lobd) false
                      :else (let [try (solve--bd (first lobd))]
                                 (if (not (false? try))
                                     try
                                     (solve--lobd (rest lobd))))))]
    (solve--bd bd)))

(deftest solver
    (is (= (solve BD4) BD4s))
    (is (= (solve BD5) BD5s))
    (is (= (solve BD7) false)))

(run-tests 'test-problem)