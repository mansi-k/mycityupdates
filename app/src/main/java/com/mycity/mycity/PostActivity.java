package com.mycity.mycity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {

    String UQNHolder;
    DatabaseReference dbcity;
    Button postButton;
    EditText titleHolder, descHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        String store = intent.getStringExtra(StoreDetailsActivity.STOREN);
        //String store = "dell";

        dbcity = FirebaseDatabase.getInstance().getReference("posts/"+store);

        titleHolder = (EditText) findViewById(R.id.posttitle);
        descHolder = (EditText) findViewById(R.id.postdesc);
        postButton = (Button) findViewById(R.id.postbutton);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPost();
            }
        });
    }

    private void addPost() {
        String title = titleHolder.getText().toString().trim();
        String desc = descHolder.getText().toString().trim();


        String pid = dbcity.push().getKey();
        Post post = new Post(title, desc);
        dbcity.child(pid).setValue(post);

        titleHolder.setText("");
        descHolder.setText("");

        Toast.makeText(this, "Posted", Toast.LENGTH_SHORT).show();
    }
}

