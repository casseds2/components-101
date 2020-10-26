(ns example.core
  (:require [com.stuartsierra.component :as component :refer [Lifecycle]]))

(defprotocol Animal
  (make-noise [_this] "Get the animal to make its noise")
  (eats [_this] "Get the animal to say what it eats"))

(defrecord Lion []
  Animal
  (make-noise [_this]
    (println "The lion goes roooooar"))
  (eats [_this]
    (println "The lion eats pig")))

(defrecord Pig []
  Animal
    (make-noise [_this]
      (println "The pig goes oink"))
    (eats [_this]
      (println "The pig eats slops")))

(defrecord Zoo [lion pig animals]
  Lifecycle
  (start [component]
    (assoc component :animals (vector lion pig)))
  (stop [component]
    (assoc component :lion nil
                     :pig nil
                     :animals nil)))

(def system
  (let [lion (->Lion)
        pig (->Pig)]
    (component/system-map
      :zoo (map->Zoo {:lion lion :pig pig}))))

(defn -main
  [& _args]
  (alter-var-root #'system component/start)
  (let [animals (-> system :zoo :animals)]
    (mapv make-noise animals)
    (mapv eats animals)
    (alter-var-root #'system component/stop)))
