package com.wasyl.fastfood.gui.majorGUIcomponents;


import javax.swing.*;

//ramka / obramówka / okienko
public class Frame extends JFrame {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    //na podstawie tych zmiennych ustawiane są prawie wszystkie komponenty
    public final static int DEFAULT_WIDTH = 1600;
    public final static int DEFAULT_HEIGHT = 900;

    public Frame() {

        setFocusable(true);
        requestFocusInWindow();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Fastfood - MACIEJ WASILEWSKI");
        setResizable(false);
        setVisible(true);
    }
}

