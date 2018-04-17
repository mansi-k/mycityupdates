package com.mycity.mycity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.mycity.mycity.StoresFragment.STORE_NAME;
import static com.mycity.mycity.StoresFragment.STORE_CAT;
import static com.mycity.mycity.StoresFragment.STORE_CITY;

public class StoreDetailsActivity extends AppCompatActivity {

    String storename, storecategory, storecity;
    public static String STOREN = "storename";
    TextView nstv, cystv, cgstv;
    com.google.firebase.database.DatabaseReference dbcity;
    private StorePostListViewAdapter mPostListAdapter;
    private ListView mPostListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        Intent intent = getIntent();
        storename = intent.getStringExtra(STORE_NAME);
        storecategory = intent.getStringExtra(STORE_CAT);
        storecity = intent.getStringExtra(STORE_CITY);
        nstv = (TextView) findViewById(R.id.store_name);
        cgstv = (TextView) findViewById(R.id.store_category);
        cystv = (TextView) findViewById(R.id.store_city);
        nstv.setText(storename);
        cgstv.setText(storecategory);
        cystv.setText(storecity);
        StorePostListViewAdapter.spnFlag = "";
        mPostListAdapter = new StorePostListViewAdapter(this,
                R.layout.stores_list, new ArrayList<Post>());
        mPostListView = (ListView) findViewById(R.id.list_store_posts);
        mPostListView.setAdapter(mPostListAdapter);

        //Firebase
        dbcity = FirebaseDatabase.getInstance().getReference("posts/");

        Query q = dbcity.orderByChild("store").equalTo(storename);

        q.addChildEventListener(new ChildEventListener() {
            // read Stores

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Do something with the individual node here`enter code here`
                Post post = dataSnapshot.getValue(Post.class);
                //Log.d("SNAP",post.toString());
                updateStorePostList(post);
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
        
    }

    public void gotoPost(View view) {
        Intent intent = new Intent(StoreDetailsActivity.this, PostActivity.class);
        intent.putExtra(STOREN,storename);
        intent.putExtra("STORECTY",storecity);
        intent.putExtra("STORECAT",storecategory);
        startActivity(intent);
    }

    private void updateStorePostList( Post post ) {
        int MAX_S = 1000;
        if ( mPostListAdapter.getCount() >= MAX_S ) {
            mPostListAdapter.remove(mPostListAdapter.getItem(0));
        }
        mPostListAdapter.insert(post, 0);
    }

}
