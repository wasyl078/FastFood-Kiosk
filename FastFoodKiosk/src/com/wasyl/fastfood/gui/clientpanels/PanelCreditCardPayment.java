package com.wasyl.fastfood.gui.clientpanels;

import com.wasyl.fastfood.gui.majorGUIcomponents.AbstractPanel;
import com.wasyl.fastfood.gui.majorGUIcomponents.Frame;
import com.wasyl.fastfood.gui.majorGUIcomponents.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCreditCardPayment extends AbstractPanel implements ActionListener {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private JProgressBar progressBar;
    private JTextField pinField;
    private JButton acceptPinButton;
    @SuppressWarnings("FieldCanBeLocal")
    private JLabel checkingCardLabel;
    private int time;
    private boolean okPin;

    public PanelCreditCardPayment(GUI gui) {
        super(gui, null);
        setBackground(Color.YELLOW);
        time = 0;
        okPin = false;
        makeGoBackButtonInvisible();
    }

    //----------------------------TWORZENIE KOMPONENTÓW------------------------------

    @Override
    public void makeComponents() {
        createProgressBar();
        createCheckingCarLabel();
        createPinField();
        createAcceptPinButton();
    }

    private void createProgressBar() {
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
        progressBar.setBounds(PROGRESS_BAR_POS_X, PROGRESS_BAR_POS_Y, PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);
        this.add(progressBar);
    }

    private void createCheckingCarLabel() {
        checkingCardLabel = new JLabel("Sprawdzanie karty");
        checkingCardLabel.setFont(GUI.BIG_FONT);
        checkingCardLabel.setHorizontalAlignment(JLabel.CENTER);
        checkingCardLabel.setBounds(CHECKING_X, CHECKING_Y, CHECKING_WIDTH, CHECKING_HEIGHT);
        this.add(checkingCardLabel);
    }

    private void createPinField() {
        pinField = new JTextField();
        pinField.setBounds(PIN_FIELD_POS_X,PIN_FIELD_POS_Y,GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        pinField.setFont(GUI.BIG_FONT);
        pinField.setHorizontalAlignment(JTextField.CENTER);
        this.add(pinField);
    }

    private void createAcceptPinButton() {
        acceptPinButton = new JButton("Akceptuj PIN");
        acceptPinButton.setBounds(ACCEPT_PIN_POS_X,ACCEPT_PIN_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        acceptPinButton.setBackground(Color.WHITE);
        acceptPinButton.addActionListener(this);
        this.add(acceptPinButton);
    }


    //----------------------------ROZPOCZĘCIE PROCEDURY PŁACENIA------------------------------

    void startPayment() {
        if (getGui().getClientsMachine().getOrdersValue() > 50) {
            setPinComponentsVisible(true);
            setPinComponentsEnabled(true);
            okPin = false;
        } else {
            startTimer();
            okPin = true;
            setPinComponentsVisible(false);
            setPinComponentsEnabled(false);
        }
    }

    private void checkPinProcedure() {
        startTimer();
        if (getGui().getClientsMachine().checkPinCode(pinField.getText())) {
            acceptPinButton.setText("POPRAWNY PIN");
            acceptPinButton.setBackground(Color.GREEN);
            okPin = true;
        } else {
            okPin = false;
            acceptPinButton.setText("NIEPOPRAWNY PIN");
            acceptPinButton.setBackground(Color.RED);
            setPinComponentsEnabled(false);
        }
    }

    //----------------------------OBSŁUGA KOMPONENTÓW------------------------------

    private void setPinComponentsVisible(boolean visible) {
        pinField.setVisible(visible);
        acceptPinButton.setVisible(visible);
    }

    private void setPinComponentsEnabled(boolean enable) {
        pinField.setEnabled(enable);
        acceptPinButton.setEnabled(enable);
    }

    private void resetComponents() {
        setPinComponentsVisible(true);
        setPinComponentsEnabled(true);
        acceptPinButton.setBackground(Color.WHITE);
        acceptPinButton.setText("Akceptuj PIN");
        pinField.setText("");
        progressBar.setValue(0);
        time = 0;
        okPin = false;
        stopTimer();
    }

    //----------------------------OBSLUGA ZDARZEŃ------------------------------

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == getTimer())
            time++;
        else if (source == acceptPinButton)
            checkPinProcedure();

        if (okPin) progressBar.setValue(Math.min(time, 100));

        if (time > 150 && okPin)
            progressOver150();

        if (time > 150 && !okPin)
            resetComponents();
    }

    private void progressOver150() {
        resetComponents();
        getGui().changePanel(PanelOrderSummary.class);
        getGui().getPanelOrderSummary().updateActualOrderList();
    }

    //----------------------------PRYWATNE POZYCJE KOMPONENTÓW------------------------------

    private int PROGRESS_BAR_POS_X;
    private int PROGRESS_BAR_POS_Y;
    private int PROGRESS_BAR_WIDTH;
    private int PROGRESS_BAR_HEIGHT;
    private int CHECKING_X;
    private int CHECKING_Y;
    private int CHECKING_WIDTH;
    private int CHECKING_HEIGHT;
    private int ACCEPT_PIN_POS_X;
    private int ACCEPT_PIN_POS_Y;
    private int PIN_FIELD_POS_X;
    private int PIN_FIELD_POS_Y;

    //----------------------------USTAWIENIE POZYCJI KOMPONENTÓW------------------------------

    @Override
    public void setDefaultLocations() {
        PROGRESS_BAR_WIDTH = Frame.DEFAULT_WIDTH / 3;
        PROGRESS_BAR_HEIGHT = 80;
        PROGRESS_BAR_POS_X = Frame.DEFAULT_WIDTH / 2 - PROGRESS_BAR_WIDTH / 2;
        PROGRESS_BAR_POS_Y = Frame.DEFAULT_HEIGHT / 2 + PROGRESS_BAR_HEIGHT *2;
        CHECKING_WIDTH = 400;
        CHECKING_HEIGHT = 50;
        CHECKING_X = Frame.DEFAULT_WIDTH / 2 - CHECKING_WIDTH / 2;
        CHECKING_Y = PROGRESS_BAR_POS_Y - CHECKING_HEIGHT * 2;

        ACCEPT_PIN_POS_X = Frame.DEFAULT_WIDTH/2 - GUI.DEFAULT_BUTTON_WIDTH /2;
        ACCEPT_PIN_POS_Y = PROGRESS_BAR_POS_Y - PROGRESS_BAR_HEIGHT*4;
        PIN_FIELD_POS_X = Frame.DEFAULT_WIDTH/2 - GUI.DEFAULT_BUTTON_WIDTH/2;
        PIN_FIELD_POS_Y = ACCEPT_PIN_POS_Y - GUI.DEFAULT_BUTTON_HEIGHT*2;
    }
}
