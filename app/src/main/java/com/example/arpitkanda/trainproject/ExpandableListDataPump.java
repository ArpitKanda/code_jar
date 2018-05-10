package com.example.arpitkanda.trainproject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> fruits = new ArrayList<String>();
        fruits.add("Fruits");
        fruits.add("Vegitables");
        fruits.add("Fruits Vegitable Baseket");
        fruits.add("Cut and herbs");
        fruits.add("South Africa");

        List<String> grocery = new ArrayList<String>();
        grocery.add("Rice & Sugar");
        grocery.add("Atta & Sooji");
        grocery.add("Daale");
        grocery.add("Oil & Ghee");
        grocery.add("Fastion Item");

        List<String> egg = new ArrayList<String>();
        egg.add("Eggs");
        egg.add("Fish & Seafood");
        egg.add("Mutton & Lamb");
        egg.add("Sausages,Bacon & Salami");

        List<String> milk = new ArrayList<String>();
        milk.add("Breads & Buns");
        milk.add("Cookies & Khari");
        milk.add("Cakes & Pastries");
        milk.add("Bakrey Snakes");
        milk.add("Ice Creams & Desseerts");

        List<String> mashale = new ArrayList<String>();
        mashale.add("Desi Mashale");
        mashale.add("Everest");
        mashale.add("Khade Masahle");

        expandableListDetail.put("Fruit & Vegitables", fruits);
        expandableListDetail.put("Grocery", grocery);
        expandableListDetail.put("Egg & Meat", egg);
        expandableListDetail.put("Milk & Dairy", milk);
        expandableListDetail.put("Mashale & Spices", mashale);
        return expandableListDetail;
    }
}
