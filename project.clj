(defproject cljs-ratios "0.2.2-SNAPSHOT"
  :description "rational numbers in clojurescript, with the help of Ratio.js"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2173"]]
  
  :plugins [[lein-cljsbuild "1.0.2"]
            [com.cemerick/clojurescript.test "0.3.0"]]
  
  :source-paths ["src"]
  
  :resource-paths ["target"]
  
  :test-paths ["test"]

  :cljsbuild {:test-commands {"unit" ["phantomjs" :runner
                                      "this.literal_js_was_evaluated=true"
                                      "src/ratio.js"
                                      "target/unit-test.js"]}
              :builds
              {:dev {:source-paths ["src"]
                     :jar true
                     :compiler {:output-to "target/cljs-ratios.js"
                                :optimizations :whitespace
                                :preamble ["ratio.js"]
                                :source-map "target/cljs-ratios.js.map"}}
               :prod {:source-paths ["src"]
                     :jar true
                     :compiler {:output-to "target/cljs-ratios-prod.js"
                                :optimizations :advanced
                                :pretty-print false
                                :preamble ^:replace ["ratio.js"]
                                :externs ["externs/ratio_externs.js"]
                                :source-map "target/cljs-ratios-prod.js.map"}}
               :test {:source-paths ["test"]
                      :jar true
                      :compiler {:output-to "target/unit-test.js"
                                 :optimizations :whitespace}}}})
