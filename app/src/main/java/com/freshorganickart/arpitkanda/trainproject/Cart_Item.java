package com.freshorganickart.arpitkanda.trainproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Cart_Item extends Fragment{
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor c;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.cart_item, container, false);
        openHelper=new SQLite_DataConnection(getContext());
        db = openHelper.getReadableDatabase();
        setCartItem();
        return view;
    }

    public void setCartItem(){
        c = db.rawQuery("SELECT*FROM "+SQLite_DataConnection.TABLE_NAME,null);
        int product_name=c.getColumnIndex(SQLite_DataConnection.product_name);
        int product_qty=c.getColumnIndex(SQLite_DataConnection.product_name);
        int product_rate=c.getColumnIndex(SQLite_DataConnection.product_name);
        int product_combo=c.getColumnIndex(SQLite_DataConnection.product_name);
        c.moveToFirst();
        while (c != null) {

            if (c.getCount() > 0) {
//                Log.i("Srudent Name",String.valueOf(get));
                Log.i("Name",String.valueOf(product_name));
                Toast.makeText(getContext(), "Login Success", Toast.LENGTH_SHORT).show();
//

            } else {
                Toast.makeText(getContext(), "Login error", Toast.LENGTH_SHORT).show();
            }
            c.moveToNext();
        }
    }
    }