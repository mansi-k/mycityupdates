package com.mycity.mycity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewStoreActivity extends AppCompatActivity {

    public static String[] cities = { "chembur", "ghatkopar", "kamothe", "vashi", "thane", "dadar" };
    String[] cats = { "electronics", "clothing", "appetite", "kids", "books" };
    DatabaseReference dbcity;
    Button NSButton;
    EditText NameHolder;
    Spinner cityHolder, catHolder;
    public String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_store);

        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        //user = intent.getStringExtra(MainActivity.CurUser);

        user = MainActivity.CurUser;

        cityHolder = (Spinner) findViewById(R.id.selectcity);
        catHolder = (Spinner) findViewById(R.id.selectcat);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,cities);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        cityHolder.setAdapter(aa);

        ArrayAdapter ab = new ArrayAdapter(this,android.R.layout.simple_spinner_item,cats);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        catHolder.setAdapter(ab);

        NameHolder = (EditText) findViewById(R.id.newstorename);


    }

    public void addnewstore(View view) {
        dbcity = FirebaseDatabase.getInstance().getReference("stores/");
        createStore();
    }

    private void createStore() {
        String city = cityHolder.getSelectedItem().toString();
        String cat = catHolder.getSelectedItem().toString();
        String name = NameHolder.getText().toString();

        String pid = dbcity.push().getKey();
        Store store = new Store(cat, city,name, user);
        Log.d("OWNER","OWNER is "+user);
        dbcity.child(pid).setValue(store);

        NameHolder.setText("");

        Toast.makeText(this, "Store Created", Toast.LENGTH_SHORT).show();
    }
}
