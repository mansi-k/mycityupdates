package com.mycity.mycity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CityStoresActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spareas = (Spinner) findViewById(R.id.selectarea);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_stores);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,NewStoreActivity.cities);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spareas.setAdapter(aa);

        spareas.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),NewStoreActivity.cities[position] , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
