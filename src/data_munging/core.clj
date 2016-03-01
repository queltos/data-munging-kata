(ns data-munging.core)

(require '[clojure.string :as str])

(defn read-file []
 (str/split
   (slurp "weather.dat")
   #"\n")
)

(defn split-line [line]
  (next (take 4 (str/split line #" +")))
)

(defn parse-item [item]
  (Integer/parseInt (str/replace item #"\*" ""))
)

(defn parse-line [line]
  (map
    parse-item
    (split-line line)
    )
)

(defn calc-spread [values]
  (let [mxt (first (next values))
        mnt (first (next (next values)))]
    (- mxt mnt)
  )
)

(defn retain-max-tempspread [max curr]
  (let [max-spread (calc-spread max)
        curr-spread (calc-spread curr)]
    (if (< max-spread curr-spread) curr max))
)

(defn find-max-temp-spread []
  (reduce retain-max-tempspread (list 0 0 0) (map parse-line (read-file)))
)
