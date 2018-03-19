package com.example.taquio.trasearch.BusinessProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.taquio.trasearch.R;
import com.example.taquio.trasearch.Samok.BusinessShow;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Del Mar on 2/24/2018.
 */

public class BusinessBuy extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference ref;
    String materialName;
    ArrayList<Material> mat = new ArrayList<>();
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_buy_activity);

        btn = (Button) findViewById(R.id.addItem);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<String> materials = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference().child("Materials");
        FirebaseRecyclerAdapter<Material, MaterialHolder> recyclerAdapter = new FirebaseRecyclerAdapter<Material, MaterialHolder>
                (Material.class, R.layout.business_material_info, MaterialHolder.class, ref) {
            @Override
            protected void populateViewHolder(final MaterialHolder viewHolder, Material model, final int position) {
                viewHolder.name.setText(model.getName());

                viewHolder.name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b == true) {
                            materialName = viewHolder.name.getText().toString();
                            materials.add(materialName);
                        }
                    }
                });
            }

        };
        recyclerView.setAdapter(recyclerAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempString="";
                for (int x=0; x <materials.size(); x++) {
                    tempString = tempString + ", " + materials.get(x);
                }
                Toast.makeText(BusinessBuy.this, tempString, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
