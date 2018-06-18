package com.freshorganickart.arpitkanda.trainproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arpit Kanda on 08-Jun-18.
 */

public class Fruits_Vegitables extends Fragment {
    private ArrayList<Get_Items> itemArrayList;  //List items Array
    private MyAppAdapter myAppAdapter; //Array Adapter
    private ListView listView; // ListView
    Statement st;
    ResultSet rs;
    TextView fuck;

    PreparedStatement pst;
    ImageView image_view;
    ArrayList<String>get_all_items=new ArrayList<String>();
    TextView rate;
    ConnectionClass connn = new ConnectionClass();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.product_listview, container, false);
//        fuck = (TextView)view.findViewById(R.id.textName);

        listView = (ListView) view.findViewById(R.id.product_list); //ListView Declaration
        itemArrayList = new ArrayList<Get_Items>(); // Arraylist Initialization
        new getProducts().execute(null, null, null);

        return view;
    }




    class getProducts extends AsyncTask<String, String, String> {
        String images="";
ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... strings) {


            try {
//                String sql_product = "select TblProductImage.ProductThumb1,TblProduct.ProductName,TblProduct.ProductPrice from TblProductImage Left Join TblProduct ON TblProductImage.ProductId=TblProduct.Id";
                String sql_product="select *from TblProduct where SubCategoryId=43 OR SubCategoryId=22";
                Connection con = connn.conn();
                pst = con.prepareStatement(sql_product);
                rs = pst.executeQuery();

                while (rs.next()) {
                    try {
                        itemArrayList.add(new Get_Items(rs.getString("ProductName"),rs.getString("ProductPrice"),rs.getString("ProductPrice")));
                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String resultset) {
           progressDialog.dismiss();
            try {
                myAppAdapter = new MyAppAdapter(itemArrayList, getContext());
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                listView.setAdapter(myAppAdapter);
            } catch (Exception ex) {
                ex.printStackTrace();

            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getContext(), "Synchronising",
                    "Listview Loading! Please Wait...", true);
        }
    }

    public class MyAppAdapter extends BaseAdapter         //has a class viewholder which holds
    {
        MyAppAdapter.ViewHolder viewHolder = null;
        public class ViewHolder {
            TextView textName;
            TextView textRate;
            ImageView imageView;
            TextView add_button;
            TextView qty_plus;
            TextView qty_minus;
            TextView qty;
            int number =0;
        }

        public List<Get_Items> parkingList;

        public Context context;
        ArrayList<Get_Items> arraylist;

        private MyAppAdapter(List<Get_Items> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<Get_Items>();
            arraylist.addAll(parkingList);

        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) // inflating the layout and initializing widgets
        {

            View rowView = convertView;

            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.product_list_container, parent, false);
                viewHolder = new MyAppAdapter.ViewHolder();
                viewHolder.textName = (TextView) rowView.findViewById(R.id.textName);
                viewHolder.textRate = (TextView) rowView.findViewById(R.id.textRate);
//                viewHolder.add_button = (TextView) rowView.findViewById(R.id.add_id);
                viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
                viewHolder.qty= (TextView)rowView.findViewById(R.id.qty);
                viewHolder.qty_plus= (TextView) rowView.findViewById(R.id.qty_plus);
                viewHolder.qty_minus= (TextView)rowView.findViewById(R.id.qty_minus);
//                qtyPlus(viewHolder);
//                qtyMinus(viewHolder);

                rowView.setTag(viewHolder);
            } else {
                viewHolder = (MyAppAdapter.ViewHolder) convertView.getTag();

            }
            // here setting up names and images
            viewHolder.textName.setText(parkingList.get(position).getName() + "");
            viewHolder.textRate.setText(parkingList.get(position).getRate() + "");
//            Picasso.with(context).load(parkingList.get(position).getImg()).into(viewHolder.imageView);
//            byte[] decodeString = Base64.decode("fuck", Base64.DEFAULT);
//            Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//            image_view.setImageBitmap(decodebitmap);
            return rowView;
        }

//        public void qtyPlus(final ViewHolder viewHolder){
//
//            viewHolder.qty_plus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    viewHolder.number=viewHolder.number+1;
//                    viewHolder.qty.setText(String.valueOf(viewHolder.number));
//                }
//            });
//        }
//
//        public void qtyMinus(final ViewHolder viewHolder){
//            viewHolder.qty_minus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    viewHolder.number=viewHolder.number-1;
//                    viewHolder.qty.setText(String.valueOf(viewHolder.number));
//                }
//            });
//        }
    }
}


