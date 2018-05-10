package com.example.arpitkanda.trainproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Item_with_Images extends Fragment{

    String[] names={"Infrastructure IT Solutions","Software & Application Devlopment","Consulting"};
    int[] images={R.drawable.code,R.drawable.code,R.drawable.code,R.drawable.code};
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        final View view;
        view=inflater.inflate(R.layout.item_with_images, container, false);
        RecyclerView rv= (RecyclerView)view.findViewById(R.id.my_recycler_view);
        //SET LAYOUT
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());
        //ADAPTER
        Item_Adapter adapter=new Item_Adapter(getContext(),names,images);
        rv.setAdapter(adapter);
        return view;
    }
}
