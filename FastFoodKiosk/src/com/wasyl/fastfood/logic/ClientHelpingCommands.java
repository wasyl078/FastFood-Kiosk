package com.wasyl.fastfood.logic;

import com.wasyl.fastfood.data.produkty.jedzeniaPicia.Product;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumIngredientsBase;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumManualNames;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.Ingredient;
import com.wasyl.fastfood.data.przechowywanie.IngredientsContainer;

import java.util.ArrayList;

public interface ClientHelpingCommands {
    void makeListsOfProductsAndAdditionals();
    void removeProductFromOrder(Product product);
    boolean checkBLIKcode(String BLIK);
    boolean checkPinCode(String PIN);
    boolean checkASvailabilityOfIngredient(Ingredient ingredient);
    IngredientsContainer findContainerForIngredient(EnumIngredientsBase ingredientsBase);
    EnumManualNames adjustProductsNameToManualName(String productsName);
    ArrayList<String> getAdditionsOfProductFromOrder(int index);
    boolean addProductToOrderFromString(String productsPreciseName, boolean better);
    String addAdditionalsToLastProductFromString(String additionsCode);
    ArrayList<String> getOrderAsStringsArray();
    double getProductsPrice(String productsPreciseName);
    double getAdditionalPriceForSpecialSaladMix();
    double getAdditionalPriceForDoubleBurger();
    ArrayList<String> getAllAvailableAdditionalsWithPrices();
}

