(ns nomnom-server.database)

(def database
  [{
    :_id 1
    :name "Kokkari"
    :rating "1"
    :menu [{:_id 1
            :name "baklava"
            :image "baklava.jpg"}
           {:_id 2
            :name "lamb chops"
            :image "lamb_chops.jpg"}
           {:_id 3
            :name "moussaka"
            :image "moussaka.jpg"}]
    
    }
   {
    :_id 2
    :name "Pizza Orgasmica"
    :rating "2"
    :menu [{:_id 1
            :name "chicago deep dish pizza"
            :image "pizza.jpg"}
           {:_id 2
            :name "thin pizza"
            :image "thin_pizza.jpg"}
           {:_id 3
            :name "Peperroni"
            :image "peperroni.jpg"}]
    
    }
   {
    :_id 3
    :name "Palomino's"
    :rating "3"
    :menu [{:_id 1
            :name "soup"
            :image "soup.jpg"}
           {:_id 2
            :name "gorgonzola fries"
            :image "gorgonzola_fries.jpg"}
           {:_id 3
            :name "creme broulet"
            :image "creme_broulet.jpg"}]
    
    }
   ])