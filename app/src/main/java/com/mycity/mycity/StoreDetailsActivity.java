package com.mycity.mycity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.mycity.mycity.StoresFragment.STORE_NAME;
import static com.mycity.mycity.StoresFragment.STORE_CAT;
import static com.mycity.mycity.StoresFragment.STORE_CITY;

public class StoreDetailsActivity extends AppCompatActivity {

    String storename, storecategory, storecity;
    public static String STOREN = "storename";
    TextView nstv, cystv, cgstv;

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
    }

    public void gotoPost(View view) {
        Intent intent = new Intent(StoreDetailsActivity.this, PostActivity.class);
        intent.putExtra(STOREN,storename);
        startActivity(intent);
    }
}
