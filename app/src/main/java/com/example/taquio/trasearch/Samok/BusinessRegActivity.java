package com.example.taquio.trasearch.Samok;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taquio.trasearch.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

/**
 * Created by Edward on 20/02/2018.
 */

public class BusinessRegActivity extends AppCompatActivity{

    private Context mContext = BusinessRegActivity.this;
    EditText bsnMail, bsnPass = (EditText) findViewById(R.id.bsnPass), bsnConPass, bsnBusinessName, bsnLocation, bsnPhone, bsnMobile;
    Button busContinue;
    TextView regCancel;
    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;


    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();

    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_register1);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener)this)
                .build();

        bsnMail = findViewById(R.id.bsnMail);
        bsnConPass = findViewById(R.id.bsnConPass);
        bsnBusinessName = findViewById(R.id.bsnBusinessName);
        bsnLocation = findViewById(R.id.bsnLocation);
        bsnPhone = findViewById(R.id.bsnPhone);
        bsnMobile = findViewById(R.id.bsnMobile);
        regCancel = findViewById(R.id.regCancel);
        regCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(BusinessRegActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        busContinue = findViewById(R.id.registerCont);
        busContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = bsnMail.getText().toString();
                String pass = bsnPass.getText().toString();
                String conpass = bsnConPass.getText().toString();
                String businessname = bsnBusinessName.getText().toString();
                String location = bsnLocation.getText().toString();
                String phone = bsnPhone.getText().toString();
                String mobile = bsnMobile.getText().toString();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(conpass) && !TextUtils.isEmpty(businessname)
                        && !TextUtils.isEmpty(location) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(mobile)) {

                    Intent i = new Intent (mContext, BusinessRegActivity2.class);
                    i.putExtra("EMAIL",email);
                    i.putExtra("PASS",pass);
                    i.putExtra("BUSINESSNAME",businessname);
                    i.putExtra("LOCATION",location);
                    i.putExtra("PHONE",phone);
                    i.putExtra("MOBILE",mobile);
                    startActivity(i);
                }else {
                    Toast.makeText(mContext, "Please fill up all fields!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stBuilder = new StringBuilder();
                String address = String.format("%s", place.getAddress());

                bsnLocation.setText(address);
            }
        }
    }

}
