package com.freshorganickart.arpitkanda.trainproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Item_Holder extends RecyclerView.ViewHolder{
    //VIEWS
    ImageView img;
    TextView product_name;
    TextView rate;
    TextView qty_plus;
    TextView qty_minus;
    TextView qty;
    int number =0;
//    ItemClickListener itemClickListener;
    public Item_Holder(View itemView) {
        super(itemView);
        //ASSIGNING VIEWS
        img= (ImageView) itemView.findViewById(R.id.img_container);
        product_name= (TextView) itemView.findViewById(R.id.item_name);
//        rate= (TextView) itemView.findViewById(R.id.item_rate);
        qty= (TextView) itemView.findViewById(R.id.qty);
//        qty_plus= (TextView) itemView.findViewById(R.id.qty_plus);
//        qty_minus= (TextView) itemView.findViewById(R.id.qty_minus);
//        itemView.setOnClickListener(this);
//        qtyPlus();
//        qtyMinus();
    }

    public void qtyPlus(){
        qty_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number=number+1;
                qty.setText(String.valueOf(number));
            }
        });
    }

    public void qtyMinus(){
        qty_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number=number-1;
                qty.setText(String.valueOf(number));
                if(number==0){
                    number=0;
                }
            }
        });
    }


    //WHEN CLICKED
//    @Override
//    public void onClick(View v) {
//        this.itemClickListener.onItemClick(v,getLayoutPosition());
//    }
//
//    //SHALL BE CALLED OUTSIDE
//    public void serItemClickListener(ItemClickListener ic)
//
//    {
//        this.itemClickListener=ic;
//    }
//    public interface ItemClickListener {
//        void onItemClick(View v,int pos);
//    }
}

