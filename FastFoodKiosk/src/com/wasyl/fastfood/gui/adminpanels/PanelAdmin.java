package com.wasyl.fastfood.gui.adminpanels;

import com.wasyl.fastfood.gui.majorGUIcomponents.AbstractPanel;
import com.wasyl.fastfood.gui.majorGUIcomponents.Frame;
import com.wasyl.fastfood.gui.majorGUIcomponents.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdmin extends AbstractPanel implements ActionListener {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private JList containersList;
    private DefaultListModel<String> containersListModel;
    private JScrollBar scrollBar;
    private JButton fulfilContainerButton;
    private JButton fulfilEverythingButton;
    private JLabel expensesLabel;
    private JLabel revenuesLabel;
    private JLabel profitLabel;
    private JButton readWarehouseConditionFromFileButton;
    private JButton saveWarehouseConditionToFileButton;
    private int time;

    public PanelAdmin(GUI gui) {
        super(gui, null);
        setBackground(Color.CYAN);
        startTimer();
    }

    //----------------------------TWORZENIE KOMPONENTÓW------------------------------

    @Override
    public void makeComponents() {
        createContainersList();
        createScrollBar();
        createFulfilContainerButton();
        createEveryThingButton();
        createExpensesLabel();
        createRevenuesLabel();
        createProfitLabel();
        createReadWarehouseConditionFromFileButton();
        createSaveWarehouseConditionToFileButton();
        updateComponents();
    }

    private void createContainersList() {
        containersListModel = new DefaultListModel<>();
        containersList = new JList<>(containersListModel);
        containersList.setBounds(100, 0, 600, 4000);
        this.add(containersList);
    }

    private void createScrollBar() {
        scrollBar = new JScrollBar();
        scrollBar.setBounds(SCROLL_BAR_POS_X, SCROLL_BAR_POS_Y, SCROLL_BAR_WIDTH, SCROLL_BAR_HEIGHT);
        this.add(scrollBar);
        scrollBar.addAdjustmentListener(adjustmentEvent ->
                containersList.setBounds(100, -scrollBar.getValue() * 9, 600, 4000));
    }

    private void createFulfilContainerButton() {
        fulfilContainerButton = new JButton("Wypełnij zaznaczony pojemnik");
        fulfilContainerButton.setBackground(Color.WHITE);
        fulfilContainerButton.setBounds(FF_SINGLE_BUTTON_POS_X, FF_SINGLE_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        fulfilContainerButton.addActionListener(this);
        this.add(fulfilContainerButton);
    }

    private void createEveryThingButton() {
        fulfilEverythingButton = new JButton("Wypełnij cały magazyn");
        fulfilEverythingButton.setBackground(Color.WHITE);
        fulfilEverythingButton.setBounds(FF_EVERY_BUTTON_POS_X, FF_EVERY_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        fulfilEverythingButton.addActionListener(this);
        this.add(fulfilEverythingButton);

    }

    private void createExpensesLabel() {
        expensesLabel = new JLabel("Wydatki");
        expensesLabel.setBounds(EXPENSES_POS_X, EXPENSES_POS_Y, LABEL_WIDTH, LABEL_HEIGHT);
        expensesLabel.setFont(GUI.BIG_FONT);
        expensesLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(expensesLabel);
    }

    private void createRevenuesLabel() {
        revenuesLabel = new JLabel("Dochód");
        revenuesLabel.setBounds(REVENUES_POS_X, REVENUES_POS_Y, LABEL_WIDTH, LABEL_HEIGHT);
        revenuesLabel.setFont(GUI.BIG_FONT);
        revenuesLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(revenuesLabel);
    }

    private void createProfitLabel() {
        profitLabel = new JLabel("Zysk");
        profitLabel.setBounds(PROFIT_POS_X, PROFIT_POS_Y, LABEL_WIDTH, LABEL_HEIGHT);
        profitLabel.setFont(GUI.BIG_FONT);
        profitLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(profitLabel);
    }

    private void createReadWarehouseConditionFromFileButton() {
        readWarehouseConditionFromFileButton = new JButton("Odczytaj stan magazynu z pliku");
        readWarehouseConditionFromFileButton.setBounds(READ_BUTTON_POS_X,READ_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH,GUI.DEFAULT_BUTTON_HEIGHT);
        readWarehouseConditionFromFileButton.setBackground(Color.WHITE);
        readWarehouseConditionFromFileButton.addActionListener(this);
        this.add(readWarehouseConditionFromFileButton);
    }

    private void createSaveWarehouseConditionToFileButton() {
        saveWarehouseConditionToFileButton = new JButton("Zapisz stan magazynu do pliku");
        saveWarehouseConditionToFileButton.setBounds(SAVE_BUTTON_POS_X,SAVE_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH,GUI.DEFAULT_BUTTON_HEIGHT);
        saveWarehouseConditionToFileButton.setBackground(Color.WHITE);
        saveWarehouseConditionToFileButton.addActionListener(this);
        this.add(saveWarehouseConditionToFileButton);
    }
    //----------------------------OBSLUGA MAGAZYNU Z GUI------------------------------

    //próba wywołania uzupełnienia pojedynczego pojeminka
    private void tryRefilingSingleContainer() {
        int selected = containersList.getSelectedIndex();
        if (selected != -1) {
            getGui().getAdminsMachine().refillContainerNumber(selected);
        } else {
            fulfilContainerButton.setBackground(Color.RED);
            startTimer();
            time = 0;
        }
    }

    //uzupełnienie wszystkich pojemników
    private void tryRefilingEveryContainer() {
        getGui().getAdminsMachine().refillWarehouse();
    }

    //aktualizacja listy pojemników i label'ów
    void updateComponents() {
        containersListModel.clear();
        for (String container : getGui().getAdminsMachine().getContainersAsStrigns()) {
            containersListModel.addElement(container);
        }
        expensesLabel.setText("Wydatki: " + getGui().getAdminsMachine().getExpenses() + " PLN");
        profitLabel.setText("Zysk: " + getGui().getAdminsMachine().getProfit() + " PLN");
        revenuesLabel.setText("Dochód: " + getGui().getClientsMachine().getRevenues() + " PLN");
    }

    //resdet kolorów przycisków
    private void resetButtons() {
        fulfilContainerButton.setBackground(Color.WHITE);
        fulfilEverythingButton.setBackground(Color.WHITE);
        saveWarehouseConditionToFileButton.setText("Zapisz stan magazynu do pliku");
        saveWarehouseConditionToFileButton.setBackground(Color.WHITE);
        readWarehouseConditionFromFileButton.setText("Odczytaj stan magazynu z pliku");
        readWarehouseConditionFromFileButton.setBackground(Color.WHITE);
    }

    //----------------------------OBSLUGA ZDARZEŃ------------------------------

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == this.getGoBackButton()) {
            getGui().changePanelToPrevious(this.getClass());
        } else if (source == fulfilContainerButton) {
            tryRefilingSingleContainer();
            updateComponents();
        } else if (source == fulfilEverythingButton) {
            tryRefilingEveryContainer();
            updateComponents();
        } else if (source == getTimer())
            time++;
        else if(source == saveWarehouseConditionToFileButton)
            saveWarehouseConditionToFileButtonAction();
        else if(source == readWarehouseConditionFromFileButton)
            readWarehouseConditionFromFileButtonAction();

        if (time >= 100) {
            time = 0;
            stopTimer();
            resetButtons();
        }
    }

    private void saveWarehouseConditionToFileButtonAction(){
        startTimer();
        time = 0;
        if(getGui().getAdminsMachine().saveWarehouseConditionToFile()){
            saveWarehouseConditionToFileButton.setBackground(Color.GREEN);
            saveWarehouseConditionToFileButton.setText("ZAPISANO");
        }else {
            saveWarehouseConditionToFileButton.setBackground(Color.RED);
            saveWarehouseConditionToFileButton.setText("NIE UDAŁO SIĘ ZAPISAĆ");
        }
    }

    private void readWarehouseConditionFromFileButtonAction(){
        startTimer();
        time = 0;
        if(getGui().getAdminsMachine().readWarehouseConditionFromFile()){
            readWarehouseConditionFromFileButton.setBackground(Color.GREEN);
            readWarehouseConditionFromFileButton.setText("ODCZYTANO");
            updateComponents();
        }else {
            readWarehouseConditionFromFileButton.setBackground(Color.GREEN);
            readWarehouseConditionFromFileButton.setText("NIE UDAŁO SIĘ ODCZYTAĆ");
        }
    }

    //----------------------------PRYWATNE POZYCJE KOMPONENTÓW------------------------------

    private int SCROLL_BAR_WIDTH;
    private int SCROLL_BAR_POS_X;
    private int SCROLL_BAR_POS_Y;
    private int SCROLL_BAR_HEIGHT;
    private int FF_SINGLE_BUTTON_POS_X;
    private int FF_SINGLE_BUTTON_POS_Y;
    private int FF_EVERY_BUTTON_POS_X;
    private int FF_EVERY_BUTTON_POS_Y;
    private int EXPENSES_POS_X;
    private int EXPENSES_POS_Y;
    private int REVENUES_POS_X;
    private int REVENUES_POS_Y;
    private int PROFIT_POS_X;
    private int PROFIT_POS_Y;
    private int LABEL_WIDTH;
    private int LABEL_HEIGHT;
    private int SAVE_BUTTON_POS_X;
    private int SAVE_BUTTON_POS_Y;
    private int READ_BUTTON_POS_X;
    private int READ_BUTTON_POS_Y;

    //----------------------------USTAWIENIE POZYCJI KOMPONENTÓW------------------------------

    @Override
    public void setDefaultLocations() {
        SCROLL_BAR_WIDTH = 20;
        SCROLL_BAR_HEIGHT = Frame.DEFAULT_HEIGHT - 40;
        SCROLL_BAR_POS_X = 0;
        SCROLL_BAR_POS_Y = 0;
        LABEL_WIDTH = GUI.DEFAULT_BUTTON_WIDTH;
        LABEL_HEIGHT = GUI.DEFAULT_BUTTON_HEIGHT;
        FF_SINGLE_BUTTON_POS_X = Frame.DEFAULT_WIDTH * 3 / 5 - GUI.DEFAULT_BUTTON_WIDTH * 3/ 5;
        FF_SINGLE_BUTTON_POS_Y = Frame.DEFAULT_HEIGHT / 5;
        FF_EVERY_BUTTON_POS_X = FF_SINGLE_BUTTON_POS_X;
        FF_EVERY_BUTTON_POS_Y = FF_SINGLE_BUTTON_POS_Y + GUI.DEFAULT_BUTTON_HEIGHT * 2;
        EXPENSES_POS_X = Frame.DEFAULT_WIDTH * 3 / 5;
        EXPENSES_POS_Y = FF_EVERY_BUTTON_POS_Y + GUI.DEFAULT_BUTTON_HEIGHT * 2;
        REVENUES_POS_X = EXPENSES_POS_X;
        REVENUES_POS_Y = EXPENSES_POS_Y + LABEL_HEIGHT;
        PROFIT_POS_X = EXPENSES_POS_X;
        PROFIT_POS_Y = REVENUES_POS_Y + LABEL_HEIGHT;
        SAVE_BUTTON_POS_X = FF_SINGLE_BUTTON_POS_X +  GUI.DEFAULT_BUTTON_WIDTH * 6/5;
        SAVE_BUTTON_POS_Y =FF_SINGLE_BUTTON_POS_Y;
        READ_BUTTON_POS_X=SAVE_BUTTON_POS_X;
        READ_BUTTON_POS_Y=FF_EVERY_BUTTON_POS_Y;
    }
}
