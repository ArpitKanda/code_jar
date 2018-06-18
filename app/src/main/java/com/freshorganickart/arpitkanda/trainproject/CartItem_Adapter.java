package com.freshorganickart.arpitkanda.trainproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by Arpit Kanda on 30-May-18.
 */

public class CartItem_Adapter extends BaseAdapter {



    private List<Cart_Item> cartItems = Collections.emptyList();

    private final Context context;

    public CartItem_Adapter(Context context) {
        this.context = context;
    }

    public void updateCartItems(List<Cart_Item> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
