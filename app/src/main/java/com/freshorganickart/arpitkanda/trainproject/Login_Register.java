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
 * Created by Arpit Kanda on 30-May-18.
 */

public class Login_Register extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.activity_login__register, container, false);


        ViewPager viewPager=(ViewPager)view.findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        SampleFragmentPagerAdapter same=new SampleFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        same.addFragment(new Login(),"Login");
        same.addFragment(new Sign_Up(),"Sing Up");
        viewPager.setAdapter(same);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
