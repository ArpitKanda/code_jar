package com.freshorganickart.arpitkanda.trainproject;

import java.util.ArrayList;

public class ExpandableListDataPump {

    public String Name;
    public String Image;
    public ArrayList<String> players=new ArrayList<String>();

    public ExpandableListDataPump(String Name)
    {
        this.Name=Name;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return Name;
    }
}