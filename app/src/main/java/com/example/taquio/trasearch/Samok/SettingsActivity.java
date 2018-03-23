package com.example.taquio.trasearch.Samok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taquio.trasearch.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    TextView cmdlogout, cmdFeedback, cmdAbout, cmdTips ;
    ImageView back;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();
        cmdlogout = findViewById(R.id.btnlogout);
        back = findViewById(R.id.ivBackArrow);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cmdFeedback = (TextView) findViewById(R.id.btnFeedback);
        cmdFeedback.setText(Html.fromHtml("<a href=\"mailto:edward.sampayan@gmail.com\">Send Feedback</a>"));
        cmdFeedback.setMovementMethod(LinkMovementMethod.getInstance());

        cmdlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SettingsActivity.this,GuestSearch.class));
                mAuth.signOut();
                finish();
            }
        });
    }
}
