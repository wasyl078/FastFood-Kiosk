package com.wasyl.fastfood.data.produkty.jedzeniaPicia;

import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumManualNames;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.Ingredient;

//burgery mogą być podwójne - podwajane składniki
public class Burger extends Foods {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    final private boolean DOUBLE_BURGER;
    @SuppressWarnings("FieldCanBeLocal")
    final static public double DEFAULT_ADDITIONAL_PRICE = 10;

    public Burger(EnumManualNames manualName, boolean doubleBurger) {
        super(manualName);
        this.DOUBLE_BURGER = doubleBurger;
        if (doubleBurger) {
            doubleIngredients();
            countKcal();
            setPrice(getPrice() + DEFAULT_ADDITIONAL_PRICE);
        }
    }

    //----------------------------PODWAJANIE BURGERA------------------------------

    private void doubleIngredients() {
        for (Ingredient ingredient : getIngredients()) {
            ingredient.setQty(ingredient.getQty() * 2);
            ingredient.setKcal(ingredient.getKcal() * 2);
        }
    }

    //----------------------------PRZYSŁANIANIE METOD------------------------------

    @Override
    public String specificInfo() {
        StringBuilder buffer = new StringBuilder("Burger ");
        if (DOUBLE_BURGER)
            buffer.append("Podwojny ");
        buffer.append(getPreciseName());
        buffer.append(" | cena: ").append(getPrice());
        buffer.append(" | kcal: ").append(getKcal());
        return buffer.toString();
    }

    @Override
    public String basicInfo() {
        if (DOUBLE_BURGER) return "Podwojny " + getPreciseName() + " " + getPrice() + " PLN " + getAdditions();
        else return getPreciseName() + " " + getPrice() + " PLN " + getAdditions();
    }
}
