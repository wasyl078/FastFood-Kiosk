package com.wasyl.fastfood.logika;

import com.wasyl.fastfood.data.produkty.jedzeniaPicia.Product;

public interface ClientImportantCommends {

    void makeNewOrder();
    void executeOrder();
    void removeOrder();
    boolean addProductToOrder(Product newProduct);
    void takeIngredientsForProduct(Product product);
    boolean checkAvailabilityOfProduct(Product product);

}
