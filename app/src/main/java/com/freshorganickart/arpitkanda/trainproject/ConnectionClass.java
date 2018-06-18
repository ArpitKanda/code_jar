package com.freshorganickart.arpitkanda.trainproject;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;



public class ConnectionClass {

    Connection conn=null;

    public Connection conn(){
        try{
            Log.e("MYSQL","Attempt To Connecct");
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:jtds:sqlserver://45.113.136.86/hairnhar_Grocery","hairnhar_UsrGrocery","Rvpw9#06");
            Log.e("MYSQL","Connected");


        }catch(Exception ex){
            ex.printStackTrace();
            Log.e("SHOW SOME ERRORS", ex.toString());
        }
        return conn;
    }
}
