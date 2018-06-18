package com.freshorganickart.arpitkanda.trainproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<String> tab=new ArrayList<>();
    public SampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
//        this.context = context;
    }
    public void addFragment(Fragment fragment,String title){
        this.fragments.add(fragment);
        this.tab.add(title);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab.get(position);
    }
}
