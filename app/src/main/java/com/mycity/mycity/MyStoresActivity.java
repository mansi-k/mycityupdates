package com.mycity.mycity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class MyStoresActivity extends AppCompatActivity {

    DatabaseReference dbcity;
    private Activity context;
    List<Store> Stores;
    ListView listViewStores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stores);
        dbcity = FirebaseDatabase.getInstance().getReference("posts/stores");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        listViewStores = (ListView) findViewById(R.id.listViewStores);
        dbcity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous Store list
                Stores.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting Store
                    Store Store = postSnapshot.getValue(Store.class);
                    //adding Store to the list
                    Stores.add(Store);
                }

                //creating adapter
                //StoreList StoreAdapter = new StoreList(MyStoresActivity.this, Stores);
                //attaching adapter to the listview
                //listViewStores.setAdapter(StoreAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
