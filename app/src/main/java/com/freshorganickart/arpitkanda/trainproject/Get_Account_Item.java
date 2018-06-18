package com.freshorganickart.arpitkanda.trainproject;

/**
 * Created by Arpit Kanda on 30-May-18.
 */

public class Get_Account_Item
{
    private String names;
    private String sub_names;
    private int images;
//    private int item_audio;

    public Get_Account_Item(String item_names, String item_sub_names,int item_images) {
        names = item_names;
        sub_names=item_sub_names;
        images = item_images;
//        item_audio = audio;

    }

    public String getNames() {
        return names;
    }

    public String getSubNames(){
        return sub_names;
    }
    public int getImage() {
        return images;

    }
}
