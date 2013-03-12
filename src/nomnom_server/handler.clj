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

  {:_links {rel link}}
  )

(defn list-restaurants
  []
  (reduce #(conj %1 {:name (%2 :name)} )  []  [])
)
(defn all-restarants []
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body {:restaurants
          [(merge {:name "Kokkari"}
                  (with-links "self" "/restaurants/1"))
           (merge {:name "Pizza Orgasmica"}
                  (with-links "self" "/restaurants/2"))
           (merge {:name "Palominos"}
                  (with-links "self" "/restaurants/3"))
           (merge {:name "Bocadillos"}
                  (with-links "self" "/restaurants/4"))]}})
(defroutes handler
  (context "/restaurants" []
           (defroutes restaurants-routes
             (GET "/" [] (all-restarants))
             (context
              "/:id" [id]
              (defroutes restaurant-routes
                (GET "/" []  {:body {"name" "Kokkari" "dishes" [1 {"picture" "1.jpg"}] }}))))
           ))

(def app
  (-> handler
      (format-params/wrap-json-params)
      (format-response/wrap-json-response :formats [:json])))

