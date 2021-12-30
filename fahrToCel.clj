(ns test-problem
    (:require [clojure.test :refer :all]))

(with-test ;; complete test definition
    (defn f2C [f]
        (* 5/9 (- f 32)))
    (is (= 0 (f2C 32)))
    (is (= 100 (f2C 212)))
    (is (= -40 (f2C -40))))

(deftest fahrToCel ;; more compact way, without function definition again
    (is (= 0 (f2C 32)))
    (is (= 100 (f2C 212)))
    (is (= -40 (f2C -40))))

(println "=========\nTests")
(run-tests 'test-problem)
(println "=========")
(print "Please enter the temperature in Fahrenheit: ")
(flush)
(def temp (Integer/parseInt (read-line)))
(println (str temp "ºF is equal to " (Math/ceil (f2C temp)) "ºC."))