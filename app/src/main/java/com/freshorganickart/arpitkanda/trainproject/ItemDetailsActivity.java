package com.freshorganickart.arpitkanda.trainproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDetailsActivity extends AppCompatActivity {

    TextView prduct_name;
    TextView prduct_rate;
    TextView item_qty;
    RelativeLayout item_plus;
    RelativeLayout item_minus;
    int number=0;
    String the_id="";
    String id_new="";
    Toolbar toolbar;
    TextView item_id;
    TextView item_price;
    ResultSet rs;
    PreparedStatement pst;
    ConnectionClass connn = new ConnectionClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        toolbar=(Toolbar)findViewById(R.id.item_detail_toolbar);
        toolbar.inflateMenu(R.menu.my_account_menu);
        toolbar.setTitle("ITEM DETAIL");
        prduct_name=(TextView)findViewById(R.id.item_product_id);
        item_id=(TextView)findViewById(R.id.item_detail);
        prduct_rate=(TextView)findViewById(R.id.product_rate_id);
        item_plus=(RelativeLayout) findViewById(R.id.plus_layout);
        item_minus=(RelativeLayout) findViewById(R.id.minus_layout);
        item_price=(TextView)findViewById(R.id.item_price);
        item_qty=(TextView)findViewById(R.id.item_qty);
        Bundle bundle =getIntent().getExtras();
        if (bundle != null) {
            String product_n=bundle.getString("product_name");
            String product_r=bundle.getString("product_rate");
            prduct_name.setText(product_n);
            prduct_rate.setText(product_r);
            the_id=prduct_name.getText().toString();
            new getId().execute(null,null,null);
            id_new=item_id.getText().toString();
        }
        back_button();
        cartButton();
        calculation();
        priceCalculation();
    }
    public void back_button(){
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_navigation_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetailsActivity.this,All_Product_Categorey_Container.class);
                startActivity(intent);
            }
        });
    }
    public void cartButton(){
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.back_id){
                    Toast.makeText(ItemDetailsActivity.this, "Cart Added",
                            Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
    }

    public void calculation(){
        item_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number=number+1;
                item_qty.setText(String.valueOf(number));
            }
        });
        item_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number=number-1;
                item_qty.setText(String.valueOf(number));
            }
        });
    }

    public void priceCalculation(){
        item_qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable){
                float total=0.0f;
                float setPrice=0.0f;
                float setQuantity=0.0f;
                String get_product_price="";
                String get_quantity="";
                get_quantity=item_qty.getText().toString();
                get_product_price=prduct_rate.getText().toString();
                setPrice=Float.valueOf(get_product_price);
                setQuantity=Float.valueOf(get_quantity);
                total=setPrice*setQuantity;
                item_price.setText(String.valueOf(total));
            }
        });
    }
    class getId extends AsyncTask<String, String, String> {
        String id="";
        String description="";
        @Override
        protected String doInBackground(String... strings) {
            try {
                String sql_product = "select Id from TblProduct where ProductName='"+the_id+"'";
                Connection con = connn.conn();
                pst = con.prepareStatement(sql_product);
                rs = pst.executeQuery();
                while (rs.next()) {
                    try {
                        id=rs.getString("Id");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }


            try {
                String sql="select ProductShortDescrip from TblProduct where Id='"+id+"'";
                Connection con = connn.conn();
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    try {
                        description=rs.getString("ProductShortDescrip");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }catch (Exception  ex){
                ex.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(String resultset){
            try {
                Log.e("ID",id);
                Log.e("Description   ",description);
                item_id.setText(description);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}