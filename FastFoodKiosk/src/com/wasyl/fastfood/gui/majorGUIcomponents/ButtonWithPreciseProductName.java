package com.wasyl.fastfood.gui.majorGUIcomponents;

import javax.swing.*;

//przycisk z polem na napis - nazwę produktu
public class ButtonWithPreciseProductName extends JButton {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private String productPreciseName;

    public ButtonWithPreciseProductName(String productPreciseName) {
        super(productPreciseName);
        this.productPreciseName = productPreciseName;
        this.setFont(GUI.BIG_FONT);
    }

    //----------------------------GETTERY I SETTERY------------------------------

    public String getProductPreciseName() {
        return productPreciseName;
    }
}
