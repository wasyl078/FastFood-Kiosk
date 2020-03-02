package com.wasyl.fastfood.data.przechowywanie;

import java.io.Serializable;

//abstrakcyjny pojemnik - narazie na dodatki i skladniki
public abstract class Container implements Serializable {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private double fulfilment;
    final private double maxFulfilment;
    final private double priceFortotalRefilling;

    Container(double fulfilment, double priceFortotalRefilling) {
        this.maxFulfilment = fulfilment;
        this.priceFortotalRefilling = priceFortotalRefilling;
    }

    //----------------------------DEKLARACJA METODY ABSTRAKCYJNEJ------------------------------

    public abstract String info();

    //----------------------------SETTERY, GETTERY------------------------------

    public void changeFulfilment(int difference){
        fulfilment += difference;
    }

    public double getFulfilment() {
        return fulfilment;
    }

    public void setFulfilment(double fulfilment) {
        this.fulfilment = fulfilment;
    }

    public double getPriceFortotalRefilling() {
        return priceFortotalRefilling;
    }

    public double getMaxFulfilment() {
        return maxFulfilment;
    }
}
