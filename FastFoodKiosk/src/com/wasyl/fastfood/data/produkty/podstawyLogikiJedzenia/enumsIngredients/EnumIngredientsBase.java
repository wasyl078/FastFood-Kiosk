package com.wasyl.fastfood.data.produkty.podstawyLogikiJedzenia.enumsIngredients;

public enum EnumIngredientsBase {

    //----------------------------SKŁADNIKI DO PICIA------------------------------

    CocaCola(100000,200,true, true, 37.5, 0, false, false),
    Sprite(100000,200,true, true, 39.4, 0, false, false),
    Warka(50000,300,true, true, 49, 4.5, false, false),
    Tyskie(50000,300,true, true, 64, 5.6, false, false),
    Harnas(50000,300,true, true, 45, 3.9, false, false),
    Zubr(50000, 300,true, true, 51, 6.4, false, false),
    RedBull(10000, 600,true, true, 45.3, 0, true, false),
    Burn(500,500,true, true, 56, 0, true, false),

    //----------------------------SKŁADNIKI DO JEDZENIA------------------------------

    //pszenne
    ChlebPszenny(20000,80,true, false, 258),
    ChlebRazowy(20000,80,true, false, 247.1),
    Bulka(20000,60,true, false, 310),
    Grahamka(20000,70,true, false, 270),
    Bagietka(20000,120,true, false, 265),
    Kajzerka(20000,100,true, false, 293),

    //mięso
    Wieprzowina(40000,160,false, false, 242.1),
    Wolowina(40000,250,false, false, 250.5),
    Jagniecina(40000,260,false, false, 294),
    Dziczyzna(40000,300,false, false, 299.2),
    Kurczak(40000,130,false, false, 272),
    Ryba(40000,100,false, false, 85),
    Bekon(2000,90,false, false, 540.6),

    //nabiał
    SerBialy(5000, 20,true, false, 402.5),
    SerZolty(5000,80,true, false, 356.3),
    Mozzarella(5000,120,true, false, 280.1),

    //warzywa
    Awokado(3000, 30,160.1),
    Baklazan(1000, 20,58.8),
    Batat(5000, 200,24.9),
    Bob(70, 20,88),
    Brokul(2000, 50,35),
    Brukiew(1000, 20,37.5),
    Brukselka(2000, 20,42.8),
    Burak(2000, 50,43),
    Cebula(1000, 20,39.7),
    Cykoria(1000, 20,22),
    Czosnek(1000, 20,149),
    Dynia(2000, 50,26.1),
    Groch(2000, 50,81),
    Jarmuz(3000, 100,49.5),
    Kalafior(2000, 50,24.9),
    Kalarepa(3000, 100,27),
    Kapusta(3000, 100,24.6),
    Karczoch(1000, 20,47.1),
    Koper(1000, 20,305),
    Kukurydza(3000, 100,86),
    Marchew(2000, 50,41.3),
    Ogorek(3000, 100,13),
    Papryka(3000, 100,39.9),
    Pasternak(1000, 20,75),
    Pietruszka(1000, 20,36.1),
    Pomidor(3000, 100,18),
    Por(2000, 50,61),
    Roszponka(1000, 20,21),
    Rzepa(3000, 100,28),
    Rzezucha(1000, 20,32),
    Rzodkiew(1000, 20,100),
    Rzodkiewka(1000, 20,16),
    Salsefia(1000, 20,82),
    Salata(10000, 150,14.8),
    Seler(3000, 100,21),
    Skorzonera(1000, 20,63),
    Szalotka(1000, 20,72),
    Szczypiorek(1000, 20,30.1),
    Szpinak(2000, 50,25),
    Ziemniak(10000, 10,77),

    //inne
    Oliwa(1000, 20,884.1),
    Hummus(1000,100,166.1),
    Tofu(150,100,76),
    ;

    //----------------------------INSTANCJE OBIEKTÓW, KONSTRUKTORY------------------------------

    private boolean vegan;
    private boolean vegetarian;
    private double kcalPer100g;
    private double alcoholPercentage;
    private boolean hotDrink;
    private boolean withCaffeine;
    private double defaulContainerSize;
    private double priceForRefiling;

    //do ciepłych / alkoholowych napojów - najbardziej szczegółowy
    EnumIngredientsBase(int defaulContainerSize, double priceForRefiling, boolean vegetarian, boolean vegan, double kcalPer100g, double alcoholPercentage, boolean withCaffeine, boolean hotDrink) {
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.kcalPer100g = kcalPer100g;
        this.alcoholPercentage = alcoholPercentage;
        this.hotDrink = hotDrink;
        this.withCaffeine = withCaffeine;
        this.defaulContainerSize = defaulContainerSize;
        this.priceForRefiling = priceForRefiling;
    }

    //do mięs - składników, ktróre są tylko dla niewegan, niewegetarian
    EnumIngredientsBase(int defaulContainerSize, double priceForRefiling, boolean vegetarian, boolean vegan, double kcalPer100g) {
        this(defaulContainerSize, priceForRefiling, vegetarian, vegan, kcalPer100g, 0, false, false);
    }

    //do warzyw - wegetarianskie i weganskie
    EnumIngredientsBase(int defaulContainerSize, double priceForRefiling, double kcalPer100g) {
        this(defaulContainerSize, priceForRefiling, true, true, kcalPer100g, 0, false, false);
    }

    //----------------------------GETTERY------------------------------

    public boolean isVegan() {
        return vegan;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public double getKcalPer100g() {
        return kcalPer100g;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public boolean isHotDrink() {
        return hotDrink;
    }

    public boolean isWithCaffeine() {
        return withCaffeine;
    }

    public double getPriceForRefiling() {
        return priceForRefiling;
    }

    public double getDefaulContainerSize() {
        return defaulContainerSize;
    }
}