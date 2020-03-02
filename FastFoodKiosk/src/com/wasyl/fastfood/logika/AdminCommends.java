package com.wasyl.fastfood.logika;

import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumIngredientsBase;

import java.util.ArrayList;

public interface AdminCommends {
    void refillContainer(EnumIngredientsBase skladnik);
    void refillWarehouse();
    boolean checkPassword(String usersPassword);
    ArrayList<String> getContainersAsStrigns();
}
