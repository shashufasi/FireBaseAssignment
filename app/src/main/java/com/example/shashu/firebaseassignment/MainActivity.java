package com.example.shashu.firebaseassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    EditText txtName;
    Button btnAddArtist;
    Spinner spinGenre;
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");
        //Finding Controls By Ids and Referencing them...
        txtName = findViewById(R.id.txtName);
        btnAddArtist = findViewById(R.id.btnAddArtist);
        spinGenre = findViewById(R.id.spinGenre);

        //Adding Click Listener to Button Add Artist...
        btnAddArtist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addArtist();
            }
        });
    }

    private void addArtist() {
        //Getting Values from controls
        String name = txtName.getText().toString().trim();
        String genre = spinGenre.getSelectedItem().toString();

        //checking if user selected any value OR not...

        if (!TextUtils.isEmpty(name)) {

            String id = databaseArtists.push().getKey();
            Artist artist = new Artist(id, name, genre);
            databaseArtists.child(id).setValue(artist);
            Toast.makeText(this, "Artist Added", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "You should Enter Name", Toast.LENGTH_LONG).show();
        }
    }
}
