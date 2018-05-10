package com.example.arpitkanda.trainproject;

import android.content.ClipData;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Item_Adapter extends RecyclerView.Adapter<Item_Holder> {
    //PROPERTIES
    Context c;
    String[] players;
    String[] positions;
//    String[] positions_1;
    String[] positions_2;
    //    String[] positions_3;
    int[] imgs;

    //CONSTRUCTOR
    public Item_Adapter(Context ctx, String[] names,int[] images)

    {
        //ASSIGN THEM AFTER BEING PASSED IN
        this.c=ctx;
        this.players=names;
//        this.positions=positions;
//        this.positions_1=positions_1;
//        this.positions_2=positions_2;
//        this.positions_3=positions_3;
        this.imgs=images;
    }

    //WHEN VIEWHOLDER IS BEING CREATED
    @Override
    public Item_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //INFLATE XML AND HOLD IN VIEW
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_item_container,null);
        //HOLDER
        Item_Holder holder=new Item_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(Item_Holder holder,final int position) {
        holder.img.setImageResource(imgs[position]);
        holder.product_name.setText(players[position]);

        //SET THE ITEM CLICK LISTENER
        holder.serItemClickListener(new Item_Holder.ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Snackbar.make(v,players[position]+" : "+positions[position],Snackbar.LENGTH_SHORT).show();
            }
        } );


    }



    //TOTAL NUM OF ITEMS
    @Override
    public int getItemCount() {
        return players.length;
    }
}
