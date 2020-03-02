package com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum EnumManualNames {

    //----------------------------PRODUKTY------------------------------

    //BURGERY
    BeafBurger("Beaf-Burger", EnumProductType.BURGER, 19.99, false,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.ChlebPszenny, 100),
                    new Ingredient(EnumIngredientsBase.Wolowina, 70),
                    new Ingredient(EnumIngredientsBase.Kurczak, 60),
                    new Ingredient(EnumIngredientsBase.Jagniecina, 50),
                    new Ingredient(EnumIngredientsBase.Salata, 20),
                    new Ingredient(EnumIngredientsBase.SerZolty, 30),
                    new Ingredient(EnumIngredientsBase.Ogorek, 15),
                    new Ingredient(EnumIngredientsBase.Ogorek, 45),
                    new Ingredient(EnumIngredientsBase.Groch, 10),
                    new Ingredient(EnumIngredientsBase.Koper, 5),
                    new Ingredient(EnumIngredientsBase.Rzodkiew, 30)
            )),

    BigBurger("Big-Burger", EnumProductType.BURGER, 26.99, false,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.ChlebPszenny, 100),
                    new Ingredient(EnumIngredientsBase.Wieprzowina, 50),
                    new Ingredient(EnumIngredientsBase.Wolowina, 50),
                    new Ingredient(EnumIngredientsBase.Jagniecina, 50),
                    new Ingredient(EnumIngredientsBase.Dziczyzna, 50),
                    new Ingredient(EnumIngredientsBase.SerZolty, 20),
                    new Ingredient(EnumIngredientsBase.Salata, 40),
                    new Ingredient(EnumIngredientsBase.Pomidor, 20),
                    new Ingredient(EnumIngredientsBase.Papryka, 20),
                    new Ingredient(EnumIngredientsBase.Rzodkiewka, 15),
                    new Ingredient(EnumIngredientsBase.Szczypiorek, 5)
            )),

    ChickenBurger("Chicken-Burger", EnumProductType.BURGER, 21.99, false,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.Bulka, 100),
                    new Ingredient(EnumIngredientsBase.Papryka, 50),
                    new Ingredient(EnumIngredientsBase.Kurczak, 200),
                    new Ingredient(EnumIngredientsBase.Salata, 40),
                    new Ingredient(EnumIngredientsBase.SerZolty, 70),
                    new Ingredient(EnumIngredientsBase.Mozzarella, 50)
            )),

    DrwalBurger("Drwal-Burger", EnumProductType.BURGER, 25.99, false,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.ChlebRazowy, 100),
                    new Ingredient(EnumIngredientsBase.Dziczyzna, 175),
                    new Ingredient(EnumIngredientsBase.Rzepa, 40),
                    new Ingredient(EnumIngredientsBase.SerZolty, 50),
                    new Ingredient(EnumIngredientsBase.Pasternak, 40),
                    new Ingredient(EnumIngredientsBase.Pietruszka, 10),
                    new Ingredient(EnumIngredientsBase.Pomidor, 70),
                    new Ingredient(EnumIngredientsBase.Salsefia, 5),
                    new Ingredient(EnumIngredientsBase.Szpinak, 30)
            )),

    FishBurger("Fish-Burger", EnumProductType.BURGER, 17.99, true,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.Grahamka, 100),
                    new Ingredient(EnumIngredientsBase.Ryba, 90),
                    new Ingredient(EnumIngredientsBase.Salata, 50),
                    new Ingredient(EnumIngredientsBase.SerZolty, 30),
                    new Ingredient(EnumIngredientsBase.SerBialy, 30),
                    new Ingredient(EnumIngredientsBase.Papryka, 20),
                    new Ingredient(EnumIngredientsBase.Kukurydza, 90),
                    new Ingredient(EnumIngredientsBase.Marchew, 80)
            )),


    RoyalBurger("Royal-Burger", EnumProductType.BURGER, 12.99, true,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.Bagietka, 100),
                    new Ingredient(EnumIngredientsBase.Baklazan, 50),
                    new Ingredient(EnumIngredientsBase.Wolowina, 200),
                    new Ingredient(EnumIngredientsBase.Cebula, 40),
                    new Ingredient(EnumIngredientsBase.Cykoria, 70),
                    new Ingredient(EnumIngredientsBase.Dynia, 50),
                    new Ingredient(EnumIngredientsBase.Kalarepa, 50),
                    new Ingredient(EnumIngredientsBase.Kapusta, 50),
                    new Ingredient(EnumIngredientsBase.Karczoch, 50)
            )),

    WiesBurger("Wies-Burger", EnumProductType.BURGER, 18.99, true,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.Kajzerka, 100),
                    new Ingredient(EnumIngredientsBase.Wieprzowina, 100),
                    new Ingredient(EnumIngredientsBase.Kurczak, 100),
                    new Ingredient(EnumIngredientsBase.SerBialy, 20),
                    new Ingredient(EnumIngredientsBase.Bob, 20),
                    new Ingredient(EnumIngredientsBase.Brokul, 30),
                    new Ingredient(EnumIngredientsBase.Kalafior, 30),
                    new Ingredient(EnumIngredientsBase.Burak, 40),
                    new Ingredient(EnumIngredientsBase.Seler, 40),
                    new Ingredient(EnumIngredientsBase.Por, 50)
            )),


    //SAŁATKI
    AmerykanskaSalatka("Salatka Amerykanska", EnumProductType.SALAD_MIX, 11.99, false,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.Wolowina, 100),
                    new Ingredient(EnumIngredientsBase.Dynia, 50),
                    new Ingredient(EnumIngredientsBase.Kukurydza, 60),
                    new Ingredient(EnumIngredientsBase.Pomidor, 70),
                    new Ingredient(EnumIngredientsBase.Salata, 90),
                    new Ingredient(EnumIngredientsBase.Szalotka, 20),
                    new Ingredient(EnumIngredientsBase.Szczypiorek, 50),
                    new Ingredient(EnumIngredientsBase.Bob, 30)
            )),

    FinskaSalatka("Salatka Finska", EnumProductType.SALAD_MIX, 17.99, false,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.Czosnek, 15),
                    new Ingredient(EnumIngredientsBase.Cykoria, 5),
                    new Ingredient(EnumIngredientsBase.Groch, 10),
                    new Ingredient(EnumIngredientsBase.Jarmuz, 50),
                    new Ingredient(EnumIngredientsBase.Koper, 10),
                    new Ingredient(EnumIngredientsBase.Kalafior, 60),
                    new Ingredient(EnumIngredientsBase.Por, 40),
                    new Ingredient(EnumIngredientsBase.Rzodkiew, 30),
                    new Ingredient(EnumIngredientsBase.Rzodkiewka, 20),
                    new Ingredient(EnumIngredientsBase.Salata, 90)
            )),

    FrancuskaSalatka("Salatka Francuska", EnumProductType.SALAD_MIX, 16.99, false,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.Baklazan, 50),
                    new Ingredient(EnumIngredientsBase.Brokul, 20),
                    new Ingredient(EnumIngredientsBase.Groch, 30),
                    new Ingredient(EnumIngredientsBase.Kalarepa, 40),
                    new Ingredient(EnumIngredientsBase.Kalafior, 50),
                    new Ingredient(EnumIngredientsBase.Ogorek, 50),
                    new Ingredient(EnumIngredientsBase.Pietruszka, 5),
                    new Ingredient(EnumIngredientsBase.Por, 40),
                    new Ingredient(EnumIngredientsBase.Szpinak, 80),
                    new Ingredient(EnumIngredientsBase.Salata, 90)
            )),

    GreckaSalatka("Salatka Grecka", EnumProductType.SALAD_MIX, 9.99, false,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.Brokul, 30),
                    new Ingredient(EnumIngredientsBase.Brukiew, 50),
                    new Ingredient(EnumIngredientsBase.Burak, 50),
                    new Ingredient(EnumIngredientsBase.Papryka, 50),
                    new Ingredient(EnumIngredientsBase.Pasternak, 30),
                    new Ingredient(EnumIngredientsBase.Pietruszka, 5),
                    new Ingredient(EnumIngredientsBase.Pomidor, 30),
                    new Ingredient(EnumIngredientsBase.Salsefia, 5),
                    new Ingredient(EnumIngredientsBase.Szczypiorek, 5),
                    new Ingredient(EnumIngredientsBase.Salata, 90)
            )),

    NiemieckaSalatka("Salatka Niemiecka", EnumProductType.SALAD_MIX, 24.99, true,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.Jarmuz, 40),
                    new Ingredient(EnumIngredientsBase.Szpinak, 70),
                    new Ingredient(EnumIngredientsBase.Dynia, 60),
                    new Ingredient(EnumIngredientsBase.Kalarepa, 50),
                    new Ingredient(EnumIngredientsBase.Karczoch, 10),
                    new Ingredient(EnumIngredientsBase.Roszponka, 5),
                    new Ingredient(EnumIngredientsBase.Rzepa, 50),
                    new Ingredient(EnumIngredientsBase.Rzezucha, 5),
                    new Ingredient(EnumIngredientsBase.Skorzonera, 5),
                    new Ingredient(EnumIngredientsBase.Salata, 90)
            )),

    PolskaSalatka("Salatka Polska", EnumProductType.SALAD_MIX, 22.99, true,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.Cebula, 80),
                    new Ingredient(EnumIngredientsBase.Czosnek, 20),
                    new Ingredient(EnumIngredientsBase.Karczoch, 60),
                    new Ingredient(EnumIngredientsBase.Szpinak, 60),
                    new Ingredient(EnumIngredientsBase.Pomidor, 50),
                    new Ingredient(EnumIngredientsBase.Rzodkiew, 30),
                    new Ingredient(EnumIngredientsBase.Rzodkiewka, 30),
                    new Ingredient(EnumIngredientsBase.Seler, 30),
                    new Ingredient(EnumIngredientsBase.Szczypiorek, 5),
                    new Ingredient(EnumIngredientsBase.Salata, 10)
            )),

    RosyjskaSalatka("Salatka Rosyjska", EnumProductType.SALAD_MIX, 8.99, true,
            Arrays.asList(
                    new Ingredient(EnumIngredientsBase.Rzepa, 100),
                    new Ingredient(EnumIngredientsBase.Roszponka, 10),
                    new Ingredient(EnumIngredientsBase.Rzezucha, 10),
                    new Ingredient(EnumIngredientsBase.Salsefia, 5),
                    new Ingredient(EnumIngredientsBase.Skorzonera, 5),
                    new Ingredient(EnumIngredientsBase.Szalotka, 5),
                    new Ingredient(EnumIngredientsBase.Szczypiorek, 5),
                    new Ingredient(EnumIngredientsBase.Szpinak, 70),
                    new Ingredient(EnumIngredientsBase.Koper, 5),
                    new Ingredient(EnumIngredientsBase.Salata, 90)
            )),

    //PICIA
    ButelkaCoca("Butelka Coca-coli", EnumProductType.DRINK, 4.99, true,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.CocaCola, 1000)
            )),

    ButelkaSprite("Butelka Sprite'a", EnumProductType.DRINK, 4.99, true,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Sprite, 1000)
            )),

    ButelkaWarka("Butelka Piwa Warka", EnumProductType.DRINK, 7.99, false,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Warka, 500)
            )),

    ButelkaTyskie("Butelka Piwa Tyskie", EnumProductType.DRINK, 7.99, false,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Tyskie, 500)
            )),

    ButelkaHarnas("Butelka Piwa Harnas", EnumProductType.DRINK, 8.99, false,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Harnas, 500)
            )),

    ButelkaZubr("Butelka Piwa Zubr", EnumProductType.DRINK, 9.99, false,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Zubr, 500)
            )),

    PuszkaRedBull("Puszka RedBull'a", EnumProductType.DRINK, 7.99, true,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.RedBull, 300)
            )),

    PuszkaBurn("Puszka Burn'a", EnumProductType.DRINK, 6.99, true,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Burn, 200)
            )),

    //DODATKI
    FrytkiZZiemniakow("Frytki ziemniaczane", EnumProductType.ADDITION, 3.99, true,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Ziemniak, 150)
            )),

    FrytkiZBatatow("Frytki z batatów", EnumProductType.ADDITION, 5.99, true,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Batat, 150)
            )),

    Hummus("Hummus", EnumProductType.ADDITION, 4.99, true,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Hummus, 50)
            )),
    Awokado("Awokado", EnumProductType.ADDITION, 2.99, true,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Awokado, 60)
            )),
    Tofu("Tofu", EnumProductType.ADDITION, 7.99, true,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Tofu, 60)
            )),

    Bekon("Bekon", EnumProductType.ADDITION, 3.99, true,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Bekon, 100)
            )),

    Brukselka("Brukselka", EnumProductType.ADDITION, 0.99, true,
            Collections.singletonList(
                    new Ingredient(EnumIngredientsBase.Brukselka, 90)
            )),
    ;

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTOR------------------------------

    private List<Ingredient> ingredients;
    private double price;
    private EnumProductType enumProductType;
    private boolean breakfastOffer;
    private String preciseName;

    //domyślny - najbardziej szczegółowy
    EnumManualNames(String preciseName, EnumProductType enumProductType, double price, boolean breakfastOffer, List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.enumProductType = enumProductType;
        this.price = price;
        this.breakfastOffer = breakfastOffer;
        this.preciseName = preciseName;
    }

    //----------------------------GETTERY------------------------------

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public double getPrice() {
        return price;
    }

    public EnumProductType getEnumProductType() {
        return enumProductType;
    }

    public boolean isBreakfastOffer() {
        return breakfastOffer;
    }

    public String getPreciseName() {
        return preciseName;
    }
}