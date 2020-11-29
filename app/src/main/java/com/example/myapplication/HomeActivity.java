package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    CardView siteCard,addCard;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        siteCard=findViewById(R.id.sites_card);
        context=this;
        siteCard.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(HomeActivity.this,SitesActivity.class);
                      context.startActivity(intent);
                    }
                }
        );
        addCard=findViewById(R.id.add_card);
        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,EntryActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add_card)
        {
            Intent intent=new Intent(this,EntryActivity.class);
            this.startActivity(intent);

        }
        if(v.getId()==R.id.sites_card)
        {
            Intent intent=new Intent(this,SitesActivity.class);
            this.startActivity(intent);

        }
    }
}