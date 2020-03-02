package com.wasyl.fastfood.data.produkty.jedzeniaPicia;

import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumIngredientsBase;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumManualNames;

//klasa picia jest bardzo prosta - picia składają się tylko z jednego składnika
// dla uproszczenia: objetosc = ilosc w gramach
public class Drinks extends Product {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private EnumIngredientsBase brand;

    public Drinks(EnumManualNames manualName) {
        super(manualName);
        this.brand = manualName.getIngredients().get(0).getBase();
        countKcal();
    }

    //----------------------------PRZYSŁANIANIE METOD------------------------------

    @Override
    public String specificInfo() {
        StringBuilder buffer = new StringBuilder();
        if (brand.isHotDrink()) buffer.append("Grzaniec ").append(getPreciseName());
        else buffer.append(getPreciseName());
        buffer.append(" | kcal: ").append(getKcal()).append(" | cena: ").append(getPrice()).append(" PLN");
        if (brand.isWithCaffeine())
            buffer.append(" | z kofeina");
        if (brand.getAlcoholPercentage() > 0)
            buffer.append(" | alk. ").append(brand.getAlcoholPercentage()).append(" %");
        return buffer.toString();
    }

    @Override
    public String basicInfo() {
        return getPreciseName() + " " + getPrice() + " PLN";
    }
}
