package com.example.taquio.trasearch.Samok;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.dmallcott.dismissibleimageview.DismissibleImageView;
import com.example.taquio.trasearch.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AdminVerification extends AppCompatActivity {
    private static final String TAG = "AdminVerification";
    String user_id;
    DatabaseReference userDatabase,forVerification;
    FirebaseAuth mAuth;
    private Button ver_IDbtn,ver_Selfiebtn,ver_Skipbtn,ver_UploadExec;
    private ImageView ver_ID,ver_Selfie;
    private Uri selfie,mID;
    private String mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verification);
        mAuth = FirebaseAuth.getInstance();
        userDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        forVerification = FirebaseDatabase.getInstance().getReference().child("ForVerification");


        ver_ID = findViewById(R.id.ver_ID);
        ver_Selfie = findViewById(R.id.ver_Selfie);
        ver_IDbtn = findViewById(R.id.ver_IDbtn);
        ver_Selfiebtn = findViewById(R.id.ver_Selfiebtn);
        ver_Skipbtn = findViewById(R.id.ver_Skipbtn);
        ver_UploadExec = findViewById(R.id.ver_UploadExec);
        user_id  = getIntent().getStringExtra("user_id");
        Log.d(TAG, "onCreate: UserID "+user_id);
        forVerification.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("IDD")||dataSnapshot.hasChild("selfieURL"))
                {
                    Log.d(TAG, "onDataChange: ID URRL"+dataSnapshot
                            .child("IDURL").getValue().toString());
                    ver_Skipbtn.setText("Verify");
                    ver_UploadExec.setText("Revoke");
                    String IDURL = dataSnapshot
                            .child("IDURL").getValue().toString();
                    Picasso.with(AdminVerification.this)
                            .load(IDURL)
                            .placeholder(R.drawable.man)
                            .into(ver_ID);
                    final ImagePopup imagePopup1 = new ImagePopup(AdminVerification.this);
                    imagePopup1.initiatePopupWithPicasso(IDURL);
                    imagePopup1.setBackgroundColor(Color.GREEN);
                    imagePopup1.animate();
                    imagePopup1.setFullScreen(true);

                            ver_ID.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imagePopup1.viewPopup();
                        }
                    });

                    Log.d(TAG, "onDataChange: ID URRL"+dataSnapshot
                            .child("selfieURL").getValue().toString());
                    final String URL = dataSnapshot
                            .child("selfieURL").getValue().toString();

                    Picasso.with(AdminVerification.this)
                            .load(URL)
                            .placeholder(R.drawable.man)
                            .into(ver_Selfie);
                    final ImagePopup imagePopup = new ImagePopup(AdminVerification.this);
                    imagePopup.initiatePopupWithPicasso(URL);
                    imagePopup.setBackgroundColor(Color.GREEN);
                    imagePopup.animate();
                    imagePopup.setFullScreen(true);

                    ver_Selfie.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imagePopup.viewPopup();
                        }
                    });
                    ver_Skipbtn.setEnabled(true);
                    ver_UploadExec.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ver_Skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDatabase.child(user_id).child("isVerified").setValue(true);
                forVerification.child(user_id).child("isVerified").setValue(true);
                startActivity(new Intent(AdminVerification.this,AdminActivity.class));
                finish();
            }
        });


    }
}
