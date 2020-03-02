package com.wasyl.fastfood.data.produkty.jedzeniaPicia;

import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumManualNames;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.Ingredient;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumProductType;

import java.util.ArrayList;

//najważniejsza klasa w module DANE, każdy produkt musi mieć jakiś składnik (lista), oraz kilka dodatkowych informacji
public abstract class Product {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private ArrayList<Ingredient> ingredients;
    private EnumProductType enumProductType;
    private EnumManualNames manualName;
    private double kcal;
    private double price;
    private String preciseName;

    Product(EnumManualNames name) {
        this.ingredients = new ArrayList<>();
        for (Ingredient ingredient : name.getIngredients())
            this.ingredients.add(new Ingredient(ingredient));
        this.manualName = name;
        this.price = name.getPrice();
        this.enumProductType = name.getEnumProductType();
        this.preciseName = name.getPreciseName();
    }

    //----------------------------ZLICZANIE KALORII - DLA KAŻDEGO PRODUKTU TAK SAMO------------------------------

    void countKcal() {
        int kcal = 0;
        for (Ingredient ingredient : getIngredients())
            kcal += ingredient.getKcal();
        this.setKcal(kcal);
    }

    //----------------------------DEKLARACJE METOD ABSTRAKCYJNYCH------------------------------

    public abstract String specificInfo();

    public abstract String basicInfo();

    //----------------------------PRZYSŁONIĘCIE TOSTRING, SETTERY, GETTERY, REPAIRPRICE------------------------------

    @Override
    public String toString() {
        return basicInfo();
    }

    private double repairNumber(double number){
        number*=100;
        number = Math.round(number);
        number /=100;
        return number;
    }

    EnumManualNames getManualName() {
        return manualName;
    }

    public double getPrice() {
        return repairNumber(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public double getKcal() {
        return repairNumber(kcal);
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public EnumProductType getEnumProductType() {
        return enumProductType;
    }

    public String getPreciseName() {
        return preciseName;
    }
}
