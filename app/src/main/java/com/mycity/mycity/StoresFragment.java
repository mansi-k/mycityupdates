package com.mycity.mycity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class StoresFragment extends Fragment {

    com.google.firebase.database.DatabaseReference dbcity;

    private static final Integer MAX_S = 10;

    private StoreListViewAdapter mStoreListAdapter;

    /** List view */
    private ListView mStoresListView;


    public StoresFragment() {
    }

    public static StoresFragment newInstance() {
        StoresFragment fragment = new StoresFragment();
        return fragment;
    }

 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stores, container, false);

        //prepare the list view and adapter
        mStoreListAdapter = new StoreListViewAdapter(getActivity(),
                R.layout.stores_list, new ArrayList<Store>());
        mStoresListView = (ListView) rootView.findViewById(R.id.listViewStores);
        mStoresListView.setAdapter(mStoreListAdapter);

        //Firebase
        dbcity = FirebaseDatabase.getInstance().getReference("stores/mansik");

        // read Stores
        dbcity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot StoreSnapshot : snapshot.getChildren()) {
                    Store store = StoreSnapshot.getValue(Store.class);
                    Log.d("SNAP",store.toString());
                    updateStoreList(store);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        return rootView;
    }

    private void updateStoreList( Store Store ) {
        if ( mStoreListAdapter.getCount() >= MAX_S ) {
            mStoreListAdapter.remove(mStoreListAdapter.getItem(0));
        }
        mStoreListAdapter.insert(Store, 0);
    }
}
