package com.example.taquio.trasearch.Samok;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.taquio.trasearch.BusinessProfile.Material;
import com.example.taquio.trasearch.R;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Edward on 15/03/2018.
 */

public class BusinessAdd extends AppCompatActivity {

    private EditText etMat;
    private Button btMat;
    private String key;
    private static final FirebaseDatabase DATABASE = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busiess_add_activity);

        etMat = findViewById(R.id.etMaterial);
        btMat = findViewById(R.id.btnMaterial);

        key = DATABASE.getReference().push().getKey();
        btMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Material material = new Material(etMat.getText().toString());
                DATABASE.getReference().child("Materials").child(key).setValue(material);
            }
        });
    }
}