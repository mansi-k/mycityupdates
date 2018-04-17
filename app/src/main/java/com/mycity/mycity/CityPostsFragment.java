package com.mycity.mycity;

import android.content.Context;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class CityPostsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spareas;
    private ListView mStoresListView;
    private static final Integer MAX_S = 1000;
    View rootView;
    com.google.firebase.database.DatabaseReference dbcity;
    private StorePostListViewAdapter mPostListAdapter;
    
    public CityPostsFragment() {
        // Required empty public constructor
    }
    
    // TODO: Rename and change types and number of parameters
    public static CityPostsFragment newInstance() {
        CityPostsFragment fragment = new CityPostsFragment();
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
        rootView =  inflater.inflate(R.layout.fragment_city_posts, container, false);
        spareas = (Spinner) rootView.findViewById(R.id.selectareaup);

        ArrayAdapter aa = new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,NewStoreActivity.cities);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spareas.setAdapter(aa);

        spareas.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), NewStoreActivity.cities[position] , Toast.LENGTH_SHORT).show();
        mPostListAdapter = new StorePostListViewAdapter(getActivity(),
                R.layout.stores_list, new ArrayList<Post>());
        mStoresListView = (ListView) rootView.findViewById(R.id.listCityPosts);
        mStoresListView.setAdapter(mPostListAdapter);


        //Firebase
        dbcity = FirebaseDatabase.getInstance().getReference("posts/");
        Query q = dbcity.orderByChild("city").equalTo(NewStoreActivity.cities[position]);

        q.addChildEventListener(new ChildEventListener() {
            // read Stores

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Do something with the individual node here`enter code here`
                Post post = dataSnapshot.getValue(Post.class);
                //Log.d("SNAP", post.getName());
                updateAreaPostList(post);
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

    private void updateAreaPostList( Post post ) {
        StorePostListViewAdapter.spnFlag = "show";
        if ( mPostListAdapter.getCount() >= MAX_S ) {
            mPostListAdapter.remove(mPostListAdapter.getItem(0));
        }
        mPostListAdapter.insert(post, 0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    
}
