package com.freshorganickart.arpitkanda.trainproject;


import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item_with_Images extends Fragment{
    static TextView mDotsText[];
    private int mDotsCount;
    private LinearLayout mDotsLayout;
    Gallery gallery;
    Statement st;
    ResultSet rs;
//    ProgressDialog progressDialog;
//    Main2Activity main2Activity=new Main2Activity();
    PreparedStatement pst;
    private RecyclerView recyclerView;
//    ConnectionClass connn=new ConnectionClass();
    int number = 0;
//    final ArrayList<Get_Items> array_name=new ArrayList<Get_Items>();
    String[] names={"Fruits & Vegitables","Fish & Meat","Milk & Dairy","Grocery"};
    int[] images={R.drawable.category_fruits,R.drawable.category_meat
            ,R.drawable.category_milk,R.drawable.category_grocery};
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.layout_recyclerview_list, container, false);
//        main2Activity.progressDialog.dismiss();
        gallery = (Gallery)view.findViewById(R.id.galary);
        gallery.setAdapter(new ImageAdapter(getContext()));
        setDots();
        mDotsLayout = (LinearLayout)view.findViewById(R.id.image_count);

        mDotsCount = gallery.getAdapter().getCount();
        mDotsText = new TextView[mDotsCount];
        for (int i = 0; i < mDotsCount; i++) {
            mDotsText[i] = new TextView(getContext());
            mDotsText[i].setText(".");
            mDotsText[i].setTextSize(45);
            mDotsText[i].setTypeface(null, Typeface.BOLD);
            mDotsText[i].setTextColor(android.graphics.Color.GRAY);
            mDotsLayout.addView(mDotsText[i]);
        }
        recyclerView = view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Item_Adapter adapter=new Item_Adapter(getContext(),names,images);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }
    public static Item_with_Images newInstance(String param1, String param2) {
        Item_with_Images fragment = new Item_with_Images();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public void setDots(){
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
            @Override
            public void onItemSelected(AdapterView arg0, View arg1, int arg2,
                                       long arg3) {
                // TODO Auto-generated method stub
                for (int i = 0; i < mDotsCount; i++) {
                    Item_with_Images.mDotsText[i].setTextColor(Color.GRAY);
                }

                Item_with_Images.mDotsText[arg2].setTextColor(Color.GREEN);

            }
        });
    }
    }

