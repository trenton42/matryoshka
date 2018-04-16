(ns matryoshka.core-test
  (:require [clojure.test :refer :all]
            [matryoshka.core :refer :all]
            [matryoshka.packer :refer :all]))

(deftest parser-case
  (testing "Test the parser"
    (let [data "key value other\na 1 2\nb 2 3"]
      (let [parsed (into [] (matryoshka.parser/parse data))]
        (is (= (get parsed 0) ["key" "value" "other"]))
        (is (= (get parsed 1) ["a" "1" "2"]))
        (is (= (get parsed 2) ["b" "2" "3"]))))
    (let [data "key         value    other\na  1       2\nb  2     3"]
      (let [parsed (into [] (matryoshka.parser/parse data))]
        (is (= (get parsed 0) ["key" "value" "other"]))
        (is (= (get parsed 1) ["a" "1" "2"]))
        (is (= (get parsed 2) ["b" "2" "3"]))))))

(deftest small-case
  (testing "A small test case"
    (let [items [(matryoshka.packer/new-doll "small" 5 10)
                 (matryoshka.packer/new-doll "medium" 7 14)
                 (matryoshka.packer/new-doll "medium-small" 8 9)
                 (matryoshka.packer/new-doll "tiny" 3 3)]]
      (let [result (matryoshka.packer/optimal-pack 15 items)]
        (is (= ["tiny" "medium" "small"] (map :name result)))))))

(deftest larger-case
  (testing "A larger test case"
    (let [items [(matryoshka.packer/new-doll "luke" 9 150)
                 (matryoshka.packer/new-doll "anthony" 13 35)
                 (matryoshka.packer/new-doll "candice" 153 200)
                 (matryoshka.packer/new-doll "dorothy" 50 160)
                 (matryoshka.packer/new-doll "puppy" 15 60)
                 (matryoshka.packer/new-doll "thomas" 68 45)
                 (matryoshka.packer/new-doll "randal" 27 60)
                 (matryoshka.packer/new-doll "april" 39 40)
                 (matryoshka.packer/new-doll "nancy" 23 30)
                 (matryoshka.packer/new-doll "bonnie" 52 10)
                 (matryoshka.packer/new-doll "marc" 11 70)
                 (matryoshka.packer/new-doll "kate" 32 30)
                 (matryoshka.packer/new-doll "tbone" 24 15)
                 (matryoshka.packer/new-doll "tommy" 48 10)
                 (matryoshka.packer/new-doll "uma" 73 40)
                 (matryoshka.packer/new-doll "grumpkin" 42 70)
                 (matryoshka.packer/new-doll "dusty" 43 75)
                 (matryoshka.packer/new-doll "grumpy" 22 80)
                 (matryoshka.packer/new-doll "eddie" 7 20)
                 (matryoshka.packer/new-doll "tory" 18 12)
                 (matryoshka.packer/new-doll "sally" 4 50)
                 (matryoshka.packer/new-doll "babe" 30 10)]]
      (let [result (matryoshka.packer/optimal-pack 400 items)]
        (is (= ["sally" "eddie" "grumpy" "dusty" "grumpkin" "marc" "randal" "puppy" "dorothy" "candice" "anthony" "luke"] (map :name result)))))))

(deftest alice-case
  (testing "A test case based on the name alice"
    (let [items [(matryoshka.packer/new-doll "Alice" 23 92)
                 (matryoshka.packer/new-doll "Barbara" 31 57)
                 (matryoshka.packer/new-doll "Charles" 29 49)
                 (matryoshka.packer/new-doll "Clive" 44 68)
                 (matryoshka.packer/new-doll "Daniel" 53 60)
                 (matryoshka.packer/new-doll "David" 38 43)
                 (matryoshka.packer/new-doll "Elizabeth" 63 67)
                 (matryoshka.packer/new-doll "Emma" 85 84)
                 (matryoshka.packer/new-doll "Fred" 89 87)
                 (matryoshka.packer/new-doll "James" 82 72)]]
      (let [result (matryoshka.packer/optimal-pack 165 items)]
        (is (= ["Alice" "Barbara" "Charles" "Clive" "David"] (sort (map :name result))))))))

(deftest barbara-case
  (testing "A test case based on the name barbara"
    (let [items [(matryoshka.packer/new-doll "Alice" 12 24)
                 (matryoshka.packer/new-doll "Barbara" 7 13)
                 (matryoshka.packer/new-doll "Charles" 11 23)
                 (matryoshka.packer/new-doll "Clive" 8 15)
                 (matryoshka.packer/new-doll "Daniel" 9 16)]]
      (let [result (matryoshka.packer/optimal-pack 26 items)]
        (is (= ["Barbara" "Charles" "Clive"] (sort (map :name result))))))))

(deftest charles-case
  (testing "A test case based on the name charles"
    (let [items [(matryoshka.packer/new-doll "Alice" 56 50)
                 (matryoshka.packer/new-doll "Barbara" 59 50)
                 (matryoshka.packer/new-doll "Charles" 80 64)
                 (matryoshka.packer/new-doll "Clive" 64 46)
                 (matryoshka.packer/new-doll "Daniel" 75 50)
                 (matryoshka.packer/new-doll "David" 17 5)]]
      (let [result (matryoshka.packer/optimal-pack 190 items)]
        (is (= ["Alice" "Barbara" "Daniel"] (sort (map :name result))))))))

(deftest clive-case
  (testing "A test case based on the name clive"
    (let [items [(matryoshka.packer/new-doll "Alice" 31 70)
                 (matryoshka.packer/new-doll "Barbara" 10 20)
                 (matryoshka.packer/new-doll "Charles" 20 39)
                 (matryoshka.packer/new-doll "Clive" 19 37)
                 (matryoshka.packer/new-doll "Daniel" 4 7)
                 (matryoshka.packer/new-doll "David" 3 5)
                 (matryoshka.packer/new-doll "Elizabeth" 6 10)]]
      (let [result (matryoshka.packer/optimal-pack 50 items)]
        (is (= ["Alice" "Clive"] (sort (map :name result))))))))

(deftest daniel-case
  (testing "A test case based on the name daniel"
    (let [items [(matryoshka.packer/new-doll "Alice" 25 350)
                 (matryoshka.packer/new-doll "Barbara" 35 400)
                 (matryoshka.packer/new-doll "Charles" 45 450)
                 (matryoshka.packer/new-doll "Clive" 5 20)
                 (matryoshka.packer/new-doll "Daniel" 25 70)
                 (matryoshka.packer/new-doll "David" 3 8)
                 (matryoshka.packer/new-doll "Elizabeth" 2 5)
                 (matryoshka.packer/new-doll "Emma" 2 5)]]
      (let [result (matryoshka.packer/optimal-pack 104 items)]
        (is (= ["Alice" "Charles" "Clive" "Daniel" "Elizabeth" "Emma"] (sort (map :name result))))))))

(deftest david-case
  (testing "A test case based on the name david"
    (let [items [(matryoshka.packer/new-doll "Alice" 41 442)
                 (matryoshka.packer/new-doll "Barbara" 50 525)
                 (matryoshka.packer/new-doll "Charles" 49 511)
                 (matryoshka.packer/new-doll "Clive" 59 593)
                 (matryoshka.packer/new-doll "Daniel" 55 546)
                 (matryoshka.packer/new-doll "David" 57 564)
                 (matryoshka.packer/new-doll "Elizabeth" 60 617)]]
      (let [result (matryoshka.packer/optimal-pack 170 items)]
        (is (= ["Barbara" "Clive" "Elizabeth"] (sort (map :name result))))))))

(deftest elizabeth-case
  (testing "A test case based on the name elizabeth"
    (let [items [(matryoshka.packer/new-doll "Alice" 70 135)
                 (matryoshka.packer/new-doll "Barbara" 73 139)
                 (matryoshka.packer/new-doll "Charles" 77 149)
                 (matryoshka.packer/new-doll "Clive" 80 150)
                 (matryoshka.packer/new-doll "Daniel" 82 156)
                 (matryoshka.packer/new-doll "David" 87 163)
                 (matryoshka.packer/new-doll "Elizabeth" 90 173)
                 (matryoshka.packer/new-doll "Emma" 94 184)
                 (matryoshka.packer/new-doll "Fred" 98 192)
                 (matryoshka.packer/new-doll "James" 106 201)
                 (matryoshka.packer/new-doll "Jennifer" 110 210)
                 (matryoshka.packer/new-doll "John" 113 214)
                 (matryoshka.packer/new-doll "Joseph" 115 221)
                 (matryoshka.packer/new-doll "Linda" 118 229)
                 (matryoshka.packer/new-doll "Maria" 120 240)]]
      (let [result (matryoshka.packer/optimal-pack 750 items)]
        (is (= ["Alice" "Charles" "Daniel" "Elizabeth" "Emma" "Fred" "Linda" "Maria"] (sort (map :name result))))))))
