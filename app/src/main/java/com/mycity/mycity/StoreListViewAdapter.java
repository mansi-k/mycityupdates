package com.mycity.mycity;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StoreListViewAdapter extends ArrayAdapter<Store> {
    private Context context;

    public StoreListViewAdapter(FragmentActivity context, int resourceId, ArrayList<Store> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /* private view holder class */
    private class ViewHolder {
        TextView StoreView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Store store = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.stores_list, null);
            holder = new ViewHolder();
            holder.StoreView = (TextView) convertView.findViewById(R.id.storeitem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.StoreView.setText(store.getName() + ": " + store.getCategory() );

        return convertView;
    }

}
