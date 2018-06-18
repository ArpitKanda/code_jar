package com.freshorganickart.arpitkanda.trainproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Item_Categorey extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;

    HashMap<String, List<String>> expandableListDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.item_category_list, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
//        expandableListDetail = ExpandableListDataPump.getData();
        final ArrayList<ExpandableListDataPump> team = getData();

        //CREATE AND BIND TO ADAPTER
        CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(getContext(), team);
        expandableListView.setAdapter(adapter);
        onExpand();

        return view;
    }

    private ArrayList<ExpandableListDataPump> getData() {

        ExpandableListDataPump t1 = new ExpandableListDataPump("Fruits & Vegitables");
        t1.players.add("Apple");
        t1.players.add("Orange");
        t1.players.add("Potato");
        t1.players.add("Onion");

        ExpandableListDataPump t2 = new ExpandableListDataPump("Grocery");
        t2.players.add("Sugar");
        t2.players.add("Rice");
        t2.players.add("Oil");
        t2.players.add("Dal");

        ExpandableListDataPump t3 = new ExpandableListDataPump("Milk & Dairy");
        t3.players.add("Paneer");
        t3.players.add("Egg");
        t3.players.add("Curd");
        t3.players.add("Cream");


        ExpandableListDataPump t4 = new ExpandableListDataPump("Fish & Meat");
        t4.players.add("Fish");
        t4.players.add("Chicken");
        t4.players.add("Mutton");
        t4.players.add("Combo");

//        ExpandableListDataPump t5 = new ExpandableListDataPump("Mashale");
//        t5.players.add("John Terry");
//        t5.players.add("Eden Hazard");
//        t5.players.add("Diego Costa");
//        t5.players.add("Oscar");


        ArrayList<ExpandableListDataPump> allTeams = new ArrayList<ExpandableListDataPump>();
        allTeams.add(t1);
        allTeams.add(t2);
        allTeams.add(t3);
        allTeams.add(t4);
//        allTeams.add(t5);

        return allTeams;
    }

    public void onExpand() {
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//                Toast.makeText(getContext(), "Fuck", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }
}