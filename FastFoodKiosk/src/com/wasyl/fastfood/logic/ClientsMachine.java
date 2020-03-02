package com.wasyl.fastfood.logic;

import com.wasyl.fastfood.data.produkty.jedzeniaPicia.*;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumIngredientsBase;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumManualNames;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.EnumProductType;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients.Ingredient;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.exceptions.CouldNotMatchContainerException;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.exceptions.CouldNotMatchProductException;
import com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.exceptions.UnsupportedProductTypeException;
import com.wasyl.fastfood.data.przechowywanie.Warehouse;
import com.wasyl.fastfood.data.przechowywanie.IngredientsContainer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class ClientsMachine implements ClientImportantCommends, ClientHelpingCommands {

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private Warehouse warehouse;
    private double revenues;
    private LinkedList<Product> order;
    private double ordersValue;
    private double ordersKcal;
    private ArrayList<String> allAvailableProducts;
    private ArrayList<String> allAvailableAdditionals;
    private ArrayList<Integer> numbersOfBreakfastOfferProducts;
    @SuppressWarnings("FieldCanBeLocal")
    private final int MAX_ADDITIONS_PER_PRODUCT_LIMIT = 30;

    public ClientsMachine(Warehouse warehouse) {
        this.warehouse = warehouse;
        makeListsOfProductsAndAdditionals();
    }

    //----------------------------PODCZAS TWORZNIE OBIEKTU------------------------------

    //ustawia listy produktów, dodatków, numerów produktów oferty śniadaniowej
    @Override
    public void makeListsOfProductsAndAdditionals() {
        allAvailableProducts = new ArrayList<>();
        allAvailableAdditionals = new ArrayList<>();
        numbersOfBreakfastOfferProducts = new ArrayList<>();
        Integer bufferInteger = 0;
        for (EnumManualNames names : EnumManualNames.values()) {
            if (names.getEnumProductType().equals(EnumProductType.ADDITION)) {
                allAvailableAdditionals.add(names.getPreciseName());
            } else {
                if (names.isBreakfastOffer())
                    numbersOfBreakfastOfferProducts.add(bufferInteger);
                bufferInteger++;
                allAvailableProducts.add(names.getPreciseName());
            }
        }
    }

    //----------------------------OBSŁUGA ZAMÓWIENIA------------------------------

    //rozpoczyna nowe zamówienie
    @Override
    public void makeNewOrder() {
        order = new LinkedList<>();
        ordersKcal = 0;
        ordersValue = 0;
    }

    //wykonuje zamówienie
    @Override
    public void executeOrder() {
        revenues += ordersValue;
        order = null;
    }

    //usuwa całe zamówienie
    @Override
    public void removeOrder() {
        if (order != null) {
            for (Product product : order)
                removeProductFromOrder(product);
        }

        order = null;
    }

    //dodaje dany produkt do zamówienia
    @Override
    public boolean addProductToOrder(Product newProduct) {
        if (checkAvailabilityOfProduct(newProduct)) {
            takeIngredientsForProduct(newProduct);
            ordersValue += newProduct.getPrice();
            ordersKcal += newProduct.getKcal();
            order.add(newProduct);
            return true;
        } else return false;
    }


    //zabiera odpowiednią ilość składników na dany produkt z magazynu
    @SuppressWarnings("ControlFlowStatementWithoutBraces")
    @Override
    public void takeIngredientsForProduct(Product product) {
        for (Ingredient ingredient : product.getIngredients())
            Objects.requireNonNull(findContainerForIngredient(ingredient.getBase())).changeFulfilment(-ingredient.getQty());
    }

    //sprawdza czy dostępne są wszystkie składniki na produkt
    @Override
    public boolean checkAvailabilityOfProduct(Product product) {
        for (Ingredient ingredient : product.getIngredients())
            if (!checkASvailabilityOfIngredient(ingredient))
                return false;
        return true;
    }

    //----------------------------METODY POMAGAJĄCE W OBSŁUDZE ZAMÓWIENIA------------------------------

    //usuwa produkt z zamówienia
    @Override
    public void removeProductFromOrder(Product product) {
        for (Ingredient ingredient : product.getIngredients())
            Objects.requireNonNull(findContainerForIngredient(ingredient.getBase())).changeFulfilment(ingredient.getQty());
        ordersKcal -= product.getKcal();
        ordersValue -= product.getPrice();
        order.remove(product);
        if (product.getEnumProductType().equals(EnumProductType.BURGER) || product.getEnumProductType().equals(EnumProductType.SALAD_MIX))
            for (Addition addition : ((Foods) product).getAdditions())
                Objects.requireNonNull(findContainerForIngredient(addition.getIngredients().get(0).getBase())).changeFulfilment(addition.getIngredients().get(0).getQty());
    }

    //sprawdza poprawność kodu BLIK
    @Override
    public boolean checkBLIKcode(String BLIK) {
        int code;
        try {
            code = Integer.parseInt(BLIK);
        } catch (NumberFormatException e) {
            return false;
        }
        return code < 1000000 && 99999 < code;
    }

    //sprawdzenie kodu PIN
    public boolean checkPinCode(String PIN) {
        int code;
        try {
            code = Integer.parseInt(PIN);
        } catch (NumberFormatException e) {
            return false;
        }
        return code < 10000 && 999< code;
    }

    //sprawdza czy dostępny jest składnik
    @Override
    public boolean checkASvailabilityOfIngredient(Ingredient ingredient) {
        return Objects.requireNonNull(findContainerForIngredient(ingredient.getBase())).getFulfilment() > ingredient.getQty();
    }

    //znajduje pojemnik na daną bazę składnika
    @Override
    public IngredientsContainer findContainerForIngredient(EnumIngredientsBase ingredientsBase) {
        for (IngredientsContainer poj : warehouse.getContainers())
            if (poj.getContainerBase().equals(ingredientsBase))
                return poj;
        throw new CouldNotMatchContainerException("Nie ma takiego pojemnika na składnik: " + ingredientsBase);
    }

    //pomaga w dopasowaniu nazwy produktu do EnumManualNames
    @Override
    public EnumManualNames adjustProductsNameToManualName(String productsName) {
        for (EnumManualNames manualNames : EnumManualNames.values())
            if (manualNames.getPreciseName().equals(productsName))
                return manualNames;
        throw new CouldNotMatchProductException("Nie ma takiego produktu: " + productsName);
    }

    //zwraca dodatki danego produktu z zamówienia
    @Override
    public ArrayList<String> getAdditionsOfProductFromOrder(int index) {
        if (order.get(index).getEnumProductType().equals(EnumProductType.DRINK))
            return null;
        ArrayList<String> additions = new ArrayList<>();
        for (Addition addition : ((Foods) order.get(index)).getAdditions())
            additions.add(addition.getPreciseName() + " | " + addition.getPrice() + " PLN | " + addition.getKcal() + " kcal");
        return additions;
    }

    //pomaga w dodawaniu produktu
    @Override
    public boolean addProductToOrderFromString(String productsPreciseName, boolean better) {
        Product newProduct;
        EnumManualNames manualName = adjustProductsNameToManualName(productsPreciseName);
        assert manualName != null;
        switch (manualName.getEnumProductType()) {
            case DRINK: {
                newProduct = new Drinks(manualName);
                break;
            }
            case BURGER: {
                newProduct = new Burger(manualName, better);
                break;
            }
            case SALAD_MIX: {
                newProduct = new SaladMix(manualName, better);
                break;
            }
            default: {
                throw new UnsupportedProductTypeException("Nie ma takiego typu jedzenia");
            }
        }
        return addProductToOrder(newProduct);
    }


    //----------------------------METODY, KTÓRE MAJĄ NA CELU DODANIE DODATKÓW DO PRODUKTU I ZAMÓWIENIA------------------------------

    //dodaje dodatki do produktu na podstawie kodu (wysłanego z GUI)
    @Override
    public String addAdditionalsToLastProductFromString(String additionsCode) {
        Foods lastProduct = (Foods) order.get(order.size() - 1);
        ArrayList<String> additionalNames = makeChoosenAdditinalNamesList(additionsCode);
        String errorCode = createErrorCode(additionalNames, lastProduct);
        ArrayList<Addition> additions = createAdditionsFromErrorCode(errorCode);
        updateOrderWithAdditions(additions, lastProduct);
        return errorCode;
    }

    //tworzy listę nazwe dodatków na podstawie kodu z GUI
    private ArrayList<String> makeChoosenAdditinalNamesList(String additionsCode) {
        ArrayList<String> additionalsNames = new ArrayList<>();
        for (int i = 0; i < getAllAvailableAdditionals().size(); i++)
            if (additionsCode.charAt(i) == '1')
                additionalsNames.add(getAllAvailableAdditionals().get(i));
            else if (additionsCode.charAt(i) == '0' || additionsCode.charAt(i) == '2')
                additionalsNames.add(null);
        return additionalsNames;
    }

    //tworzy kod błędu - które dodatki są, których nie ma
    private String createErrorCode(ArrayList<String> additionalNames, Foods lastProduct) {
        StringBuilder errorCode = new StringBuilder();
        for (String additionName : additionalNames) {
            if (additionName == null) errorCode.append(6);
            else {
                if (checkASvailabilityOfIngredient(Objects.requireNonNull(adjustProductsNameToManualName(additionName)).getIngredients().get(0))) {
                    if (lastProduct.getAdditions().size() >= MAX_ADDITIONS_PER_PRODUCT_LIMIT) errorCode.append(5);
                    else errorCode.append(4);
                } else errorCode.append(5);
            }
        }
        return errorCode.toString();
    }

    //tworzy lsitę dodatków na podstawie kodu błędu
    private ArrayList<Addition> createAdditionsFromErrorCode(String errorCode) {
        ArrayList<Addition> additions = new ArrayList<>();
        for (int i = 0; i < errorCode.length(); i++)
            if (errorCode.charAt(i) == '4') {
                EnumManualNames manualName = adjustProductsNameToManualName(getAllAvailableAdditionals().get(i));
                Addition newAddition = new Addition(manualName);
                additions.add(newAddition);
            }
        return additions;
    }

    //aktualizuje zamówienie (i produkt) dodatkami
    private void updateOrderWithAdditions(ArrayList<Addition> additions, Foods lastProduct) {
        lastProduct.addAdditions(additions);
        for (Addition addition : additions) {
            takeIngredientsForProduct(addition);
            lastProduct.setPrice(lastProduct.getPrice() + addition.getPrice());
            lastProduct.setKcal(lastProduct.getKcal() + addition.getKcal());
            ordersValue += addition.getPrice();
            ordersKcal += addition.getKcal();
        }
    }

    //----------------------------GETTERY I ROZBUDOWANE GETTERY, METODY POMOCNICZE------------------------------

    //zwraca zamówienie jako liste napisów
    @Override
    public ArrayList<String> getOrderAsStringsArray() {
        ArrayList<String> stringOrder = new ArrayList<>();
        if (order == null) return null;
        else {
            for (Product product : order) stringOrder.add(product.specificInfo());
            return stringOrder;
        }
    }

    //zwraca wartość produktu
    @Override
    public double getProductsPrice(String productsPreciseName) {
        return Objects.requireNonNull(adjustProductsNameToManualName(productsPreciseName)).getPrice();
    }

    //zwraca cenę za ulepszenie hamburgera
    @Override
    public double getAdditionalPriceForSpecialSaladMix() {
        return SaladMix.DEFAULT_ADDITIONAL_PRICE;
    }

    //zwraca cenę za ulepszenie sałatki
    @Override
    public double getAdditionalPriceForDoubleBurger() {
        return Burger.DEFAULT_ADDITIONAL_PRICE;
    }

    //zwraca nazwy dodatków + ich ceny
    @Override
    public ArrayList<String> getAllAvailableAdditionalsWithPrices() {
        ArrayList<String> additionalsNamesWithPricesAndKcals = new ArrayList<>();
        for (EnumManualNames addition : EnumManualNames.values())
            if (addition.getEnumProductType().equals(EnumProductType.ADDITION))
                additionalsNamesWithPricesAndKcals.add(addition.getPreciseName() + " | " + addition.getPrice() + " PLN | ");
        return additionalsNamesWithPricesAndKcals;
    }

    public void removeProductFromOrderOfNumber(int number) {
        removeProductFromOrder(getOrder().get(number));
    }

    //----------------------------GETTERY I SETTERY------------------------------

    //zwraca dochód
    public double getRevenues() {
        return roundNumbers(revenues);
    }

    //zwraca zamówienie
    public LinkedList<Product> getOrder() {
        return order;
    }

    //zwraca wartość zamówienia
    public double getOrdersValue() {
        return roundNumbers(ordersValue);
    }

    //zwraca liczbę kcal
    public double getOrdersKcal() {
        return roundNumbers(ordersKcal);
    }

    //zwraca listę produktów
    public ArrayList<String> getAllAvailableProducts() {
        return allAvailableProducts;
    }

    //zwraca listę dodatków
    private ArrayList<String> getAllAvailableAdditionals() {
        return allAvailableAdditionals;
    }

    //zwraca dodatki ostatniego produktu z zamówienia
    public ArrayList<String> getLastProductAdditionals() {
        return ((Foods) order.get(order.size() - 1)).getAdditionsAsStrings();
    }

    //zwraca numery produktów z oferty śniadaniowej
    public ArrayList<Integer> getNumbersOfBreakfastOfferProducts() {
        return numbersOfBreakfastOfferProducts;
    }

    //zaokrągla do dwóch miejsc po przecinku
    double roundNumbers(double number) {
        number *= 100;
        number = Math.round(number);
        number /= 100;
        return number;
    }

    //ustawia jaki jest dochód aktualny
    public void setRevenues(double revenues) {
        this.revenues = revenues;
    }
}
