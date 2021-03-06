package com.mycity.mycity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class StoresFragment extends Fragment {

    com.google.firebase.database.DatabaseReference dbcity;

    public static final String STORE_NAME = "nameofstore";
    public static final String STORE_CAT = "catofstore";
    public static final String STORE_CITY = "cityofstore";

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
        dbcity = FirebaseDatabase.getInstance().getReference("stores/");

        Query q = dbcity.orderByChild("owner").equalTo(HomeActivity.curuser);

        //Log.d("QRY", HomeActivity.curuser);

        q.addChildEventListener(new ChildEventListener() {
            // read Stores

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Do something with the individual node here`enter code here`
                Store store = dataSnapshot.getValue(Store.class);
                Log.d("SNAP", store.getName());
                updateStoreList(store);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });

        if(mStoresListView!=null) {
            mStoresListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Store item = (Store) mStoreListAdapter.getItem(position);

                    Intent intent = new Intent(view.getContext(), StoreDetailsActivity.class);
                    intent.putExtra(STORE_NAME, item.getName());
                    intent.putExtra(STORE_CAT, item.getCategory());
                    intent.putExtra(STORE_CITY, item.getCity());
                    startActivity(intent);
                }
            });
        }

        return rootView;
    }

    private void updateStoreList( Store Store ) {
        if ( mStoreListAdapter.getCount() >= MAX_S ) {
            mStoreListAdapter.remove(mStoreListAdapter.getItem(0));
        }
        mStoreListAdapter.insert(Store, 0);
    }
}
