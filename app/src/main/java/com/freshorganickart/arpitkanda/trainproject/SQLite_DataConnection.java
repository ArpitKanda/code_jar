package com.freshorganickart.arpitkanda.trainproject;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SQLite_DataConnection extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="fresh_organic_kart.db";
    public static final String TABLE_NAME="product_cart_info_ji";
    public static final String product_name="Product_Name";
//    public static final String product_rate="Product_Rate";
//    public static final String product_qty="Product Qty";
//    public static final String product_combo="Prodcut_Combo";
    public SQLite_DataConnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLite_DataConnection(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Product_Name TEXT/*,Product_Rate Text,Product_Qty TEXT,Product_Combo TEXT*/)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
