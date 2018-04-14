package com.mycity.mycity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

 
public class StoresFragment extends Fragment {

    com.google.firebase.database.DatabaseReference dbcity;
    private android.app.Activity context;
    java.util.List<Store> Stores;
    android.widget.ListView listViewStores;

    public static StoresFragment newInstance() {
        StoresFragment fragment = new StoresFragment();
        return fragment;
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbcity = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("posts/stores");

        dbcity.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous Store list
                //Stores.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting Store
                    Store Store = postSnapshot.getValue(Store.class);
                    //adding Store to the list
                   // Stores.add(Store);
                }

                //creating adapter
                //StoreList StoreAdapter = new StoreList(HomeActivity.class, Stores);
                //attaching adapter to the listview
                //listViewStores.setAdapter(StoreAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //listViewStores = (ListView) getView().findViewById(R.id.listViewStores);
        return inflater.inflate(R.layout.fragment_stores, container, false);
    }
}
