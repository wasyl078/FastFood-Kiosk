package com.wasyl.fastfood.data.produkty.jedzeniaPicia;

import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumManualNames;

//klasa dodatku - tylko jeden skladnik, jest w liście produktów w jedzeniach
public class Addition extends Product {

    //----------------------------KONSTRUKTOR------------------------------

    public Addition(EnumManualNames manualName) {
        super(manualName);
        countKcal();
    }

    //----------------------------PRZYSŁANIANIE METOD------------------------------

    @Override
    public String specificInfo() {
        return "Dodatek: " + getPreciseName() + " | kcal: " + getKcal() + " | cena: " + getPrice() + " PLN";
    }

    @Override
    public String toString() {
        return specificInfo();
    }

    @Override
    public String basicInfo() {
        return "Dod: " + getPreciseName() + " " + getPrice();
    }
}
