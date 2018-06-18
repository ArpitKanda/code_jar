package com.freshorganickart.arpitkanda.trainproject;
public class Get_Items {

    public String img; //Image URL
    public String name; //Name
    public String rate; //Rate

    public Get_Items(String name,String rate,String img)
    {
        this.img = img;
        this.name = name;
        this.rate=rate;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getRate() {
        return rate;
    }
}
