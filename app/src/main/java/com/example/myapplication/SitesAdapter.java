package com.example.myapplication;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import static android.content.Context.CLIPBOARD_SERVICE;

public class SitesAdapter extends RecyclerView.Adapter<SitesAdapter.SitesViewHolder> {
ArrayList<String> s,p;
Context context;

    public SitesAdapter(ArrayList<String> s, ArrayList<String> p, Context context) {
        this.s = s;
        this.p = p;
        this.context = context;
    }

    @NonNull
    @Override
    public SitesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.sites_item,viewGroup,false);
        return new SitesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SitesViewHolder sitesViewHolder, int i) {
        sitesViewHolder.site.setText(s.get(i));
        sitesViewHolder.pass.setText(p.get(i));
        sitesViewHolder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager = (ClipboardManager)context.getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text",sitesViewHolder.pass.getText());
                manager.setPrimaryClip(clipData);
                Toast.makeText(context, "Copied !!!", Toast.LENGTH_SHORT).show();
            }
        });
        sitesViewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st=sitesViewHolder.pass.getText().toString();
                if(st.equals(null)||st.equals("")) {
                    sitesViewHolder.pass.setError("Password should not be Empty !!!");
                }
                else {
                    DBHelper dbHelper = new DBHelper(context);
                    boolean isUpdated = dbHelper.updateData(i + 1, s.get(i), sitesViewHolder.pass.getText().toString());
                    if (isUpdated)
                        Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return s.size();
    }
    public class SitesViewHolder extends RecyclerView.ViewHolder
    {
        TextView site;
        TextInputEditText pass;
        Button copy,update;
        public SitesViewHolder(@NonNull View itemView) {
            super(itemView);
            site=itemView.findViewById(R.id.site_name_tv);
            pass=itemView.findViewById(R.id.pass_et);
            copy=itemView.findViewById(R.id.copy_btn);
            update=itemView.findViewById(R.id.update_btn);
        }
    }
}
