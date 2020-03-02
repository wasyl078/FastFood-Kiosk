package com.wasyl.fastfood.logic;

import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumIngredientsBase;
import com.wasyl.fastfood.data.przechowywanie.Container;
import com.wasyl.fastfood.data.przechowywanie.Warehouse;
import com.wasyl.fastfood.data.przechowywanie.IngredientsContainer;

import java.util.ArrayList;

public class AdminsMachine implements AdminCommends {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private Warehouse warehouse;
    private double expenses;
    @SuppressWarnings("FieldCanBeLocal")
    private final String realPassword = "password";
    private ClientsMachine clientsMachine;
    private InputOutputMachine inputOutputMachine;

    public AdminsMachine(Warehouse warehouse, ClientsMachine clientsMachine, InputOutputMachine inputOutputMachine) {
        this.warehouse = warehouse;
        this.clientsMachine = clientsMachine;
        this.inputOutputMachine = inputOutputMachine;
        expenses = 0;
        clientsMachine.setRevenues(0);
    }

    //----------------------------OBSŁUGA MAGAZYNU------------------------------

    //sprawdza poprawność podanego przez użytkownika hasła
    @Override
    public boolean checkPassword(String usersPassword) {
        return usersPassword.equals(realPassword);
    }

    //uzupełnienie całego magazynu
    @Override
    public void refillWarehouse() {
        for (EnumIngredientsBase ingredientsBase : EnumIngredientsBase.values())
            refillContainer(ingredientsBase);
    }

    //uzupełnia pojemnik i aktualizuje wydatki
    @Override
    public void refillContainer(EnumIngredientsBase ingredient) {
        IngredientsContainer container = this.clientsMachine.findContainerForIngredient(ingredient);
        double containerCondition = container.getFulfilment() / container.getMaxFulfilment();
        double singleExpense = (1 - containerCondition) * container.getPriceFortotalRefilling();
        if (singleExpense != 0) {
            expenses += singleExpense;
            container.setFulfilment(container.getMaxFulfilment());
        }
    }

    //metoda wywołująca uzupełnienie pojeminka na podstawie numeru pojemnika
    public void refillContainerNumber(int index) {
        refillContainer(EnumIngredientsBase.values()[index]);
    }

    //----------------------------OBSŁUGA WPISYWANIA / ODCZYTYWANIA Z PLIKU------------------------------

    //zapisywanie stanu magazynu (oraz bilansu zysków / wydatków) do pliku
    public boolean saveWarehouseConditionToFile() {
    inputOutputMachine.setIoRevenues(clientsMachine.getRevenues());
    inputOutputMachine.setIoExpenses(getExpenses());
        return inputOutputMachine.saveWarehouseConditionToFile();
    }

    //odczytywanie stanu magazynu (oraz bilansu zysków / wydatków) z pliku
    public boolean readWarehouseConditionFromFile() {
        ArrayList<IngredientsContainer> containersFromFile = inputOutputMachine.readWarehouseConditionFromFile();
        if (containersFromFile != null) {
            for (int i = 0; i < warehouse.getContainers().size(); i++)
                warehouse.getContainers().get(i).setFulfilment(containersFromFile.get(i).getFulfilment());

            expenses = clientsMachine.roundNumbers(inputOutputMachine.getIoExpenses());
            clientsMachine.setRevenues(clientsMachine.roundNumbers(inputOutputMachine.getIoRevenues()));

            return true;
        } else return false;
    }


    //----------------------------"GETTERY", NAPRAWIANIE LICZB------------------------------

    //zwrócenie wszystkich pojemników jako lista napisów
    @Override
    public ArrayList<String> getContainersAsStrigns() {
        ArrayList<String> containers = new ArrayList<>();
        for (Container container : warehouse.getContainers())
            containers.add(container.info());
        return containers;
    }

    //zwraca wydatki
    public double getExpenses() {
        return clientsMachine.roundNumbers(expenses);
    }

    //zwraca dochód
    public double getProfit() {
        double profit = clientsMachine.getRevenues() - getExpenses();
        return clientsMachine.roundNumbers(profit);
    }
}
