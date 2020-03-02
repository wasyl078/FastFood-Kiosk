package com.wasyl.fastfood.gui.clientpanels;

import com.wasyl.fastfood.gui.majorGUIcomponents.AbstractPanel;
import com.wasyl.fastfood.gui.majorGUIcomponents.Frame;
import com.wasyl.fastfood.gui.majorGUIcomponents.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelBlikPayment extends AbstractPanel implements ActionListener {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private JButton sendBlikCodeButton;
    private JTextField blikField;
    @SuppressWarnings("FieldCanBeLocal")
    private JLabel typeBlikLabel;
    private int time;

    public PanelBlikPayment(GUI gui) {
        super(gui, null);
        setBackground(Color.YELLOW);
    }

    //----------------------------TWORZENIE KOMPONENTÓW------------------------------

    @Override
    public void makeComponents() {
        createTypeBlikLabel();
        createBlikField();
        createSendBlikCodeButton();
    }

    private void createTypeBlikLabel() {
        typeBlikLabel = new JLabel("Podaj kod BLIK");
        typeBlikLabel.setFont(GUI.BIG_FONT);
        typeBlikLabel.setHorizontalAlignment(JLabel.CENTER);
        typeBlikLabel.setBounds(BLIK_LABEL_X, BLIK_LABEL_Y, BLIK_LABEL_WIDTH, BLIK_LABEL_HEIGHT);
        this.add(typeBlikLabel);
    }

    private void createBlikField() {
        blikField = new JTextField();
        blikField.setBounds(BLIK_FIELD_POS_X, BLIK_FIELD_POS_Y, BLIK_FIELD_WIDTH, BLIK_FIELD_HEIGHT);
        blikField.setFont(GUI.BIG_FONT);
        blikField.setHorizontalAlignment(JTextField.CENTER);
        this.add(blikField);

    }

    private void createSendBlikCodeButton() {
        sendBlikCodeButton = new JButton("Potwierdź kod BLIK");
        sendBlikCodeButton.setBounds(BLIK_CODE_BUTTON_POS_X, BLIK_CODE_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        sendBlikCodeButton.addActionListener(this);
        sendBlikCodeButton.setBackground(Color.WHITE);
        this.add(sendBlikCodeButton);
    }

    //----------------------------OBSLUGA ZDARZEŃ------------------------------

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source == sendBlikCodeButton) {
            sendBlikCodeAction();
        } else if (source == getTimer()) {
            time++;
        } else if (source == this.getGoBackButton()) {
            getGui().changePanelToPrevious(this.getClass());
        }
        if (time >= 100) {
            timeAction();
        }
    }

    private void sendBlikCodeAction() {
        if (getGui().getClientsMachine().checkBLIKcode(blikField.getText())) {
            getGui().changePanel(PanelOrderSummary.class);
            getGui().getPanelOrderSummary().updateActualOrderList();
            blikField.setText("");
        } else {
            startTimer();
            sendBlikCodeButton.setBackground(Color.RED);
            time = 0;
            sendBlikCodeButton.setText("NIEPOPRAWNY KOD BLIK");
        }
    }

    private void timeAction() {
        stopTimer();
        sendBlikCodeButton.setBackground(Color.WHITE);
        sendBlikCodeButton.setText("Potwierdź kod BLIK");
    }

    //----------------------------PRYWATNE POZYCJE KOMPONENTÓW------------------------------

    private int BLIK_FIELD_WIDTH;
    private int BLIK_FIELD_HEIGHT;
    private int BLIK_FIELD_POS_X;
    private int BLIK_FIELD_POS_Y;
    private int BLIK_CODE_BUTTON_POS_X;
    private int BLIK_CODE_BUTTON_POS_Y;
    private int BLIK_LABEL_X;
    private int BLIK_LABEL_Y;
    private int BLIK_LABEL_WIDTH;
    private int BLIK_LABEL_HEIGHT;

    //----------------------------USTAWIENIE POZYCJI KOMPONENTÓW------------------------------

    @Override
    public void setDefaultLocations() {
        BLIK_FIELD_WIDTH = 250;
        BLIK_FIELD_HEIGHT = 60;
        BLIK_FIELD_POS_X = Frame.DEFAULT_WIDTH / 2 - BLIK_FIELD_WIDTH / 2;
        BLIK_FIELD_POS_Y = Frame.DEFAULT_HEIGHT / 2 - BLIK_FIELD_HEIGHT * 2;
        BLIK_CODE_BUTTON_POS_X = Frame.DEFAULT_WIDTH / 2 - GUI.DEFAULT_BUTTON_WIDTH / 2;
        BLIK_CODE_BUTTON_POS_Y = Frame.DEFAULT_HEIGHT / 2;
        BLIK_LABEL_WIDTH = 400;
        BLIK_LABEL_HEIGHT = 50;
        BLIK_LABEL_X = Frame.DEFAULT_WIDTH / 2 - BLIK_LABEL_WIDTH / 2;
        BLIK_LABEL_Y = BLIK_FIELD_POS_Y - BLIK_LABEL_HEIGHT;
    }
}
