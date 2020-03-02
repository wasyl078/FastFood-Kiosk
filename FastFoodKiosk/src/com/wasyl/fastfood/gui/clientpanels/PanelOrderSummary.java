package com.wasyl.fastfood.gui.clientpanels;

import com.wasyl.fastfood.gui.majorGUIcomponents.AbstractPanel;
import com.wasyl.fastfood.gui.majorGUIcomponents.Frame;
import com.wasyl.fastfood.gui.majorGUIcomponents.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelOrderSummary extends AbstractPanel implements ActionListener {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private DefaultListModel<String> orderListModel;
    private JList<String> orderList;
    private JScrollBar scrollBar;
    private JLabel priceLabel;
    private JLabel kcalLabel;
    private DefaultListModel<String> choosenProductAdditionistModel;
    @SuppressWarnings("FieldCanBeLocal")
    private JList<String> choosenProductAdditionList;
    @SuppressWarnings("FieldCanBeLocal")
    private JLabel additionsLabel;
    private int lastChoosenIndexOfProduct = -1;


    public PanelOrderSummary(GUI gui) {
        super(gui, null);
        setBackground(Color.YELLOW);

    }

    //----------------------------TWORZENIE KOMPONENTÓW------------------------------

    @Override
    public void makeComponents() {
        createOrderList();
        createScrollBar();
        createPriceLabel();
        createKcalLabel();
        createAdditionsLabel();
        createChoosenProductAdditionList();
    }

    private void createOrderList() {
        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        orderList.setBounds(ORDER_LIST_POS_X, ORDER_LIST_POS_Y, ORDER_LIST_WIDTH, ORDER_LIST_HEIGHT);
        this.add(orderList);
    }

    private void createScrollBar() {
        scrollBar = new JScrollBar();
        scrollBar.setBounds(SCROLL_BAR_POS_X, SCROLL_BAR_POS_Y, SCROLL_BAR_WIDTH, SCROLL_BAR_HEIGHT);
        this.add(scrollBar);
        scrollBar.addAdjustmentListener(adjustmentEvent ->
                orderList.setBounds(ORDER_LIST_POS_X, -scrollBar.getValue() * 2, ORDER_LIST_WIDTH, ORDER_LIST_HEIGHT));
    }

    private void createPriceLabel() {
        priceLabel = new JLabel("cena");
        priceLabel.setBounds(PRICE_X, PRICE_Y, LABEL_WIDTH, LABEL_HEIGHT);
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        priceLabel.setFont(GUI.BIG_FONT);
        this.add(priceLabel);
    }

    private void createKcalLabel() {
        kcalLabel = new JLabel("kcal");
        kcalLabel.setBounds(KCAL_X, KCAL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        kcalLabel.setFont(GUI.BIG_FONT);
        kcalLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(kcalLabel);
    }

    private void createAdditionsLabel() {
        additionsLabel = new JLabel("DODATKI PRODUKTU:");
        additionsLabel.setBounds(ADDITIONS_LABEL_X, ADDITIONS_LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        additionsLabel.setFont(GUI.BIG_FONT);
        this.add(additionsLabel);

    }

    private void createChoosenProductAdditionList() {
        choosenProductAdditionistModel = new DefaultListModel<>();
        choosenProductAdditionList = new JList<>(choosenProductAdditionistModel);
        choosenProductAdditionList.setBounds(ADDITIONS_LIST_POS_X, ADDITIONS_LIST_POS_Y, ADDITIONS_LIST_WIDTH, ADDITIONS_LIST_HEIGHT);
        this.add(choosenProductAdditionList);
    }

    //----------------------------OBSLUGA LISTY------------------------------

    void updateActualOrderList() {
        if (!getTimer().isRunning())
            startTimer();
        priceLabel.setText("Cena: " + getGui().getClientsMachine().getOrdersValue() + " PLN");
        kcalLabel.setText("Kcal: " + getGui().getClientsMachine().getOrdersKcal());
        choosenProductAdditionistModel.clear();
        lastChoosenIndexOfProduct = -1;
        orderListModel.clear();
        for (String product : getGui().getClientsMachine().getOrderAsStringsArray())
            orderListModel.addElement(product);
    }

    //----------------------------OBSLUGA ZDARZEŃ------------------------------

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == this.getGoBackButton())
            goBackButtonAction();
        else if (source == getTimer())
            timerAction();
    }

    private void goBackButtonAction() {
        getGui().changePanelToPrevious(this.getClass());
        getGui().getClientsMachine().executeOrder();
    }

    private void timerAction() {
        if (orderList != null && orderList.getSelectedIndex() != lastChoosenIndexOfProduct) {
            ArrayList<String> additions = null;
            if (orderList.getSelectedIndex() != -1) {
                additions = getGui().getClientsMachine().getAdditionsOfProductFromOrder(orderList.getSelectedIndex());
                lastChoosenIndexOfProduct = orderList.getSelectedIndex();
                choosenProductAdditionistModel.clear();
            }
            if (additions != null) {
                for (String addition : additions)
                    choosenProductAdditionistModel.addElement(addition);
            }
        }
    }

    //----------------------------PRYWATNE POZYCJE KOMPONENTÓW------------------------------

    private int ORDER_LIST_POS_X;
    private int ORDER_LIST_POS_Y;
    private int ORDER_LIST_WIDTH;
    private int ORDER_LIST_HEIGHT;
    private int ADDITIONS_LIST_POS_X;
    private int ADDITIONS_LIST_POS_Y;
    private int ADDITIONS_LIST_WIDTH;
    private int ADDITIONS_LIST_HEIGHT;
    private int SCROLL_BAR_WIDTH;
    private int SCROLL_BAR_POS_X;
    private int SCROLL_BAR_POS_Y;
    private int SCROLL_BAR_HEIGHT;
    private int PRICE_X;
    private int PRICE_Y;
    private int KCAL_X;
    private int KCAL_Y;
    private int ADDITIONS_LABEL_X;
    private int ADDITIONS_LABEL_Y;
    private int LABEL_WIDTH;
    private int LABEL_HEIGHT;

    //----------------------------USTAWIENIE POZYCJI KOMPONENTÓW------------------------------

    @Override
    public void setDefaultLocations() {
        SCROLL_BAR_WIDTH = 20;
        SCROLL_BAR_HEIGHT = Frame.DEFAULT_HEIGHT - 40;
        SCROLL_BAR_POS_X = 0;
        SCROLL_BAR_POS_Y = 0;
        ORDER_LIST_WIDTH = Frame.DEFAULT_WIDTH / 4;
        ORDER_LIST_HEIGHT = 4000;
        ORDER_LIST_POS_X = SCROLL_BAR_WIDTH;
        ORDER_LIST_POS_Y = 0;
        ADDITIONS_LIST_POS_X = Frame.DEFAULT_WIDTH * 3 / 4;
        ADDITIONS_LIST_POS_Y = Frame.DEFAULT_HEIGHT / 5;
        ADDITIONS_LIST_WIDTH = 250;
        ADDITIONS_LIST_HEIGHT = 540;
        LABEL_WIDTH = 300;
        LABEL_HEIGHT = 50;
        PRICE_X = Frame.DEFAULT_WIDTH / 2 - LABEL_WIDTH / 2;
        //noinspection SuspiciousNameCombination
        PRICE_Y = LABEL_WIDTH;
        KCAL_X = PRICE_X;
        KCAL_Y = PRICE_Y + 2 * LABEL_HEIGHT;
        ADDITIONS_LABEL_X = ADDITIONS_LIST_POS_X;
        ADDITIONS_LABEL_Y = ADDITIONS_LIST_POS_Y - LABEL_HEIGHT;
    }
}
