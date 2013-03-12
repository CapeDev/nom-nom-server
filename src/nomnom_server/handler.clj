(ns nomnom-server.handler
  (:use compojure.core)
  (:require
   [nomnom-server.database :as db]
   [compojure.handler :as handler]
   [compojure.route :as route]
   [ring.middleware.format-params :as format-params]
   [ring.middleware.format-response :as format-response]
   [clojure.data.json :as json]))

(defn with-links [rel link & rest]
  {:_links {rel {:href link}}}
  )

(defn list-restaurants
  []
  (reduce
   #(conj %1 (merge{:name (%2 :name)}
                   (with-links :self (str "/restaurants/" (%2 :_id)))))
   []
   db/database)
  )

(defn list-restaurant-menu
  [id]
  (let [intId (Integer/parseInt id)
        restaurant (first (filter #(= (:_id %) intId) db/database))
        menu (:menu restaurant)]
    {:name (restaurant :name)
     :menu (map (fn [entry] {:name (entry :name) :image (entry :image)}) menu)
     }
    ))

(defn all-restarants []
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body {:restaurants
          (list-restaurants)}})

(defn handle-restaurant
  [id]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (list-restaurant-menu id)}
  )

;; TODO consider hal-builder to simplify this
(defroutes handler
  (context "/restaurants" []
           (defroutes restaurants-routes
             (GET "/" [] (all-restarants))
             (context
              "/:id" [id]
              (defroutes restaurant-routes
                (GET "/" []  (handle-restaurant id)))))
           ))

(def app
  (-> handler
      (format-params/wrap-json-params)
      (format-response/wrap-json-response :formats [:json])))

