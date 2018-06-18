package com.freshorganickart.arpitkanda.trainproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Genral_Info extends Fragment{

    PreparedStatement pst;
    Statement st;
    ResultSet rs;
    ImageView imageView;
    Button button;
    ConnectionClass connn = new ConnectionClass();
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        final View view;
        view=inflater.inflate(R.layout.genral_info, container, false);
        imageView=(ImageView)view.findViewById(R.id.image_get);
        button=(Button)view.findViewById(R.id.gen_save_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new getImage().execute(null,null,null);
            }
        });
        return view;
    }

    class getImage extends AsyncTask<String,String,String>{
        String z = "";
        @Override
        protected String doInBackground(String... strings) {

            try {
                String sql="select * from TblProductImage where ProductId='"+36+"'";
                Connection con = connn.conn();
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs != null && rs.next()){
                    try{
                        z=rs.getString("ProductImage1");
                        Log.e("Path  ",z);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            byte[] decodeString = Base64.decode(s, Base64.DEFAULT);
            Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
                    0, decodeString.length);
            imageView.setImageBitmap(decodebitmap);

        }

        @Override
        protected void onPreExecute() {

        }
    }
}
