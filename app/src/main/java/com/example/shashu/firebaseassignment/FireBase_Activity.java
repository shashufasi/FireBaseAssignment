package com.example.shashu.firebaseassignment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBase_Activity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    EditText txtName;
    Button btnAddArtist;
    Spinner spinGenre;
    DatabaseReference databaseArtists;
    List<Artist> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        context = FireBase_Activity.this;
        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");

        //Finding Controls By Ids and Referencing them...
        txtName = findViewById(R.id.txtName);
        btnAddArtist = findViewById(R.id.btnAddArtist);
        spinGenre = findViewById(R.id.spinGenre);
        recyclerView = findViewById(R.id.recyclerList);
        artistList = new ArrayList<>();
        //Adding Click Listener to Button Add Artist...
        btnAddArtist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtName.getWindowToken(), 0);
                addArtist();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseArtists.addValueEventListener(new ValueEventListener() {

            //OnDataChange method will be Executed Every Time when data has been changed...
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList.clear();
                for (DataSnapshot artistSnapShot : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapShot.getValue(Artist.class);
                    artistList.add(artist);
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(FireBase_Activity.this));
                recyclerView.setAdapter(new ArtistAdapter(FireBase_Activity.this, artistList));
            }

            //OnCancelled will be executed everytime an ERROR occurs...
            @Override
            public void onCancelled(DatabaseError databaseError) {

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
            txtName.setText("");
        } else {
            Toast.makeText(this, "You should Enter Name", Toast.LENGTH_LONG).show();
        }
    }
}
