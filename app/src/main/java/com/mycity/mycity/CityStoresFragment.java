package com.mycity.mycity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class CityStoresFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spareas;
    private ListView mStoresListView;
    private static final Integer MAX_S = 1000;
    View rootView;
    com.google.firebase.database.DatabaseReference dbcity;

    private StoreListViewAdapter mStoreListAdapter;

    public CityStoresFragment() {
        // Required empty public constructor
    }


    public static CityStoresFragment newInstance() {
        CityStoresFragment fragment = new CityStoresFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_city_stores, container, false);
        spareas = (Spinner) rootView.findViewById(R.id.selectarea);

        ArrayAdapter aa = new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,NewStoreActivity.cities);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spareas.setAdapter(aa);

        spareas.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), NewStoreActivity.cities[position] , Toast.LENGTH_SHORT).show();
        mStoreListAdapter = new StoreListViewAdapter(getActivity(),
                R.layout.stores_list, new ArrayList<Store>());
        mStoresListView = (ListView) rootView.findViewById(R.id.listCityStores);
        mStoresListView.setAdapter(mStoreListAdapter);


        //Firebase
        dbcity = FirebaseDatabase.getInstance().getReference("stores/");
        Query q = dbcity.orderByChild("city").equalTo(NewStoreActivity.cities[position]);

        q.addChildEventListener(new ChildEventListener() {
            // read Stores

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Do something with the individual node here`enter code here`
                    Store store = dataSnapshot.getValue(Store.class);
                    Log.d("SNAP", store.getName());
                    updateAreaStoreList(store);
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

            }

        });

    }

    private void updateAreaStoreList( Store Store ) {
        if ( mStoreListAdapter.getCount() >= MAX_S ) {
            mStoreListAdapter.remove(mStoreListAdapter.getItem(0));
        }
        mStoreListAdapter.insert(Store, 0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
