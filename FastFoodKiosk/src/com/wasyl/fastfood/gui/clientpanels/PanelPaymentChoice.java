package com.wasyl.fastfood.gui.clientpanels;

import com.wasyl.fastfood.gui.majorGUIcomponents.AbstractPanel;
import com.wasyl.fastfood.gui.majorGUIcomponents.Frame;
import com.wasyl.fastfood.gui.majorGUIcomponents.GUI;
import com.wasyl.fastfood.gui.majorGUIcomponents.PanelUserChoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPaymentChoice extends AbstractPanel implements ActionListener {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private JButton payAtTheCheckoutButton;
    private JButton payWithCardButton;
    private JButton payWithBlikButton;
    private JButton cancelWholeOrderButton;

    public PanelPaymentChoice(GUI gui) {
        super(gui, null);
        setBackground(Color.YELLOW);
        setBounds(0, 0, 1000, 700);
    }

    //----------------------------TWORZENIE KOMPONENTÓW------------------------------

    @Override
    public void makeComponents() {
        createPayAtTheCheckoutButton();
        createPayWithBlikButton();
        createPayWithCardButton();
        createCcancelWholeOrderButton();

    }

    private void createPayAtTheCheckoutButton() {
        payAtTheCheckoutButton = new JButton("Przy odbiorze zamówienia");
        payAtTheCheckoutButton.addActionListener(this);
        payAtTheCheckoutButton.setBounds(PAY_AT_CHECKOUT_POS_X, PAY_AT_CHECKOUT_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        payAtTheCheckoutButton.setBackground(Color.WHITE);
        this.add(payAtTheCheckoutButton);
    }

    private void createPayWithBlikButton() {
        payWithBlikButton = new JButton("BLIK");
        payWithBlikButton.addActionListener(this);
        payWithBlikButton.setBounds(PAY_WITH_BLIK_POS_X, PAY_WITH_BLIK_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        payWithBlikButton.setBackground(Color.WHITE);
        this.add(payWithBlikButton);
    }

    private void createPayWithCardButton() {
        payWithCardButton = new JButton("Karta płatnicza");
        payWithCardButton.addActionListener(this);
        payWithCardButton.setBounds(PAY_WITH_CARD_POS_X, PAY_WITH_CARD_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        payWithCardButton.setBackground(Color.WHITE);
        this.add(payWithCardButton);
    }

    private void createCcancelWholeOrderButton() {
        cancelWholeOrderButton = new JButton("Anuluj calą transakcję");
        cancelWholeOrderButton.addActionListener(this);
        cancelWholeOrderButton.setBounds(CANCEL_WHOLE_ORDER_POS_X, CANCEL_WHOLE_ORDER_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        cancelWholeOrderButton.setBackground(Color.WHITE);
        this.add(cancelWholeOrderButton);
    }

    //----------------------------OBSLUGA ZDARZEŃ------------------------------

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source == payAtTheCheckoutButton)
            payAtTheCheckoutButtonAction();
        else if (source == payWithBlikButton)
            payWithBlikButtonAction();
        else if (source == payWithCardButton)
            payWithCardButtonAction();
        else if (source == cancelWholeOrderButton)
            cancelWholeOrderButtonAction();
        else if (source == this.getGoBackButton())
            goBackButtonAction();

    }

    private void payAtTheCheckoutButtonAction() {
        getGui().changePanel(PanelOrderSummary.class);
        getGui().getPanelOrderSummary().updateActualOrderList();
    }

    private void payWithBlikButtonAction() {
        getGui().changePanel(PanelBlikPayment.class);
    }

    private void payWithCardButtonAction() {
        getGui().changePanel(PanelCreditCardPayment.class);
        getGui().getPanelCreditCardPayment().startPayment();
    }

    private void cancelWholeOrderButtonAction() {
        getGui().changePanel(PanelUserChoice.class);
        getGui().getClientsMachine().removeOrder();
    }

    private void goBackButtonAction() {
        getGui().changePanelToPrevious(this.getClass());
        getGui().getPanelClient().swapButtonsVisibilityAndColours();
    }

    //----------------------------PRYWATNE POZYCJE KOMPONENTÓW------------------------------

    private int PAY_AT_CHECKOUT_POS_X;
    private int PAY_AT_CHECKOUT_POS_Y;
    private int PAY_WITH_CARD_POS_X;
    private int PAY_WITH_CARD_POS_Y;
    private int PAY_WITH_BLIK_POS_X;
    private int PAY_WITH_BLIK_POS_Y;
    private int CANCEL_WHOLE_ORDER_POS_X;
    private int CANCEL_WHOLE_ORDER_POS_Y;


    //----------------------------USTAWIENIE POZYCJI KOMPONENTÓW------------------------------

    @Override
    public void setDefaultLocations() {
        int bufferX = Frame.DEFAULT_WIDTH / 2 - GUI.DEFAULT_BUTTON_WIDTH / 2;
        final int DEFAULT_DISTANCE_Y = 70;
        PAY_AT_CHECKOUT_POS_X = bufferX;
        PAY_AT_CHECKOUT_POS_Y = DEFAULT_DISTANCE_Y;
        PAY_WITH_CARD_POS_X = bufferX;
        PAY_WITH_CARD_POS_Y = PAY_AT_CHECKOUT_POS_Y + GUI.DEFAULT_BUTTON_HEIGHT + DEFAULT_DISTANCE_Y;
        PAY_WITH_BLIK_POS_X = bufferX;
        PAY_WITH_BLIK_POS_Y = PAY_WITH_CARD_POS_Y + GUI.DEFAULT_BUTTON_HEIGHT + DEFAULT_DISTANCE_Y;
        CANCEL_WHOLE_ORDER_POS_X = bufferX;
        CANCEL_WHOLE_ORDER_POS_Y = PAY_WITH_BLIK_POS_Y + GUI.DEFAULT_BUTTON_HEIGHT + DEFAULT_DISTANCE_Y;
    }
}
