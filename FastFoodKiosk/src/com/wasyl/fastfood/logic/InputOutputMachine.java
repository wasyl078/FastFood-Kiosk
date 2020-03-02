package com.wasyl.fastfood.logic;

import com.wasyl.fastfood.data.przechowywanie.IngredientsContainer;
import com.wasyl.fastfood.data.przechowywanie.Warehouse;

import java.io.*;
import java.util.ArrayList;

public class InputOutputMachine {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private Warehouse warehouse;
    private final String DEFAULT_FILE_NAME = "stan_magazynu.ser";
    private double ioExpenses;
    private double ioRevenues;

    public InputOutputMachine(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    //----------------------------OBSŁUGA ZAPISYWANIA Z I DO PLIKU STANU MAGAZYNU------------------------------

    boolean saveWarehouseConditionToFile() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(DEFAULT_FILE_NAME))) {
            for (IngredientsContainer container : warehouse.getContainers())
                os.writeObject(container);
            os.writeObject(new String(ioRevenues + ""));
            os.writeObject(new String(ioExpenses + ""));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Nie znaleziono pliku do zapisania w nim");
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Coś się mocno zepsuło");
            return false;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Coś innego się stało :(");
        }
        return true;
    }

    ArrayList<IngredientsContainer> readWarehouseConditionFromFile() {
        ArrayList<IngredientsContainer> containers = new ArrayList<>();
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(DEFAULT_FILE_NAME))) {
            for (int i = 0; i < warehouse.getContainers().size(); i++)
                containers.add((IngredientsContainer) oi.readObject());
            this.ioRevenues = Double.parseDouble((String) oi.readObject());
            this.ioExpenses = Double.parseDouble((String) oi.readObject());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Nie znaleziono pliku do ocdczytania z niego");
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("odczyt sie zepsuł");
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("błąd 2");
            return null;
        }
        return containers;
    }

    //----------------------------GETTERY, SETTERY------------------------------

    double getIoExpenses() {
        return ioExpenses;
    }

    void setIoExpenses(double ioExpenses) {
        this.ioExpenses = ioExpenses;
    }

    double getIoRevenues() {
        return ioRevenues;
    }

    void setIoRevenues(double ioRevenues) {
        this.ioRevenues = ioRevenues;
    }

}
