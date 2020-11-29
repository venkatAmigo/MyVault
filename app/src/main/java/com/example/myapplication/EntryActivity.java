package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class EntryActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputEditText site,pass;
    MaterialButton add;
    DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        site=findViewById(R.id.site);
        pass=findViewById(R.id.password);
        add=findViewById(R.id.add);
        add.setOnClickListener(this);
        myDb=new DBHelper(this);

    }

    @Override
    public void onClick(View v) {
       String s=site.getText().toString();
       String p=pass.getText().toString();
       if(s.equals(null)||s.equals(""))
       {
           site.setError("Please Enter Site Name");
       }
       else if(p.equals(null)||p.equals(""))
       {
           pass.setError("Please Enter Password");

       }
       else{
           int nr=myDb.numberOfRows();
           if(nr==0)
               nr=1;
           boolean isInserted= myDb.insertData(nr+1,s,p);
           if(isInserted)
           {
               Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
               site.setText("");
               pass.setText("");
           }
           else{
               Toast.makeText(this, "Insertion Failed", Toast.LENGTH_SHORT).show();
           }
       }
    }
}