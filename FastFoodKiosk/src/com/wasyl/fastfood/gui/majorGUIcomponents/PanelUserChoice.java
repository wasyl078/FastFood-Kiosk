package com.wasyl.fastfood.gui.majorGUIcomponents;

import com.wasyl.fastfood.gui.adminpanels.PanelAdminPassword;
import com.wasyl.fastfood.gui.clientpanels.PanelClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelUserChoice extends AbstractPanel implements ActionListener {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private JButton clientChoiceButton;
    private JButton adminChoiceButton;

    PanelUserChoice(GUI gui) {
        super(gui, null);
        setBackground(Color.LIGHT_GRAY);
        setBounds(0, 0, 1000, 700);
        makeGoBackButtonInvisible();
    }

    //----------------------------TWORZENIE KOMPONENTÓW------------------------------

    public void makeComponents() {
        createClientChoiceButton();
        createAdminChoiceButton();
    }

    private void createClientChoiceButton() {
        clientChoiceButton = new JButton("PANEL KLIENT FASTFOOD");
        clientChoiceButton.setBounds(CLIENT_BUTTON_POS_X, CLIENT_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        clientChoiceButton.addActionListener(this);
        clientChoiceButton.setBackground(Color.WHITE);
        this.add(clientChoiceButton);
    }

    private void createAdminChoiceButton() {
        adminChoiceButton = new JButton("PANEL ADMIN FASTFOOD");
        adminChoiceButton.setBounds(ADMIN_BUTTON_POS_X, ADMIN_BUTTON_POS_Y, GUI.DEFAULT_BUTTON_WIDTH, GUI.DEFAULT_BUTTON_HEIGHT);
        adminChoiceButton.addActionListener(this);
        adminChoiceButton.setBackground(Color.WHITE);
        this.add(adminChoiceButton);
    }

    //----------------------------OBSLUGA ZDARZEŃ------------------------------

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == clientChoiceButton)
            clientChoiceButtonAction();
        else if (source == adminChoiceButton)
            getGui().changePanel(PanelAdminPassword.class);
        else if (source == this.getGoBackButton())
            getGui().changePanelToPrevious(this.getClass());

    }

    private void clientChoiceButtonAction() {
        getGui().changePanel(PanelClient.class);
        getGui().getPanelClient().updateActualOrderList();
    }

    //----------------------------PRYWATNE POZYCJE KOMPONENTÓW------------------------------

    private int CLIENT_BUTTON_POS_X;
    private int ADMIN_BUTTON_POS_X;
    private int CLIENT_BUTTON_POS_Y;
    private int ADMIN_BUTTON_POS_Y;

    //----------------------------USTAWIENIE POZYCJI KOMPONENTÓW------------------------------

    @Override
    public void setDefaultLocations() {
        CLIENT_BUTTON_POS_X = Frame.DEFAULT_WIDTH / 2 - GUI.DEFAULT_BUTTON_WIDTH / 2;
        ADMIN_BUTTON_POS_X = CLIENT_BUTTON_POS_X;
        CLIENT_BUTTON_POS_Y = Frame.DEFAULT_HEIGHT / 2 - GUI.DEFAULT_BUTTON_HEIGHT;
        ADMIN_BUTTON_POS_Y = Frame.DEFAULT_HEIGHT / 2 + GUI.DEFAULT_BUTTON_HEIGHT;
    }
}
