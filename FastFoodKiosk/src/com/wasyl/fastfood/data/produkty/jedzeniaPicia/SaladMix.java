package com.wasyl.fastfood.data.produkty.jedzeniaPicia;

import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumIngredientsBase;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumManualNames;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.Ingredient;

//salatka moze miec dwa razy więcej sałaty i oliwę, czyli być specjalną salatką
@SuppressWarnings("FieldCanBeLocal")
public class SaladMix extends Foods {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    final private boolean specjalSaladMix;
    final private int DEFAULT_OLIVE_VOLUME = 25;
    final static public  int DEFAULT_ADDITIONAL_PRICE = 5;

    public SaladMix(EnumManualNames manualName, boolean specjalSaladMix) {
        super(manualName);
        this.specjalSaladMix = specjalSaladMix;
        if (specjalSaladMix) {
            makeSaladSpecial();
            setPrice(getPrice() + DEFAULT_ADDITIONAL_PRICE);
            countKcal();
        }
    }

    //----------------------------SPECJALIZOWANIE SAŁATKI------------------------------

    private void makeSaladSpecial() {
        getIngredients().add(new Ingredient(EnumIngredientsBase.Oliwa, DEFAULT_OLIVE_VOLUME));
        for (Ingredient ingredient : getIngredients())
            if (ingredient.getBase().equals(EnumIngredientsBase.Salata)) {
                ingredient.setQty(ingredient.getQty() * 2);
                ingredient.setKcal(ingredient.getKcal() * 2);
            }
    }

    //----------------------------PRZYSŁANIANIE METOD------------------------------

    @Override
    public String specificInfo() {
        StringBuilder buffer = new StringBuilder("Salatka ");
        if (specjalSaladMix)
            buffer.append(" Specjalna ");
        buffer.append(getPreciseName());
        buffer.append(" | cena: ").append(getPrice()).append(" | kcal: ").append(getKcal());
        return buffer.toString();
    }

    @Override
    public String basicInfo() {
        if (specjalSaladMix) return "Specjalna " + getPreciseName() + " " + getPrice() + " PLN " + getAdditions();
        else return getPreciseName() + " " + getPrice() + " PLN " + getAdditions();
    }
}
