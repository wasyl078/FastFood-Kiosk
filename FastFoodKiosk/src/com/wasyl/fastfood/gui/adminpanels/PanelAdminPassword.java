package com.wasyl.fastfood.gui.adminpanels;

import com.wasyl.fastfood.gui.majorGUIcomponents.AbstractPanel;
import com.wasyl.fastfood.gui.majorGUIcomponents.Frame;
import com.wasyl.fastfood.gui.majorGUIcomponents.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelAdminPassword extends AbstractPanel {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private JTextField passwordField;
    private JButton sendPasswordButton;
    @SuppressWarnings("FieldCanBeLocal")
    private JLabel typePasswordLabel;
    private int time;

    public PanelAdminPassword(GUI gui) {
        super(gui, null);
        setBackground(Color.CYAN);
    }

    //----------------------------TWORZENIE KOMPONENTÓW------------------------------

    @Override
    public void makeComponents() {
        createPasswordField();
        createSendPasswordButton();
        createTypePasswordLabel();
    }

    private void createPasswordField(){
        passwordField = new JTextField();
        passwordField.setBounds(PASSWORD_FIELD_POS_X, PASSWORD_FIELD_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        passwordField.setFont(GUI.BIG_FONT);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        this.add(passwordField);
    }

    private void createSendPasswordButton(){
        sendPasswordButton = new JButton("Zatwierdź hasło");
        sendPasswordButton.setBounds(SEND_PASSWORD_POS_X, SEND_PASSWORD_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        sendPasswordButton.addActionListener(this);
        sendPasswordButton.setBackground(Color.WHITE);
        this.add(sendPasswordButton);
    }

    private void createTypePasswordLabel(){

        typePasswordLabel = new JLabel("Wprowadź hasło, by dostać się do panelu Administratora");
        typePasswordLabel.setBounds(LABEL_POS_X, LABEL_POS_Y, LABEL_WIDTH, LABEL_HEIGHT);
        typePasswordLabel.setHorizontalAlignment(JLabel.CENTER);
        typePasswordLabel.setFont(GUI.BIG_FONT);
        this.add(typePasswordLabel);
    }

    //----------------------------OBSŁUGA KOMPONENTÓW------------------------------

    private void makeButtonRed(){
        startTimer();
        time = 0;
        sendPasswordButton.setBackground(Color.RED);
        passwordField.setText("");
        sendPasswordButton.setText("Niepoprawne hasło");
        sendPasswordButton.setEnabled(false);
    }

    private void makeButtonWhite(){
        stopTimer();
        sendPasswordButton.setBackground(Color.WHITE);
        sendPasswordButton.setEnabled(true);
        passwordField.setText("");
        sendPasswordButton.setText("Zatwierdź hasło");
    }

    //----------------------------OBSŁUGA PRZEJŚCIA DO PANELU ADMINISTRATORA------------------------------

    private void tryGoIntoAdminsPanel() {
        String passwordFromTextField = passwordField.getText();
        if (getGui().getAdminsMachine().checkPassword(passwordFromTextField)) {
            getGui().changePanel(PanelAdmin.class);
            getGui().getPanelAdmin().updateComponents();
        } else {
            makeButtonRed();
        }
    }

    //----------------------------OBSLUGA ZDARZEŃ------------------------------

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == sendPasswordButton) {
            tryGoIntoAdminsPanel();
        }
        if (source == getGoBackButton()) {
            getGui().changePanelToPrevious(this.getClass());
        }
        if(source == getTimer()){
            time++;
        }
        if(time > 100){
            makeButtonWhite();
        }
    }

    //----------------------------PRYWATNE POZYCJE KOMPONENTÓW------------------------------

    private int PASSWORD_FIELD_POS_X;
    private int PASSWORD_FIELD_POS_Y;
    private int SEND_PASSWORD_POS_X;
    private int SEND_PASSWORD_POS_Y;
    private int LABEL_POS_X;
    private int LABEL_POS_Y;
    private int LABEL_WIDTH;
    private int LABEL_HEIGHT;

    //----------------------------USTAWIENIE POZYCJI KOMPONENTÓW------------------------------

    @Override
    public void setDefaultLocations() {
        LABEL_WIDTH = Frame.DEFAULT_WIDTH / 2;
        LABEL_HEIGHT = 40;
        LABEL_POS_X = Frame.DEFAULT_WIDTH / 2 - LABEL_WIDTH / 2;
        LABEL_POS_Y = GUI.DEFAULT_BUTTON_HEIGHT  * 2;
        PASSWORD_FIELD_POS_X = Frame.DEFAULT_WIDTH / 2 - GUI.DEFAULT_BUTTON_WIDTH / 2;
        PASSWORD_FIELD_POS_Y = LABEL_POS_Y + GUI.DEFAULT_BUTTON_HEIGHT * 2;
        SEND_PASSWORD_POS_X = PASSWORD_FIELD_POS_X;
        SEND_PASSWORD_POS_Y = PASSWORD_FIELD_POS_Y + GUI.DEFAULT_BUTTON_HEIGHT * 2;
    }
}
