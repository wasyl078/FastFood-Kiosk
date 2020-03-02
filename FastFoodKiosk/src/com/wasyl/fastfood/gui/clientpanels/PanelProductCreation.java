package com.wasyl.fastfood.gui.clientpanels;

import com.wasyl.fastfood.gui.majorGUIcomponents.AbstractPanel;
import com.wasyl.fastfood.gui.majorGUIcomponents.Frame;
import com.wasyl.fastfood.gui.majorGUIcomponents.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelProductCreation extends AbstractPanel implements ActionListener {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private JButton addProductButton;
    private JButton addBetterProductButton;
    private JButton addAdditionalsButton;
    private ArrayList<JCheckBox> checkBoxes;
    private DefaultListModel<String> additionsListModel;
    private JList<String> additionsList;
    private JLabel chooseTypeLabel;
    private JLabel actualAdditionsLabel;
    private JLabel availableAdditionsLabel;
    private String productsPreciseName;
    private int buttonsTime;

    public PanelProductCreation(GUI gui) {
        super(gui, null);
        setBackground(Color.YELLOW);
    }

    //----------------------------TWORZENIE KOMPONENTÓW------------------------------

    @Override
    public void makeComponents() {
        createAdditionsList();
        createAddProductButton();
        createAddBetterProductButton();
        createAddAdditionalsButton();
        createCheckBoxes();
        createChooseTypeLabel();
        createActualAdditionsLabel();
        createAvailableAdditionsLabel();
    }

    private void createAdditionsList() {
        additionsListModel = new DefaultListModel<>();
        additionsList = new JList<>(additionsListModel);
        additionsList.setBounds(LIST_POS_X, LIST_POS_Y, LIST_WIDTH, LIST_HEIGHT);
        this.add(additionsList);
    }

    private void createAddProductButton() {

        addProductButton = new JButton("Dodaj ? ");
        addProductButton.addActionListener(this);
        addProductButton.setBounds(ADD_PRODUCT_X_FOODS, ADD_PRODUCT_Y_FOODS, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        this.add(addProductButton);

    }

    private void createAddBetterProductButton() {
        addBetterProductButton = new JButton("ulepszenie");
        addBetterProductButton.addActionListener(this);
        addBetterProductButton.setBounds(ADD_BETTER_PRODUCT_X, ADD_BETTER_PRODUCT_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        this.add(addBetterProductButton);
    }

    private void createAddAdditionalsButton() {
        addAdditionalsButton = new JButton("Dodaj Dodatki");
        addAdditionalsButton.addActionListener(this);
        addAdditionalsButton.setBounds(ADD_ADDITIONALS_X, ADD_ADDITIONALS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        this.add(addAdditionalsButton);
    }

    private void createCheckBoxes() {
        checkBoxes = new ArrayList<>();
        for (String addidtionName : getGui().getClientsMachine().getAllAvailableAdditionalsWithPrices()) {
            JCheckBox newCheckBox = new JCheckBox(addidtionName);
            newCheckBox.setBounds(CHECK_BOX_X, CHECK_BOX_Y + BUFFER_CHECK_BOX_DISTANCE_Y, DEFAULT_CHECK_BOX_WIDTH, DEFAULT_CHECK_BOX_HEIGHT);
            BUFFER_CHECK_BOX_DISTANCE_Y += 50 + DEFAULT_CHECK_BOX_HEIGHT;
            checkBoxes.add(newCheckBox);
            this.add(newCheckBox);
        }
    }

    private void createChooseTypeLabel() {
        chooseTypeLabel = new JLabel("Wybierz typ produktu ");
        chooseTypeLabel.setBounds(CHOOSE_TYPE_LABEL_POS_X, LABEL_POS_Y, LABEL_WIDTH, LABEL_HEIGHT);
        chooseTypeLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseTypeLabel.setFont(GUI.BIG_FONT);
        this.add(chooseTypeLabel);
    }

    private void createActualAdditionsLabel() {
        actualAdditionsLabel = new JLabel("Aktualnie wybrane dodatki");
        actualAdditionsLabel.setBounds(ACTUAL_ADDITIONS_LABEL_POS_X, LABEL_POS_Y, LABEL_WIDTH, LABEL_HEIGHT);
        actualAdditionsLabel.setHorizontalAlignment(JLabel.CENTER);
        actualAdditionsLabel.setFont(GUI.BIG_FONT);
        this.add(actualAdditionsLabel);
    }

    private void createAvailableAdditionsLabel() {
        availableAdditionsLabel = new JLabel("Dostępne dodatki");
        availableAdditionsLabel.setBounds(AVAILABLE_ADDITIONS_LABEL_POS_X, LABEL_POS_Y, LABEL_WIDTH, LABEL_HEIGHT);
        availableAdditionsLabel.setHorizontalAlignment(JLabel.CENTER);
        availableAdditionsLabel.setFont(GUI.BIG_FONT);
        this.add(availableAdditionsLabel);
    }
    //----------------------------OBSLUGA DODAWANIA PRODUKTU DO ZAMÓWIENIA------------------------------

    //próba wywołania dodania poroduktu do zamówienia
    private void tryAddProductToOrder(String productsPreciseName, boolean better) {
        if (getGui().getClientsMachine().addProductToOrderFromString(productsPreciseName, better))
            addingProductAccepted(better);
        else addingProductDenied(better);
    }

    private void addingProductAccepted(boolean better) {
        if (better) {
            addBetterProductButton.setBackground(Color.GREEN);
            addProductButton.setVisible(false);
        } else {
            addProductButton.setBackground(Color.GREEN);
            addBetterProductButton.setVisible(false);
        }
        addBetterProductButton.setEnabled(false);
        addProductButton.setEnabled(false);
        addAdditionalsButton.setEnabled(true);
        setCheckBoxesEnabled(true);
    }

    private void addingProductDenied(boolean better) {
        startTimer();
        buttonsTime = 0;
        if (better) {
            addBetterProductButton.setBackground(Color.RED);
        } else {
            addProductButton.setBackground(Color.RED);
        }
    }

    //----------------------------OBSLUGA DODAWANIA DODATKÓW DO PRODUKTU------------------------------

    //1 dodaj dodatek, 2 juz dodany, 0 nie dodawaj
    //4 - dodano, 5 - nie ma, 6 - nic nie rob
    private void tryAddAdditionals() {
        String additionalCode = createAdditionalCode();
        String errorCode = getGui().getClientsMachine().addAdditionalsToLastProductFromString(additionalCode);
        interpretErrorCode(errorCode);
    }

    private String createAdditionalCode() {
        StringBuilder additionalCode = new StringBuilder();
        for (JCheckBox checkBox : checkBoxes)
            if (checkBox.isSelected())
                additionalCode.append("1");
            else if (!checkBox.isSelected())
                additionalCode.append("0");
            else if (checkBox.getBackground() == Color.GREEN)
                additionalCode.append("2");
        return additionalCode.toString();
    }

    private void interpretErrorCode(String errorCode) {
        for (int i = 0; i < checkBoxes.size(); i++) {
            JCheckBox actualChechBox = checkBoxes.get(i);
            if (errorCode.charAt(i) == '5') {
                actualChechBox.setBackground(Color.RED);
                actualChechBox.setEnabled(false);
                actualChechBox.setSelected(false);
            } else if (errorCode.charAt(i) == '4') {
                actualChechBox.setEnabled(true);
                actualChechBox.setBackground(Color.GREEN);
                actualChechBox.setSelected(false);
            }
        }
    }

//----------------------------OBSŁUGA KOMPONENTÓW------------------------------

    //aktualizacja komponentów
    void updateComponents(String productsPreciseName) {
        resetButtons(true);
        updateBoxesButtons(productsPreciseName);
        updateButtonTexts();
    }

    private void updateBoxesButtons(String productsPreciseName) {
        this.productsPreciseName = productsPreciseName;
        addProductButton.setText(productsPreciseName);
        additionsList.setVisible(true);
        setCheckBoxesVisible(true);
        setCheckBoxesEnabled(false);
        addAdditionalsButton.setVisible(true);
        chooseTypeLabel.setVisible(true);
        actualAdditionsLabel.setVisible(true);
        availableAdditionsLabel.setVisible(true);
        addProductButton.setText(productsPreciseName + " - " + getGui().getClientsMachine().getProductsPrice(productsPreciseName) + " PLN");
    }

    private void updateButtonTexts() {
        if (productsPreciseName.contains("Burger")) {
            addBetterProductButton.setText("Podwójny? + " + getGui().getClientsMachine().getAdditionalPriceForDoubleBurger() + " PLN");
            addProductButton.setBounds(ADD_PRODUCT_X_FOODS, ADD_PRODUCT_Y_FOODS, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        } else if (productsPreciseName.contains("Salatka")) {
            addBetterProductButton.setText("Specjalna? + " + getGui().getClientsMachine().getAdditionalPriceForSpecialSaladMix() + " PLN");
            addProductButton.setBounds(ADD_PRODUCT_X_FOODS, ADD_PRODUCT_Y_FOODS, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        } else {
            setCheckBoxesVisible(false);
            addBetterProductButton.setVisible(false);
            addAdditionalsButton.setVisible(false);
            chooseTypeLabel.setVisible(false);
            actualAdditionsLabel.setVisible(false);
            availableAdditionsLabel.setVisible(false);
            additionsList.setVisible(false);
            setCheckBoxesVisible(false);
            addProductButton.setBounds(ADD_PRODUCT_X_DRINKS, ADD_PRODUCT_Y_DRINKS, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        }
    }

    private void resetButtons(boolean everyButton) {
        if (everyButton)
            addBetterProductButton.setVisible(true);
        addProductButton.setVisible(true);
        addBetterProductButton.setBackground(Color.WHITE);
        addProductButton.setBackground(Color.WHITE);
        addAdditionalsButton.setBackground(Color.WHITE);
        addAdditionalsButton.setEnabled(false);
        addBetterProductButton.setEnabled(true);
        addProductButton.setEnabled(true);
    }

    //---------------------------- ROZBUDOWANE SETTERY------------------------------

    private void resetCheckBoxes() {
        for (JCheckBox checkBox : checkBoxes) {
            checkBox.setSelected(false);
            checkBox.setBackground(Color.WHITE);
        }
    }

    private void setCheckBoxesVisible(boolean visible) {
        for (JCheckBox checkBox : checkBoxes)
            checkBox.setVisible(visible);
    }

    private void setCheckBoxesEnabled(boolean enabled) {
        for (JCheckBox checkBox : checkBoxes)
            checkBox.setEnabled(enabled);
    }

    //----------------------------OBSLUGA ZDARZEŃ------------------------------

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source == addProductButton || source == addBetterProductButton)
            tryAddProductToOrder(productsPreciseName, source == addBetterProductButton);
        else if (source == this.getGoBackButton())
            goBackButtonAction();
        else if (source == addAdditionalsButton)
            addAdditionalsButtonAction();
        else if (source == getTimer())
            buttonsTime++;

        if (buttonsTime > 100) {
            timeAction();
        }
    }

    private void goBackButtonAction() {
        getGui().changePanelToPrevious(this.getClass());
        resetCheckBoxes();
        setCheckBoxesEnabled(true);
        additionsListModel.clear();
        getGui().getPanelClient().updateActualOrderList();
    }

    private void addAdditionalsButtonAction() {
        tryAddAdditionals();
        additionsListModel.clear();
        for (String product : getGui().getClientsMachine().getLastProductAdditionals()) {
            additionsListModel.addElement(product);
        }
    }

    private void timeAction() {
        resetButtons(false);
        stopTimer();
        buttonsTime = 0;
    }


    //----------------------------PRYWATNE POZYCJE KOMPONENTÓW------------------------------

    private int ADD_PRODUCT_X_FOODS;
    private int ADD_PRODUCT_X_DRINKS;
    private int ADD_PRODUCT_Y_FOODS;
    private int ADD_PRODUCT_Y_DRINKS;
    private int ADD_BETTER_PRODUCT_X;
    private int ADD_BETTER_PRODUCT_Y;
    private int ADD_ADDITIONALS_X;
    private int ADD_ADDITIONALS_Y;
    private int CHECK_BOX_X;
    private int CHECK_BOX_Y;
    private int BUFFER_CHECK_BOX_DISTANCE_Y;
    private int DEFAULT_CHECK_BOX_WIDTH;
    private int DEFAULT_CHECK_BOX_HEIGHT;
    private int LIST_POS_X;
    private int LIST_POS_Y;
    private int LIST_WIDTH;
    private int LIST_HEIGHT;
    private int LABEL_WIDTH;
    private int LABEL_HEIGHT;
    private int LABEL_POS_Y;
    private int ACTUAL_ADDITIONS_LABEL_POS_X;
    private int CHOOSE_TYPE_LABEL_POS_X;
    private int AVAILABLE_ADDITIONS_LABEL_POS_X;

    //----------------------------USTAWIENIE POZYCJI KOMPONENTÓW------------------------------

    @Override
    public void setDefaultLocations() {
        int bufferX = Frame.DEFAULT_WIDTH / 6;
        ADD_PRODUCT_X_FOODS = bufferX;
        ADD_PRODUCT_X_DRINKS = Frame.DEFAULT_WIDTH / 2 - GUI.DEFAULT_BUTTON_WIDTH / 2;
        ADD_PRODUCT_Y_FOODS = Frame.DEFAULT_HEIGHT / 6;
        ADD_PRODUCT_Y_DRINKS = ADD_PRODUCT_Y_FOODS;
        ADD_BETTER_PRODUCT_X = bufferX;
        ADD_BETTER_PRODUCT_Y = ADD_PRODUCT_Y_FOODS + GUI.DEFAULT_BUTTON_HEIGHT * 2;
        ADD_ADDITIONALS_X = bufferX;
        ADD_ADDITIONALS_Y = ADD_BETTER_PRODUCT_Y + GUI.DEFAULT_BUTTON_HEIGHT * 2;
        CHECK_BOX_X = Frame.DEFAULT_WIDTH * 2 / 3;
        CHECK_BOX_Y = ADD_PRODUCT_Y_FOODS;
        DEFAULT_CHECK_BOX_HEIGHT = 35;
        BUFFER_CHECK_BOX_DISTANCE_Y = -DEFAULT_CHECK_BOX_HEIGHT;
        DEFAULT_CHECK_BOX_WIDTH = 300;
        LIST_WIDTH = DEFAULT_CHECK_BOX_WIDTH;
        LIST_HEIGHT = 540;
        LIST_POS_X = Frame.DEFAULT_WIDTH / 2 - LIST_WIDTH / 2;
        LIST_POS_Y = CHECK_BOX_Y - DEFAULT_CHECK_BOX_HEIGHT;
        LABEL_WIDTH = LIST_WIDTH;
        LABEL_HEIGHT = 50;
        LABEL_POS_Y = ADD_PRODUCT_Y_FOODS - LABEL_HEIGHT * 2;
        ACTUAL_ADDITIONS_LABEL_POS_X = LIST_POS_X;
        CHOOSE_TYPE_LABEL_POS_X = ADD_PRODUCT_X_FOODS;
        AVAILABLE_ADDITIONS_LABEL_POS_X = CHECK_BOX_X;
    }
}
