package com.wasyl.fastfood.data.przechowywanie;

import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumIngredientsBase;

//pojemnik na skladniki
public class IngredientsContainer extends Container {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private final EnumIngredientsBase containerBase;

    IngredientsContainer(EnumIngredientsBase base, double qty, double priveForTotalRefilling) {
        super(qty, priveForTotalRefilling);
        this.containerBase = base;
    }

    //----------------------------PRZYSŁONIĘCIE METODY------------------------------

    @Override
    public String info() {
        return "Pojemnik na: " + containerBase +
                " (" + getFulfilment() + " g / " + getMaxFulfilment() +
                " g ) [" + getPriceFortotalRefilling() + " PLN za uzup.]";
    }

    //----------------------------GETTER------------------------------

    public EnumIngredientsBase getContainerBase() {
        return containerBase;
    }

}
