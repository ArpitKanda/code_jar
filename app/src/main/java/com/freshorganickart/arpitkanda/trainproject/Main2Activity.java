package com.freshorganickart.arpitkanda.trainproject;

import android.app.ProgressDialog;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.drawable.VisibilityAwareDrawable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.Connection;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GoogleApiClient.OnConnectionFailedListener {
    Fragment fragment;
    BottomNavigationView bottom;
    EditText searchEditText;
    ConnectionClass connectionClass;
    BottomNavigationMenuView bottomNavigationMenuView;
    ImageView GoogleImage;
    LinearLayout myAccount;
    TextView Name;
//    ProgressBar progressBar;
//    MyCountDownTimer myCountDownTimer;
    TextView EMail;
    GoogleApiClient googleApiClient;
    private static final int REQ_CODE=9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setTitle("FRESH ORGENIC CART");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        progressBar=(ProgressBar)findViewById(R.id.progressbar_3);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        searchEditText=(EditText)findViewById(R.id.edit_search);
//        myAccount=(LinearLayout)findViewById(R.id.my_account_liner);
//        searchEditText.setFocusable(false);
        searchEditText.setCursorVisible(false);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        bottomNavigationMenuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(3);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        View badge = LayoutInflater.from(this)
                .inflate(R.layout.notification_badge, bottomNavigationMenuView, false);

        itemView.addView(badge);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        Name = (TextView)header.findViewById(R.id.google_name);
        EMail=(TextView)header.findViewById(R.id.google_email);
        connectionClass = new ConnectionClass();
        (new DBConnect()).execute(null, null, null);
//        searchEditText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                searchEditText.setFocusable(true);
//                searchEditText.setCursorVisible(true);
//            }
//        });


        Bundle bundle =getIntent().getExtras();
        if (bundle != null) {
            String key_name =bundle.getString("user_name");
            String key_email =bundle.getString("user_email");
            Name.setText(key_name);
            EMail.setText(key_email);
        }
        fragment = new Item_with_Images();
        loadFragment(fragment);
        textFocus();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new Item_with_Images();
                    loadFragment(fragment);

                    return true;
                case R.id.navigation_dashboard:
//                    toolbar.setTitle("My Gifts");
                    fragment = new Item_Categorey();
                    loadFragment(fragment);
//                    progressDialog.dismiss();
                    return true;
                case R.id.navigation_notifications:
//                    toolbar.setTitle("Cart");
                    fragment = new All_Product_Categorey_Container();
                    loadFragment(fragment);
//                    progressDialog.dismiss();
                    return true;
                case R.id.cart:
//                    toolbar.setTitle("Profile");
                    fragment = new Cart_Item();
                    loadFragment(fragment);
//                    progressDialog.dismiss();
                    return true;

            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public  void textFocus(){
        searchEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!(searchEditText.getText().toString().equalsIgnoreCase(""))) {
                    if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
//                et_mobile.setText("");
                        searchEditText.setFocusableInTouchMode(true);
                        searchEditText.setFocusable(true);
                        searchEditText.requestFocus();
                    }
//            return true; // return is important...
                }
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
//        MenuItem item =menu.findItem(R.id.login_bar);
//        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Login_Register login_register=new Login_Register();
        int id=item.getItemId();
        if(id==R.id.login_bar){
            loadFragment(login_register);
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    class DBConnect extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection conn = connectionClass.conn();
                Toast.makeText(Main2Activity.this, "GET CONNECTION"+conn,
                        Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e("GET SOME ERRORS", ex.toString());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Main2Activity.this, "Synchronising",
                    "Listview Loading! Please Wait...", true);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_offers) {

        } else if (id == R.id.nav_item3) {

        } else if (id == R.id.nav_item4) {

        } else if (id == R.id.nav_item6) {

        }else if (id == R.id.my_wishlist) {

        }else if (id == R.id.my_cart) {

        }
        else if (id == R.id.my_account) {
//            loadFragment(fragment);


        }else {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
