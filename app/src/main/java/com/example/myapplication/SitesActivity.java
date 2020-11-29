package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class SitesActivity extends AppCompatActivity {
    DBHelper mydb;
    RecyclerView recyclerView;
    SitesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);
        mydb = new DBHelper(this);
        ArrayList<String> s_list = mydb.getAll();
        ArrayList<String> p_list = mydb.getAllP();
        recyclerView=findViewById(R.id.sites_recycle);
        adapter=new SitesAdapter(s_list,p_list,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}