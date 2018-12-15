package com.example.shashu.firebaseassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Api_RecyclerView extends AppCompatActivity {

    ArrayList<ApiData> apiData;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api__recycler_view);

        Bundle bundle=getIntent().getExtras();
        apiData=(ArrayList<ApiData>) bundle.getSerializable("data");
        recyclerView=findViewById(R.id.recycler_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Api_Adapter(this,apiData));

    }
}
