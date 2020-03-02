package com.wasyl.fastfood.data.przechowywanie;

import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumIngredientsBase;

import java.util.ArrayList;
import java.util.Random;

///magazyn służy do przechowywania wszystkich pojemników - na skłądniki i na dodatki
public class Warehouse {

    //----------------------------PRZYSŁANIANIE METOD------------------------------

    private ArrayList<IngredientsContainer> containers;

    public Warehouse() {
        //zmienić tylko gdy będzie się chciało mieć losowe wypełnienie magazynu
        //gdy jest na false, to należy odczytać z pliku stan amgazynu
        fulfillRandomWarehouse(false);
    }

    //----------------------------OBSŁUGA POCZĄTKOWA MAGAZYNU------------------------------

    private void fulfillRandomWarehouse(boolean random) {
        Random gen = new Random();
        containers = new ArrayList<>();
        for (EnumIngredientsBase ingredientBase : EnumIngredientsBase.values()) {
            IngredientsContainer buf = new IngredientsContainer(ingredientBase, ingredientBase.getDefaulContainerSize(), ingredientBase.getPriceForRefiling());
            containers.add(buf);
            if (random)
                buf.setFulfilment(gen.nextInt((int) buf.getMaxFulfilment()));
            else buf.setFulfilment(0);
        }
    }

    //----------------------------GETTER------------------------------

    public ArrayList<IngredientsContainer> getContainers() {
        return containers;
    }
}
