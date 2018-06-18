package com.freshorganickart.arpitkanda.trainproject;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Product_In_List extends Fragment {

   final ArrayList<Get_Items> itemArrayList =new ArrayList<Get_Items>();  //List items Array
    private MyAppAdapter myAppAdapter; //Array Adapter
    private ListView listView; // ListView
    ResultSet rs;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Spinner the_combo;
    TextView qty_plus;
    TextView qty_minus;
    TextView qty;
    int total=0;
    ItemDetailsActivity detailsActivity=new ItemDetailsActivity();
    Cursor c;
    String product,p_rate,combo="";
    private int progressStatus = 0;
    private Handler handler = new Handler();
    ProgressBar progressBar;
//    MyCountDownTimer myCountDownTimer;
    PreparedStatement pst;
    ImageView image_view;
    ArrayList<String>get_all_items=new ArrayList<String>();

//    double progress;
    Double progress;
    CountDownTimer countDownTimer;
    ConnectionClass connn = new ConnectionClass();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.product_listview, container, false);
        listView = (ListView) view.findViewById(R.id.product_list); //ListView Declaration
        the_combo=(Spinner)view.findViewById(R.id.spinnder_product);
        new getProducts().execute(null, null, null);
        openHelper=new SQLite_DataConnection(getContext());
        db = openHelper.getReadableDatabase();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Get_Items get_items=itemArrayList.get(i);
                product=get_items.getName();
                p_rate=get_items.getRate();
//                combo=String.valueOf(the_combo.getSelectedItemPosition());
                Toast.makeText(getContext(),String.valueOf(get_items.getName()), Toast.LENGTH_SHORT).show();
                loadActivity();
            }
        });
//        qtyCal();
        return view;
    }

    public void loadActivity(){
        Intent intent = new Intent(getContext(), ItemDetailsActivity.class);
        intent.putExtra("product_name",product);
        intent.putExtra("product_rate",p_rate);
        startActivity(intent);
    }

    class getProducts extends AsyncTask<String, String, String> {
        String images="";
        String id="";
        ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... strings) {


            try {
                String sql_product = "select TblProductImage.ProductThumb1,TblProduct.ProductName,TblProduct.ProductPrice from TblProductImage Left Join TblProduct ON TblProductImage.ProductId=TblProduct.Id";
                Connection con = connn.conn();
                pst = con.prepareStatement(sql_product);
                rs = pst.executeQuery();

                while (rs.next()) {
                    try {
                        itemArrayList.add(new Get_Items(rs.getString("ProductName"),rs.getString("ProductPrice"),rs.getString("ProductThumb1")));
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
        protected void onPostExecute(String resultset){
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
           progressDialog  = ProgressDialog.show(getContext(), "Synchronising",
                    "Listview Loading! Please Wait...", true);
        }
    }

    public class MyAppAdapter extends BaseAdapter         //has a class viewholder which holds
    {
        boolean flag=true;
        ViewHolder viewHolder = null;
        int total=0;
        String product_name="";
        String product_rate="";
        String product_qty="";
        String product_combo="";
        int lunda=0;
        public class ViewHolder {
            TextView textName;
            TextView textRate;
            ImageView imageView;
            ImageView wishlist;
            TextView qty_plus;
            TextView qty_minus;
            Spinner textCombo;
            TextView qty;
            TextView the_id;
            Button add_button;

        }
        public class QTY{
            int cat=0;
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
            lunda =position;

            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.product_list_container, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView) rowView.findViewById(R.id.textName);
                viewHolder.textRate = (TextView) rowView.findViewById(R.id.textRate);
                viewHolder.the_id=(TextView)rowView.findViewById(R.id.text_id);
                viewHolder.textCombo = (Spinner)rowView.findViewById(R.id.spinnder_product);
                viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
                viewHolder.wishlist = (ImageView) rowView.findViewById(R.id.product_wish_list);
                viewHolder.qty= (TextView)rowView.findViewById(R.id.qty);
                viewHolder.qty_plus= (TextView) rowView.findViewById(R.id.qty_plus);
                viewHolder.qty_minus= (TextView) rowView.findViewById(R.id.qty_minus);
                viewHolder.add_button= (Button) rowView.findViewById(R.id.item_button);
                wish(viewHolder);
                qtyCal(viewHolder);
                getItems();

                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();

            }
            // here setting up names and images
            viewHolder.textName.setText(parkingList.get(position).getName() + "");
            viewHolder.textRate.setText(parkingList.get(position).getRate() + "");
            Picasso.with(context).load("http://"+parkingList.get(position).getImg()).into(viewHolder.imageView);
//            byte[] decodeString = Base64.decode("fuck", Base64.DEFAULT);
//            Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//            image_view.setImageBitmap(decodebitmap);
//            listclik();

            return rowView;
        }
        public void getItems(){
            viewHolder.add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                  viewHolder.add_button.setVisibility(View.INVISIBLE);
                    Log.e("Position",String.valueOf(lunda));
//                    Toast.makeText(getContext(),String.valueOf(arraylist.get(lunda)), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void listclik(){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Get_Items get_items=arraylist.get(i);
                    Toast.makeText(getContext(),get_items.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }



        public void qtyCal(final ViewHolder viewHolder){
           final int number=0;
            viewHolder.qty_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    total=number+1;
                    viewHolder.qty.setText(String.valueOf(total));
                }
            });

            viewHolder.qty_plus.setClickable(true);
            viewHolder.qty_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    total=number-1;
                    viewHolder.qty.setText(String.valueOf(total));
                }
            });
        }
        public void wish(final ViewHolder viewHolder){
//            db = openHelper.getWritableDatabase();
//            product_name=viewHolder.textName.getText().toString();
//            product_qty=viewHolder.qty.getText().toString();
//            product_rate=viewHolder.textRate.getText().toString();
//            product_combo=viewHolder.textCombo.getSelectedItem().toString();
            viewHolder.wishlist.setClickable(true);
            viewHolder.wishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(flag==true){
                        viewHolder.wishlist.setBackgroundColor(Color.WHITE);
                        flag=false;
                        delele_cart();
//                        insert_Data(product_name,product_qty,product_rate,product_combo);

                    }else if(flag==false){
                        viewHolder.wishlist.setBackgroundColor(Color.RED);
                        flag=true;
                        Toast.makeText(getContext(), String.valueOf(flag), Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
        public void insert_Data(String p_name,String p_rate,String p_qty,String p_combo){
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLite_DataConnection.product_name, p_name);
//            contentValues.put(SQLite_DataConnection.product_rate, p_rate);
//            contentValues.put(SQLite_DataConnection.product_qty, p_qty);
//            contentValues.put(SQLite_DataConnection.product_combo, p_combo);
            db.insert(SQLite_DataConnection.TABLE_NAME, null, contentValues);
            Toast.makeText(getContext(),"Product Added", Toast.LENGTH_SHORT).show();
        }

        public void delele_cart(){
            db = openHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
//            db.delete(SQLite_DataConnection.TABLE_NAME, null, contentValues);
            db.delete(SQLite_DataConnection.TABLE_NAME, 1 + "=" + 1, null);
            Toast.makeText(getContext(),"Product Deleted", Toast.LENGTH_SHORT).show();
        }

    }
}
