package com.example.shashu.firebaseassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button btnNetwork;
    Button btnFireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNetwork = findViewById(R.id.btnNetwork);
        btnFireBase = findViewById(R.id.btnFireBase);

        //Network Button click listener...
        btnNetwork.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, NetworkActivity.class);
                startActivity(intent);
            }
        });
        //FireBase Button click listener...
        btnFireBase.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, FireBase_Activity.class);
                startActivity(intent);
            }
        });
    }
}
