package com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients;

//podstawa każdego Produktu
public class Ingredient {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTORY------------------------------

    private EnumIngredientsBase base;
    private int qty;
    private boolean vegetarian;
    private boolean vegan;
    private double kcal;

    public Ingredient(EnumIngredientsBase manualName, int qty) {
        this.base = manualName;
        this.qty = qty;
        this.vegetarian = manualName.isVegetarian();
        this.vegan = manualName.isVegan();
        this.kcal = manualName.getKcalPer100g() * qty / 100;
    }

    public Ingredient(Ingredient otherIngredient) {
        this.base = otherIngredient.base;
        this.qty = otherIngredient.qty;
        this.vegetarian = otherIngredient.vegetarian;
        this.vegan = otherIngredient.vegan;
        this.kcal = otherIngredient.kcal;
    }

    //----------------------------PRZYSŁANIANIE TOSTRING, GETTERY, SETTERY------------------------------

    @Override
    public String toString() {
        return base + ": " + qty + "g" + " [" + kcal + " kcal]";
    }

    public EnumIngredientsBase getBase() {
        return base;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }
}
