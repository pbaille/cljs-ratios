(defproject cljs-numbers "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2173"]]

  :plugins [[lein-cljsbuild "1.0.2"]
            [com.cemerick/clojurescript.test "0.3.0"]]

  :source-paths ["src"]
  
  :test-paths ["test"]

  :cljsbuild {:test-commands {"unit" ["phantomjs" :runner
                                      "this.literal_js_was_evaluated=true"
                                      "js-libs/ratio.js"
                                      "target/unit-test.js"]}
              :builds
              {:dev {:source-paths ["src"]
                     :jar true
                     :compiler {:output-to "target/numbers.js"
                                :optimizations :whitespace
                                :source-map "target/numbers.js.map"}}
               :prod {:source-paths ["src"]
                     :jar true
                     :compiler {:output-to "target/numbers-prod.js"
                                :optimizations :advanced
                                :pretty-print false
                                :externs ["externs/ratio_externs.js"]
                                :source-map "target/numbers-prod.js.map"}}
               :test {:source-paths ["test"]
                      :jar true
                      :compiler {:output-to "target/unit-test.js"
                                 :optimizations :whitespace}}}})
