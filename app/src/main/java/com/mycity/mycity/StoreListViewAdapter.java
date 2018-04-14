package com.mycity.mycity;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class StoreListViewAdapter extends ArrayAdapter<Store> {
    private Context context;

    public StoreListViewAdapter(Context context, int resourceId, List<Store> items) {
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

        holder.StoreView.setText(store.getName() + ": " + store.getCity() );

        return convertView;
    }

}
