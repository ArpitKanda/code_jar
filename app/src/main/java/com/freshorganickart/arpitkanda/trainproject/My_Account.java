package com.freshorganickart.arpitkanda.trainproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class My_Account extends AppCompatActivity {
    ListView account_list;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;
    ListView listView;
    Bitmap bitmap;
    MenuItem back_menu;
    TextView change_profile;
    TextView name;
    TextView email;
    LinearLayout myAccount;
    CircleImageView imageView;
    Statement st;
    ResultSet rs;
    Toolbar toolbar;
    String get_username="";
    String get_email="";
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor c;
    PreparedStatement pst;
    private ArrayList<Get_Account_Item> account_items;  //List items Array
    private My_Account_Adapter myAccountAdapter; //Array Adapter
    ConnectionClass connn=new ConnectionClass();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);
        openHelper=new SQLite_DataConnection(this);
        db = openHelper.getReadableDatabase();
//        SQLiteDatabase db=this.openOrCreateDatabase("REG",MODE_PRIVATE,null);
//        setStatusBarColor(findViewById(R.id.statusBarBackground),getResources().getColor(android.R.color.holo_green_dark));
        toolbar=(Toolbar)findViewById(R.id.my_account_toolbar);
        toolbar.setTitle("Fresh Organic Kart");
//        toolbar.inflateMenu(R.menu.my_account_menu);
        name=(TextView)findViewById(R.id.user_name_id);
        email=(TextView)findViewById(R.id.user_emial_id);
        change_profile=(TextView)findViewById(R.id.change_id);
        imageView=(CircleImageView)findViewById(R.id.user_image);
        Bundle bundle =getIntent().getExtras();
        if (bundle != null) {
            String key_name =bundle.getString("name");
            String key_email =bundle.getString("email");
//            String key_image=bundle.getString("image");
//            Toast.makeText(getContext(),"Lund"+name, Toast.LENGTH_SHORT).show();
            name.setText(key_name);
            email.setText(key_email);
            get_username=name.getText().toString();
            get_email=email.getText().toString();


        }

        final ArrayList<Get_Account_Item> name=new ArrayList<Get_Account_Item>();
        name.add(new Get_Account_Item("My Orders","Apple",R.drawable.order_icon));
        name.add(new Get_Account_Item("My Wallets","0.0",R.drawable.wallet));
        name.add(new Get_Account_Item("Notification","",R.drawable.notifi));
        name.add(new Get_Account_Item("Refer & Run","",R.drawable.rupee));
        name.add(new Get_Account_Item("My Gift Cards","",R.drawable.gift));
        name.add(new Get_Account_Item("Change Password","",R.drawable.ch_pass));
        name.add(new Get_Account_Item("Log Out","",R.drawable.logout));
        account_items = new ArrayList<Get_Account_Item>();
        listView=(ListView)findViewById(R.id.list_myaccount);
        My_Account_Adapter adapter=new My_Account_Adapter(this,name,R.color.pure_white);

        listView.setAdapter(adapter);
        change_profile=(TextView)findViewById(R.id.change_id);

        change_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCart();
            }
        });
//        upadteProfile();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Get_Account_Item accountItem=name.get(i);
             String name=   accountItem.getNames();
                Toast.makeText(My_Account.this,accountItem.getNames(), Toast.LENGTH_SHORT).show();
                insert_Data(name);
            }
        });
aa();

//        db.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR,age INT(3))");
//        db.execSQL("INSERT INTO users (name,age)VALUES('Arpit',24)");
//        db.execSQL("INSERT INTO users (name,age)VALUES('Prachi',24)");
//        Cursor c=db.rawQuery("SELECT * FROM users",null);
//        int nameIndex=c.getColumnIndex("name");
//        int ageIndex=c.getColumnIndex("age");
//
//        if (c!=null){
//            if(c.moveToFirst()){
//                do{
//                    Log.e("Name",c.getString(nameIndex));
//                    Log.e("Age",Integer.toString(c.getInt(ageIndex)));
//                }while ( c.moveToNext());
//            }
//
//
//        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view;
//        view=inflater.inflate(R.layout.my_account, container, false);
//        myAccount=(LinearLayout)view.findViewById(R.id.my_account_liner);
//        name=(TextView)view.findViewById(R.id.user_name_id);
//        email=(TextView)view.findViewById(R.id.user_emial_id);
//        imageView=(CircleImageView) view.findViewById(R.id.user_image);
//        Bundle bundle =getArguments();
//        if (bundle != null) {
//            String key_name =bundle.getString("name");
//            String key_email =bundle.getString("email");
////            String key_image=bundle.getString("image");
////            Toast.makeText(getContext(),"Lund"+name, Toast.LENGTH_SHORT).show();
//            name.setText(key_name);
//            email.setText(key_email);
////            imageView.setImageBitmap(key_image);
//
//        }
//
//        final ArrayList<Get_Account_Item> name=new ArrayList<Get_Account_Item>();
//        name.add(new Get_Account_Item("My Orders","Apple",R.drawable.order_icon));
//        name.add(new Get_Account_Item("My Wallets","0.0",R.drawable.wallet));
//        name.add(new Get_Account_Item("Notification","",R.drawable.notifi));
//        name.add(new Get_Account_Item("Refer & Run","",R.drawable.rupee));
//        name.add(new Get_Account_Item("My Gift Cards","",R.drawable.gift));
//        name.add(new Get_Account_Item("Change Password","",R.drawable.ch_pass));
//        name.add(new Get_Account_Item("Log Out","",R.drawable.logout));
//        account_items = new ArrayList<Get_Account_Item>();
//        listView=(ListView)view.findViewById(R.id.list);
//        My_Account_Adapter adapter=new My_Account_Adapter(getContext(),name,R.color.pure_white);
//
//        listView.setAdapter(adapter);
//        change_profile=(TextView)view.findViewById(R.id.change_id);
//        upadteProfile();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//              Get_Account_Item accountItem=name.get(i);
//              accountItem.getNames();
//                Toast.makeText(getContext(),accountItem.getNames(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        return view;
//    }


//    public void upadteProfile(){
//        final Genral_Info genral=new Genral_Info();
//        change_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadFragment(genral);
//            }
//        });
//    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void insert_Data(String p_name){
        db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite_DataConnection.product_name, p_name);
//        contentValues.put(SQLite_DataConnection.product_rate, p_rate);
//        contentValues.put(SQLite_DataConnection.product_qty, p_qty);
//        contentValues.put(SQLite_DataConnection.product_combo, p_combo);
        db.insert(SQLite_DataConnection.TABLE_NAME, null, contentValues);
        Toast.makeText(My_Account.this,"Product Added", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Item_with_Images itemWithImages=new Item_with_Images();
//
        int id=item.getItemId();
        if(id==R.id.back_id){
//
            loadFragment(itemWithImages);

        }
        return super.onOptionsItemSelected(item);
    }

public void aa(){
    toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_navigation_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My_Account.this, Main2Activity.class);
                intent.putExtra("user_name",get_username);
                intent.putExtra("user_email",get_email);
                startActivity(intent);
            }
        });
}

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        MenuItem register = menu.findItem(R.id.back_id);
//        if(name.getText().toString().equalsIgnoreCase("Name"))
//        {
//            register.setVisible(true);
//            Toast.makeText(My_Account.this,"Jannat", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            register.setVisible(false);
//        }
//    }

    public void getCart(){
        db = openHelper.getReadableDatabase();

        c=db.rawQuery("SELECT*FROM "+SQLite_DataConnection.TABLE_NAME,null);
        int nameIndex=c.getColumnIndex(SQLite_DataConnection.product_name);
        if (c!=null){
            if(c.moveToFirst()){
                do{
                    Log.e("Name",c.getString(nameIndex));
//                    Log.e("Age",Integer.toString(c.getInt(ageIndex)));
                }while ( c.moveToNext());
            }


        }
    }
}



