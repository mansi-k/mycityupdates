package com.mycity.mycity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    public static String curuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        //curuser = intent.getStringExtra(MainActivity.CurUser);
        curuser = MainActivity.CurUser;

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.my_interests:
                                selectedFragment = CityPostsFragment.newInstance();
                                break;
                            case R.id.all_updates:
                                selectedFragment = CityStoresFragment.newInstance();
                                break;
                            case R.id.my_store:
                                selectedFragment = StoresFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, CityPostsFragment.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    public void addStore(View view) {
        Intent intent = new Intent(HomeActivity.this, NewStoreActivity.class);
        intent.putExtra("username",curuser);
        startActivity(intent);
    }

}