(defproject matryoshka "0.1.0"
  :description "An optimal doll packing program"
  :url "https://github.com/trenton42/matryoshka"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot matryoshka.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
