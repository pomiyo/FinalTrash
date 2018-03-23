package com.example.taquio.trasearch.Samok;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taquio.trasearch.R;
import com.example.taquio.trasearch.Utils.BottomNavigationViewHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Del Mar on 2/16/2018.
 */

public class UserJunkShopView extends AppCompatActivity {

    private static final String TAG = "BusinessProfile";
    private Context mContext = UserJunkShopView.this;
    private static final int ACTIVITY_NUM = 0;

    TextView tvName, tvEmail, tvMobile, tvPhone, tvLocation;
    Button btnBuy, btnSell, btnEdit,btnRoute;
    ImageView verify, notVerify;
    CircleImageView profPicImage;
    private BottomNavigationViewEx bottomNavigationView;
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;
    String user_id;
    String verifier;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_activity_profile);
        Log.d(TAG, "onCreate: business profile 2 sstarting");


        final Junkshop bundle = (Junkshop) getIntent().getParcelableExtra("busprofile");
        Toast.makeText(mContext, "" +bundle.getBsnBusinessName(), Toast.LENGTH_SHORT).show();

//        setupViewPager();
        tvName = (TextView) findViewById(R.id.busEditUser);
        tvEmail = (TextView) findViewById(R.id.busUserEmail);
        tvMobile = (TextView) findViewById(R.id.busUserNumber);
        tvPhone = (TextView) findViewById(R.id.busTele);
        tvLocation = (TextView) findViewById(R.id.busLoc);
        btnBuy = (Button) findViewById(R.id.btnBuy);
        btnSell = (Button) findViewById(R.id.btnSell);
        btnRoute = findViewById(R.id.route);
        btnEdit = (Button) findViewById(R.id.busBtnEdit);
        bottomNavigationView = findViewById(R.id.bottomNavViewBar);

        verify = (ImageView) findViewById(R.id.imVerify);
        notVerify = (ImageView) findViewById(R.id.imNotVerify);
        profPicImage = (CircleImageView) findViewById(R.id.busImageView10);

        setupBottomNavigationView();
//        btnBuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(UserJunkShopView.this, BusinessBuy.class);
//                startActivity(i);
//            }
//        });
//
//        btnSell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(UserJunkShopView.this, BusinessBuy.class);
//                startActivity(i);
//            }
//        });
//
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mContext, BusinessEdit.class);
//                startActivity(i);
//            }
//        });
        //pass data to MapActivity
        btnRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Junkshop junkshop = (Junkshop) getIntent().getParcelableExtra("busprofile");
                Intent i = new Intent(UserJunkShopView.this, MapActivity.class);
                i.putExtra("CallFrom", "fromprofile");
                i.putExtra("BusinessDetail", bundle);
                startActivity(i);
            }
        });

        tvName.setText(bundle.getBsnBusinessName());
        tvEmail.setText(bundle.getBsnEmail());
        tvMobile.setText(bundle.getBsnMobile());
        tvPhone.setText(bundle.getBsnPhone());
        tvLocation.setText(bundle.getBsnLocation());
        Picasso.with(UserJunkShopView.this).load(bundle.getImage())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.drawable.man)
                .into(profPicImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(UserJunkShopView.this)
                                .load(bundle.getImage())
                                .placeholder(R.drawable.man)
                                .into(profPicImage);

                    }
                });
        verifier = bundle.getVerify().toString();

        if(verifier.equals("true")) {
            verify.setVisibility(View.VISIBLE);
            notVerify.setVisibility(View.GONE);
        }else if(verifier.equals("false")) {
            verify.setVisibility(View.GONE);
            notVerify.setVisibility(View.VISIBLE);
        }
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        user_id = currentUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(bundle.getUserId());

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(final DataSnapshot dataSnapshot) {
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(mContext,this ,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}
