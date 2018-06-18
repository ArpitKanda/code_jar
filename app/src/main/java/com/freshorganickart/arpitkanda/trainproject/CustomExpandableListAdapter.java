package com.freshorganickart.arpitkanda.trainproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arpit Kanda on 05-May-18.
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {



    private Context context;
    //    private List<String> expandableListTitle;
    ArrayList<ExpandableListDataPump> team;
    LayoutInflater inflater;
//    private HashMap<String, List<String>> expandableListDetail;

    public CustomExpandableListAdapter(Context context,ArrayList<ExpandableListDataPump> team) {
        this.context = context;
        this.team=team;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.expandableListTitle = expandableListTitle;
//        this.expandableListDetail = expandableListDetail;
    }


    @Override
    public int getGroupCount() {
        return team.size();
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return team.get(listPosition).players.size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return team.get(listPosition);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return team.get(listPosition).players.get(expandedListPosition);
    }

    @Override
    public long getGroupId(int listPosition) {
        return 0;
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
//        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) this.context.
//                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(R.layout.list_group, null);
            convertView=inflater.inflate(R.layout.list_group, null);
        }
        ExpandableListDataPump t=(ExpandableListDataPump) getGroup(listPosition);

        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        ImageView img=(ImageView) convertView.findViewById(R.id.imageView1);
        String name=t.Name;
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(name);

        if(name=="Fruits & Vegitables")
        {
            img.setImageResource(R.drawable.fruits_n_vegitable);
        }else if(name=="Grocery")
        {
            img.setImageResource(R.drawable.grocery_store);
        }else if(name=="Milk & Dairy")
        {
            img.setImageResource(R.drawable.milks);
        }
        else if(name=="Egg & Meat")
        {
            img.setImageResource(R.drawable.eggs);
        }

        convertView.setBackgroundColor(Color.WHITE);

        return convertView;

    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) this.context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(R.layout.list_item, null);
            convertView=inflater.inflate(R.layout.list_sub_item, null);
        }
        String  child=(String) getChild(listPosition, expandedListPosition);
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
