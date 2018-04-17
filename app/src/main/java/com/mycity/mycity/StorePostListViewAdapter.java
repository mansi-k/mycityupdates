package com.mycity.mycity;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class StorePostListViewAdapter extends ArrayAdapter<Post> {
    private Context context;
    public static String spnFlag = "";

    public StorePostListViewAdapter(Activity context, int resourceId, List<Post> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /* private view holder class */
    private class ViewHolder {
        TextView PostView1;
        TextView PostView2;
        TextView PostView3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Post post = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.store_post_list, null);
            holder = new ViewHolder();
            holder.PostView1 = (TextView) convertView.findViewById(R.id.storeposttitle);
            holder.PostView2 = (TextView) convertView.findViewById(R.id.storepostdescmy);
            holder.PostView3 = (TextView) convertView.findViewById(R.id.psname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.PostView1.setText(post.getTitle());
        holder.PostView2.setText(post.getDesc());
        if(spnFlag == "show")
            holder.PostView3.setText(post.getStore());
        Log.d("DESC","DESC : "+post.getDesc());

        return convertView;
    }

}
