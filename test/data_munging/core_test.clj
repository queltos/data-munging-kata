(ns data-munging.core-test
  (:use clojure.test data-munging.core))

(deftest test-read-file
  (testing "reading whole weather data"
    (is (= (count (read-file)) 33))))

(deftest test-parse-line
  (testing "parse regular line"
    (is (=
          (parse-line "  20  84    57    71          58.9       0.00 FH      150  6.3 160  13  3.6  90 43 1032.5")
          '(20 84 57)
        ))))

(deftest test-retain-max-tempspread
  (testing "first larger spread than second"
    (is (= (retain-max-tempspread '(1 7 5) '(2 7 6)) '(1 7 5))))
  (testing "first larger spread than second"
    (is (= (retain-max-tempspread '(5 9 5) '(6 11 6)) '(6 11 6)))))

(deftest test-parse-item
  (testing "parsing a number"
    (is (= (parse-item "2") 2)))
  (testing "parsing a number with asterisk"
    (is (= (parse-item "3*") 3))))
