package com.wasyl.fastfood.gui.majorGUIcomponents;

import com.wasyl.fastfood.gui.adminpanels.PanelAdmin;
import com.wasyl.fastfood.gui.adminpanels.PanelAdminPassword;
import com.wasyl.fastfood.gui.clientpanels.*;
import com.wasyl.fastfood.logika.AdminsMachine;
import com.wasyl.fastfood.logika.ClientsMachine;

import javax.swing.*;
import java.awt.*;

//główna klasa odpowidająca za GUI
@SuppressWarnings("FieldCanBeLocal")
public class GUI {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------
    //musi zawierać instancje wszystkich paneli, żeby móc przeskakiwać między nimi
    private Frame frame;
    private JPanel panelSwapper;
    private final ClientsMachine clientsMachine;
    private final AdminsMachine adminsMachine;
    private PanelUserChoice panelUserChoice;
    private PanelAdmin panelAdmin;
    private PanelClient panelClient;
    private PanelProductCreation panelProductCreation;
    private PanelPaymentChoice panelPaymentChoice;
    private PanelBlikPayment panelBlikPayment;
    private PanelCreditCardPayment panelCreditCardPayment;
    private PanelOrderSummary panelOrderSummary;
    private PanelAdminPassword panelAdminPassword;

    public static final int DEFAULT_BUTTON_WIDTH = 300;
    public static final int DEFAULT_BUTTON_HEIGHT = 80;
    public static final Font BIG_FONT = new Font("SansSerif", Font.BOLD, 20);

    //getter tworzy ramkę i wszystkie panele
    public GUI(ClientsMachine clientsMachine, AdminsMachine adminsMachine) {
        this.adminsMachine = adminsMachine;
        this.clientsMachine = clientsMachine;
        createFrame();
        createAllPanels();
        addAllPanelsToPanelSwapper(frame.getContentPane());
        frame.setVisible(true);
    }

    //----------------------------STWORZENIE PANELI------------------------------

    //stworzenie wszystkich paneli
    private void createAllPanels() {
        panelAdmin = new PanelAdmin(this);
        panelClient = new PanelClient(this);
        panelUserChoice = new PanelUserChoice(this);
        panelProductCreation = new PanelProductCreation(this);
        panelPaymentChoice = new PanelPaymentChoice(this);
        panelBlikPayment = new PanelBlikPayment(this);
        panelCreditCardPayment = new PanelCreditCardPayment(this);
        panelOrderSummary = new PanelOrderSummary(this);
        panelAdminPassword = new PanelAdminPassword(this);
        panelSwapper = new JPanel(new CardLayout());
    }

    //dodanie paneli do panelSwapper
    private void addAllPanelsToPanelSwapper(Container panelsContainer) {
        panelSwapper.add(panelUserChoice, "panelUserChoice");
        panelSwapper.add(panelAdmin, "panelAdmin");
        panelSwapper.add(panelClient, "panelClient");
        panelSwapper.add(panelProductCreation, "panelProductCreation");
        panelSwapper.add(panelPaymentChoice, "panelPaymentChoice");
        panelSwapper.add(panelBlikPayment, "panelBlikPayment");
        panelSwapper.add(panelCreditCardPayment, "panelCreditCardPayment");
        panelSwapper.add(panelOrderSummary, "panelOrderSummary");
        panelSwapper.add(panelAdminPassword, "panelAdminPassword");
        panelsContainer.add(panelSwapper, BorderLayout.CENTER);
    }

    //----------------------------OBSŁUGA PANELI------------------------------

    //metoda odpowiadająca za zmianę aktualnie wyświetlnaego panelu na życzenie innego panela
    public void changePanel(Class panel) {
        CardLayout cl = (CardLayout) (panelSwapper.getLayout());
        String actualPanelName;
        if (panel.equals(PanelUserChoice.class))
            actualPanelName = "panelUserChoice";
        else if (panel.equals(PanelClient.class))
            actualPanelName = "panelClient";
        else if (panel.equals(PanelAdmin.class))
            actualPanelName = "panelAdmin";
        else if (panel.equals(PanelProductCreation.class))
            actualPanelName = "panelProductCreation";
        else if (panel.equals(PanelPaymentChoice.class))
            actualPanelName = "panelPaymentChoice";
        else if (panel.equals(PanelOrderSummary.class))
            actualPanelName = "panelOrderSummary";
        else if (panel.equals(PanelCreditCardPayment.class))
            actualPanelName = "panelCreditCardPayment";
        else if (panel.equals(PanelBlikPayment.class))
            actualPanelName = "panelBlikPayment";
        else if (panel.equals(PanelAdminPassword.class))
            actualPanelName = "panelAdminPassword";
        else throw new CouldNotMatchPanelException("nie można było dopasować class do paneli: " + panel);

        cl.show(panelSwapper, actualPanelName);
    }

    //szynka zmiana aktualnego panelu na poprzedni
    public void changePanelToPrevious(Class actualClassPanel) {
        CardLayout cl = (CardLayout) (panelSwapper.getLayout());
        String goToPanel;

        if (actualClassPanel.equals(PanelClient.class))
            goToPanel = "panelUserChoice";
        else if (actualClassPanel.equals(PanelAdmin.class))
            goToPanel = "panelUserChoice";
        else if (actualClassPanel.equals(PanelProductCreation.class))
            goToPanel = "panelClient";
        else if (actualClassPanel.equals(PanelPaymentChoice.class))
            goToPanel = "panelClient";
        else if (actualClassPanel.equals(PanelOrderSummary.class))
            goToPanel = "panelUserChoice";
        else if (actualClassPanel.equals(PanelCreditCardPayment.class))
            goToPanel = "panelPaymentChoice";
        else if (actualClassPanel.equals(PanelBlikPayment.class))
            goToPanel = "panelPaymentChoice";
        else if (actualClassPanel.equals(PanelAdminPassword.class))
            goToPanel = "panelUserChoice";
        else throw new CouldNotMatchPanelException("nie można było dopasować: " + actualClassPanel + " do paneli");

        cl.show(panelSwapper, goToPanel);
    }

    //----------------------------GETTERY------------------------------

    public ClientsMachine getClientsMachine() {
        return clientsMachine;
    }

    public AdminsMachine getAdminsMachine() {
        return adminsMachine;
    }

    private void createFrame() {
        this.frame = new Frame();
    }

    public PanelAdmin getPanelAdmin() {
        return panelAdmin;
    }

    public PanelClient getPanelClient() {
        return panelClient;
    }

    public PanelProductCreation getPanelProductCreation() {
        return panelProductCreation;
    }

    public PanelCreditCardPayment getPanelCreditCardPayment() {
        return panelCreditCardPayment;
    }

    public PanelOrderSummary getPanelOrderSummary() {
        return panelOrderSummary;
    }
}
