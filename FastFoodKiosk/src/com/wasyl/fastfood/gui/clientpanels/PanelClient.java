package com.wasyl.fastfood.gui.clientpanels;

import com.wasyl.fastfood.gui.majorGUIcomponents.AbstractPanel;
import com.wasyl.fastfood.gui.majorGUIcomponents.ButtonWithPreciseProductName;
import com.wasyl.fastfood.gui.majorGUIcomponents.Frame;
import com.wasyl.fastfood.gui.majorGUIcomponents.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelClient extends AbstractPanel implements ActionListener {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private ArrayList<ButtonWithPreciseProductName> productsButtons;
    private JButton showOnlyBreakfastOfferButton;
    private JButton showEveryProductButton;
    private JButton choosePaymentMethodButton;
    private JButton startNewOrderButton;
    private JButton removeProductButton;
    private DefaultListModel<String> orderListModel;
    private JList<String> orderList;
    private JScrollBar productsScrollBar;
    private JScrollBar orderScrollBar;
    private int time;

    public PanelClient(GUI gui) {
        super(gui, null);
        setBackground(Color.YELLOW);
        swapButtonsVisibilityAndColours();
    }


    //----------------------------TWORZENIE KOMPONENTÓW------------------------------

    @Override
    public void makeComponents() {
        createStartNewOrderButton();
        createProductsButtons();
        createProductsScrollBar();
        createOrderList();
        createOrdersScrollBar();
        createShowOnlyBreakfastOOfferButton();
        createShowEveryProductButton();
        createChoosePaymentMethodButton();
        createRemoveProductButton();
    }

    private void createStartNewOrderButton() {
        startNewOrderButton = new JButton("Rozpocznij nowe zamówienie");
        startNewOrderButton.addActionListener(this);
        startNewOrderButton.setBounds(START_BUTTON_POS_X, START_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH * 2, GUI.DEFAULT_BUTTON_HEIGHT);
        startNewOrderButton.setBackground(Color.WHITE);
        this.add(startNewOrderButton);
        startNewOrderButton.setVisible(false);
    }

    private void createProductsButtons() {
        productsButtons = new ArrayList<>();
        ButtonWithPreciseProductName bufforButton;
        int bufY = 0;
        for (String productName : getGui().getClientsMachine().getAllAvailableProducts()) {
            bufforButton = new ButtonWithPreciseProductName(productName);
            bufforButton.setBounds(PRODUCTS_BUTTONS_POS_X, bufY, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
            bufforButton.addActionListener(this);
            this.add(bufforButton);
            productsButtons.add(bufforButton);
            bufforButton.setBackground(Color.WHITE);
            bufY += GUI.DEFAULT_BUTTON_HEIGHT * 2;
        }
    }

    private void createProductsScrollBar() {
        productsScrollBar = new JScrollBar();
        productsScrollBar.setBounds(PRODUCTS_SCROLL_BAR_POS_X, PRODUCTS_SCROLL_BAR_POS_Y, PRODUCTS_SCROLL_BAR_WIDTH, PRODUCTS_SCROLL_BAR_HEIGHT);
        this.add(productsScrollBar);
        productsScrollBar.addAdjustmentListener(adjustmentEvent -> {
            for (int i = 0; i < productsButtons.size(); i++) {
                ButtonWithPreciseProductName button = productsButtons.get(i);
                button.setBounds(PRODUCTS_BUTTONS_POS_X, i * (GUI.DEFAULT_BUTTON_HEIGHT * 2) - productsScrollBar.getValue() * 32, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
            }
        });
    }

    private void createOrderList() {
        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        orderList.setBounds(LIST_POS_X, LIST_POS_Y, LIST_WIDTH, LIST_HEIGHT);
        this.add(orderList);
    }

    private void createOrdersScrollBar() {
        orderScrollBar = new JScrollBar();
        orderScrollBar.setBounds(ORDER_SCROLL_BAR_POS_X, ORDER_SCROLL_BAR_POS_Y, ORDER_SCROLL_BAR_WIDTH, ORDER_SCROLL_BAR_HEIGHT);
        this.add(orderScrollBar);
        orderScrollBar.addAdjustmentListener(adjustmentEvent ->
                orderList.setBounds(LIST_POS_X, -orderScrollBar.getValue() * 5, LIST_WIDTH, LIST_HEIGHT));
    }

    private void createShowOnlyBreakfastOOfferButton() {
        showOnlyBreakfastOfferButton = new JButton("Zaznacz ofertę śniadaniową");
        showOnlyBreakfastOfferButton.addActionListener(this);
        showOnlyBreakfastOfferButton.setBounds(ONLY_BREAKFAST_BUTTON_POS_X, ONLY_BREAKFAST_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        showOnlyBreakfastOfferButton.setBackground(Color.WHITE);
        this.add(showOnlyBreakfastOfferButton);
    }

    private void createShowEveryProductButton() {
        showEveryProductButton = new JButton("Odznacz ofertę śniadaniową");
        showEveryProductButton.addActionListener(this);
        showEveryProductButton.setBounds(EVERYTHING_BUTTON_POS_X, EVERYTHING_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        showEveryProductButton.setBackground(Color.WHITE);
        this.add(showEveryProductButton);
    }

    private void createChoosePaymentMethodButton() {
        choosePaymentMethodButton = new JButton("Przejdź do płacenia");
        choosePaymentMethodButton.addActionListener(this);
        choosePaymentMethodButton.setBounds(PAYMENT_BUTTON_POS_X, PAYMENT_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        choosePaymentMethodButton.setBackground(Color.WHITE);
        this.add(choosePaymentMethodButton);
    }

    private void createRemoveProductButton() {
        removeProductButton = new JButton("Usuń daną pozycje");
        removeProductButton.addActionListener(this);
        removeProductButton.setBounds(REMOVE_PRODUCT_BUTTON_POS_X, REMOVE_PRODUCT_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        removeProductButton.setBackground(Color.WHITE);
        this.add(removeProductButton);
    }

    //----------------------------OBSLUGA KOMPONENTÓW------------------------------

    //aktualizacja listy - zamówienia
    public void updateActualOrderList() {
        orderListModel.clear();
        if (getGui().getClientsMachine().getOrder() != null)
            for (String product : getGui().getClientsMachine().getOrderAsStringsArray())
                orderListModel.addElement(product);
        orderList.setVisible(orderScrollBar.isVisible());
    }

    //zmiana tego panala: sam przycisk - rozpocznij nowe zamowienie, wszystkie przyciski produktów i lista
    void swapButtonsVisibilityAndColours() {
        boolean setter = startNewOrderButton.isVisible();
        startNewOrderButton.setVisible(!setter);
        for (ButtonWithPreciseProductName button : productsButtons) {
            button.setBackground(Color.WHITE);
            button.setVisible(setter);
        }
        showEveryProductButton.setVisible(setter);
        showOnlyBreakfastOfferButton.setVisible(setter);
        choosePaymentMethodButton.setVisible(setter);
        productsScrollBar.setVisible(setter);
        orderScrollBar.setVisible(setter);
        orderList.setVisible(setter);
        removeProductButton.setVisible(setter);
    }

    //----------------------------OBSLUGA ZDARZEŃ------------------------------

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source == startNewOrderButton)
            startNewOrderButtonAction();
        else if (source == showOnlyBreakfastOfferButton)
            showOnlyBreakfastOfferButtonAction();
        else if (source == choosePaymentMethodButton)
            choosePaymentMethodButtonAction();
        else if (source == getTimer())
            time++;
        else if (source == this.getGoBackButton())
            goBackButtonAction();
        else if (source == showEveryProductButton)
            showEveryProductButtonAction();
        else if (source == removeProductButton)
            removeProductButtonAction();
        checkProductsButtonsActions(source);

        if (time >= 100)
            timeAction();
    }

    private void startNewOrderButtonAction() {
        updateActualOrderList();
        swapButtonsVisibilityAndColours();
        getGui().getClientsMachine().makeNewOrder();
    }

    private void showOnlyBreakfastOfferButtonAction() {
        for (ButtonWithPreciseProductName button : productsButtons) button.setBackground(Color.RED);

        for (Integer iter : getGui().getClientsMachine().getNumbersOfBreakfastOfferProducts())
            productsButtons.get(iter).setBackground(Color.GREEN);
    }

    private void choosePaymentMethodButtonAction() {
        if (getGui().getClientsMachine().getOrder().size() != 0) {
            swapButtonsVisibilityAndColours();
            getGui().changePanel(PanelPaymentChoice.class);
        } else {
            startTimer();
            time = 0;
            choosePaymentMethodButton.setBackground(Color.RED);
            choosePaymentMethodButton.setText("Zamówienie jest puste");
        }
    }

    private void goBackButtonAction() {
        getGui().changePanelToPrevious(this.getClass());
        if (!startNewOrderButton.isVisible())
            swapButtonsVisibilityAndColours();
        getGui().getClientsMachine().removeOrder();
    }

    private void showEveryProductButtonAction() {
        for (ButtonWithPreciseProductName button : productsButtons)
            button.setBackground(Color.WHITE);
    }

    private void timeAction() {
        stopTimer();
        choosePaymentMethodButton.setBackground(Color.WHITE);
        choosePaymentMethodButton.setText("Przejdź do płacenia");
        removeProductButton.setBackground(Color.WHITE);
        removeProductButton.setText("Usuń daną pozycje");
        time = 0;
    }

    private void checkProductsButtonsActions(Object source) {
        for (ButtonWithPreciseProductName button : productsButtons) {
            if (source == button) {
                getGui().changePanel(PanelProductCreation.class);
                getGui().getPanelProductCreation().updateComponents(button.getProductPreciseName());
            }
        }
    }

    private void removeProductButtonAction() {
        if (orderListModel.isEmpty() || orderList.getSelectedIndex() == -1) {
            startTimer();
            time = 0;
            removeProductButton.setBackground(Color.RED);
            removeProductButton.setText("Nie wybrano żadnej pozycji z listy");
        } else {
            getGui().getClientsMachine().removeProductFromOrderOfNumber(orderList.getSelectedIndex());
            updateActualOrderList();
        }
    }

    //----------------------------PRYWATNE POZYCJE KOMPONENTÓW------------------------------

    private int START_BUTTON_POS_X;
    private int START_BUTTON_POS_Y;
    private int PRODUCTS_SCROLL_BAR_WIDTH;
    private int PRODUCTS_SCROLL_BAR_HEIGHT;
    private int PRODUCTS_SCROLL_BAR_POS_X;
    private int PRODUCTS_SCROLL_BAR_POS_Y;
    private int ORDER_SCROLL_BAR_WIDTH;
    private int ORDER_SCROLL_BAR_HEIGHT;
    private int ORDER_SCROLL_BAR_POS_X;
    private int ORDER_SCROLL_BAR_POS_Y;
    private int REMOVE_PRODUCT_BUTTON_POS_X;
    private int REMOVE_PRODUCT_BUTTON_POS_Y;
    private int LIST_POS_X;
    private int LIST_POS_Y;
    private int LIST_WIDTH;
    private int LIST_HEIGHT;
    private int ONLY_BREAKFAST_BUTTON_POS_X;
    private int ONLY_BREAKFAST_BUTTON_POS_Y;
    private int EVERYTHING_BUTTON_POS_X;
    private int EVERYTHING_BUTTON_POS_Y;
    private int PAYMENT_BUTTON_POS_X;
    private int PAYMENT_BUTTON_POS_Y;
    private int PRODUCTS_BUTTONS_POS_X;

    //----------------------------USTAWIENIE POZYCJI KOMPONENTÓW------------------------------

    @Override
    public void setDefaultLocations() {
        START_BUTTON_POS_X = Frame.DEFAULT_WIDTH / 2 - GUI.DEFAULT_BUTTON_WIDTH;
        START_BUTTON_POS_Y = Frame.DEFAULT_HEIGHT / 2 - GUI.DEFAULT_BUTTON_HEIGHT / 2;
        PRODUCTS_SCROLL_BAR_WIDTH = 20;
        PRODUCTS_SCROLL_BAR_HEIGHT = Frame.DEFAULT_HEIGHT - 40;
        PRODUCTS_SCROLL_BAR_POS_X = 0;
        PRODUCTS_SCROLL_BAR_POS_Y = 0;
        ORDER_SCROLL_BAR_WIDTH = 20;
        ORDER_SCROLL_BAR_HEIGHT = Frame.DEFAULT_HEIGHT - 40;
        ORDER_SCROLL_BAR_POS_X = Frame.DEFAULT_WIDTH - ORDER_SCROLL_BAR_WIDTH - 20;
        ORDER_SCROLL_BAR_POS_Y = 0;
        LIST_WIDTH = Frame.DEFAULT_WIDTH / 4;
        LIST_HEIGHT = 4000;
        LIST_POS_X = ORDER_SCROLL_BAR_POS_X - LIST_WIDTH;
        LIST_POS_Y = 0;
        ONLY_BREAKFAST_BUTTON_POS_X = Frame.DEFAULT_WIDTH / 2 - GUI.DEFAULT_BUTTON_WIDTH / 2;
        ONLY_BREAKFAST_BUTTON_POS_Y = GUI.DEFAULT_BUTTON_HEIGHT / 2;
        EVERYTHING_BUTTON_POS_X = ONLY_BREAKFAST_BUTTON_POS_X;
        EVERYTHING_BUTTON_POS_Y = ONLY_BREAKFAST_BUTTON_POS_Y + 2 * GUI.DEFAULT_BUTTON_HEIGHT;
        PAYMENT_BUTTON_POS_X = ONLY_BREAKFAST_BUTTON_POS_X;
        PAYMENT_BUTTON_POS_Y = EVERYTHING_BUTTON_POS_Y + 2 * GUI.DEFAULT_BUTTON_HEIGHT;
        PRODUCTS_BUTTONS_POS_X = Frame.DEFAULT_WIDTH / 13;
        REMOVE_PRODUCT_BUTTON_POS_X = ONLY_BREAKFAST_BUTTON_POS_X;
        REMOVE_PRODUCT_BUTTON_POS_Y = PAYMENT_BUTTON_POS_Y + 2 * GUI.DEFAULT_BUTTON_HEIGHT;
        ;
    }
}
