package com.wasyl.fastfood.data.produkty.jedzeniaPicia;

import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumManualNames;

import java.util.ArrayList;

//klasa odpowiedzialna za produkty jedzeniowe - burgery, salatki
public abstract class Foods extends Product {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private ArrayList<Addition> additions;

    Foods(EnumManualNames manualName) {
        super(manualName);
        this.additions = new ArrayList<>();
        countKcal();
    }

    //----------------------------GETTERY, SETTERY------------------------------

    public void addAdditions(ArrayList<Addition> newAddidtions){
        this.additions.addAll(newAddidtions);
    }

    public ArrayList<Addition> getAdditions() {
        return additions;
    }

    public ArrayList<String> getAdditionsAsStrings(){
        ArrayList<String> names = new ArrayList<>();
        for(Addition addition:getAdditions())
            names.add(addition.getPreciseName());
        return names;
    }
}
