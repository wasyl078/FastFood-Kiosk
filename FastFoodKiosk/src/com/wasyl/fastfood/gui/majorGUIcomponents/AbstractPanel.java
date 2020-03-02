package com.wasyl.fastfood.gui.majorGUIcomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// mój własny abstrakcyjny JPanel, wywołuje tworzenie komponentów i wywołuje ustawianie ich pozycji
public abstract class AbstractPanel extends JPanel implements ActionListener {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    //każdy zawiera wspólny główny obiekt klasy GUI i własny obiekt Timer, i przycisk powrotu
    private GUI gui;
    private Timer timer;
    private JButton goBackButton;
    @SuppressWarnings("FieldCanBeLocal")
    private final int DEFAULT_GO_BACK_BUTTON_POS_X = Frame.DEFAULT_WIDTH/2 - GUI.DEFAULT_BUTTON_WIDTH/4;
    @SuppressWarnings("FieldCanBeLocal")
    private final int DEFAULT_GO_BACK_BUTTON_POS_Y = Frame.DEFAULT_HEIGHT - GUI.DEFAULT_BUTTON_HEIGHT*2;

    //konstruktor wywołuje abstrakcyjne, przesłonięte w klasach metody, i ustawia Layout, i tworzy przycisk powrotu
    public AbstractPanel(GUI gui, Object layout){
        this.gui = gui;
        setLayout((LayoutManager) layout);
        setDefaultLocations();
        makeComponents();
        makeGoBackButton();
        timer = new Timer(10, this);
    }

    //----------------------------OBSŁUGA TIMERA------------------------------
    public void startTimer(){
        timer.start();
    }

    protected void stopTimer(){
        timer.stop();
    }

    protected Timer getTimer(){
        return timer;
    }

    //----------------------------TWORZENIE PRZYCISKU POWROTU------------------------------

    private void makeGoBackButton(){
        goBackButton = new JButton("Powrót");
        goBackButton.setBounds(DEFAULT_GO_BACK_BUTTON_POS_X,DEFAULT_GO_BACK_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH/2, GUI.DEFAULT_BUTTON_HEIGHT/2);
        goBackButton.addActionListener(this);
        goBackButton.setBackground(Color.WHITE);
        this.add(goBackButton);
    }

    //----------------------------GETTERY------------------------------

    protected void makeGoBackButtonInvisible(){
        goBackButton.setVisible(false);
    }

    public abstract void setDefaultLocations();

    public abstract void makeComponents();

    public GUI getGui() {
        return gui;
    }

    protected JButton getGoBackButton(){
        return goBackButton;
    }
}
