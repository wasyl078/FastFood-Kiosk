package com.wasyl.fastfood;

import com.wasyl.fastfood.data.przechowywanie.Warehouse;
import com.wasyl.fastfood.gui.majorGUIcomponents.GUI;
import com.wasyl.fastfood.logika.AdminsMachine;
import com.wasyl.fastfood.logika.ClientsMachine;
import com.wasyl.fastfood.logika.InputOutputMachine;

public class Main {
    public static void main(String[] args){
        Warehouse warehouse = new Warehouse();
        InputOutputMachine inputOutputMachine = new InputOutputMachine(warehouse);
        ClientsMachine clientsMachine = new ClientsMachine(warehouse);
        AdminsMachine adminsMachine = new AdminsMachine(warehouse, clientsMachine,inputOutputMachine);
        GUI gui = new GUI(clientsMachine, adminsMachine);
    }
}
