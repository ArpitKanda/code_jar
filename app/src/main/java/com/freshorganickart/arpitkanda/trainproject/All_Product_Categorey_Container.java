package com.freshorganickart.arpitkanda.trainproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Arpit Kanda on 08-Jun-18.
 */

public class All_Product_Categorey_Container extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.product_categorey_collection, container, false);


        ViewPager viewPager=(ViewPager)view.findViewById(R.id.viewpager_product);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.product_tabs);
        SampleFragmentPagerAdapter same=new SampleFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        same.addFragment(new Product_In_List(),"All");
        same.addFragment(new Fruits_Vegitables(),"Fruits & Vegitables");
        same.addFragment(new Milk_Dairy(),"Milk & Dairy");
        same.addFragment(new Fish_Meat(),"Fish & Meat");
        same.addFragment(new Grocery(),"Grocery");
        viewPager.setAdapter(same);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
