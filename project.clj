(defproject threejs-figwheel "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Do what the fuck you want to public license"
            :url "www.wtfpl.net"}

  :min-lein-version "2.5.3"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"
                  :exclusions [org.clojure/tools.reader]]]

  :plugins [[lein-figwheel "0.5.0-6"]
            [lein-cljsbuild "1.1.2" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src"]
              :figwheel {:on-jsload "threejs-figwheel.core/render!"}

              :compiler {:main threejs-figwheel.core
                         :asset-path "js/compiled/out"
                         :output-to "resources/public/js/compiled/threejs_figwheel.js"
                         :output-dir "resources/public/js/compiled/out"
                         :foreign-libs [{:file "resources/public/js/three.min.js"
                                         :provides ["three"]}
                                        {:file "resources/public/js/stats.min.js"
                                         :provides ["stats"]}]
                         :source-map-timestamp true }}
             {:id "min"
              :source-paths ["src"]
              :compiler {:output-to "resources/public/js/compiled/threejs_figwheel.js"
                         :main threejs-figwheel.core
                         :optimizations :advanced
                         :foreign-libs [{:file "resources/public/js/three.min.js"
                                         :provides ["three"]}
                                        {:file "resources/public/js/stats.min.js"
                                         :provides ["stats"]}]
                         :externs ["resources/public/js/three.ext.js"]
                         :pretty-print true}}]}

  :figwheel {
             ;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"

             :css-dirs ["resources/public/css"] ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this
             ;; doesn't work for you just run your own server :)
             ;; :ring-handler hello_world.server/handler

             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             ;; :open-file-command "myfile-opener"

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"
             })
