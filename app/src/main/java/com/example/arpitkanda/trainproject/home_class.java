package com.example.arpitkanda.trainproject;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;

public class home_class extends Fragment{

   static TextView mDotsText[];
    private int mDotsCount;
    private LinearLayout mDotsLayout;
    Gallery gallery;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view;
        view=inflater.inflate(R.layout.home, container, false);
        gallery = (Gallery)view.findViewById(R.id.galary);
        gallery.setAdapter(new ImageAdapter(getContext()));
        setDots();
        mDotsLayout = (LinearLayout)view.findViewById(R.id.image_count);
        // here we count the number of images we have to know how many dots we
        // need
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
        return view;
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
                    home_class.mDotsText[i].setTextColor(Color.GRAY);
                }

                home_class.mDotsText[arg2].setTextColor(Color.GREEN);

            }
        });
    }

}
