package com.freshorganickart.arpitkanda.trainproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class My_Account_Adapter extends ArrayAdapter<Get_Account_Item> {
    private int mcolor;
    TextView qty_plus;
    TextView qty_minus;
    int number = 0;

    public My_Account_Adapter(Context context, ArrayList<Get_Account_Item> words, int colorID) {
        super(context, 0, words);
        mcolor = colorID;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.my_account_list, parent, false);

        }
        Get_Account_Item word = getItem(position);
        TextView name = (TextView) listView.findViewById(R.id.myaccount_item);
        name.setText(word.getNames());
//        TextView sub_name = (TextView) listView.findViewById(R.id.all_sub_name);
//        sub_name.setText(word.getSubNames());
        ImageView imageView = (ImageView) listView.findViewById(R.id.account_image_id_holder);
        imageView.setImageResource(word.getImage());
        return listView;
    }
}
