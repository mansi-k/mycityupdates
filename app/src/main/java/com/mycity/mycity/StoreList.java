package com.mycity.mycity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class StoreList extends ArrayAdapter<Store> {

    DatabaseReference dbcity;
    private Activity context;
    List<Store> Stores;

    public StoreList(Activity context, List<Store> Stores) {
        super(context, R.layout.activity_my_stores, Stores);
        this.context = context;
        this.Stores = Stores;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_my_stores, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        Store Store = Stores.get(position);
        textViewName.setText(Store.getName());
        textViewGenre.setText(Store.getCat());

        return listViewItem;
    }

}
