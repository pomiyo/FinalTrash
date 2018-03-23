package com.example.taquio.trasearch.BusinessProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.taquio.trasearch.R;

/**
 * Created by Del Mar on 2/16/2018.
 */

public class BusinessSellFragment extends Fragment {
    private static final String TAG = "BusinessSellFragment";
    Button btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.business_profile_sell_fragment, container, false);
        btn = (Button) view.findViewById(R.id.btnAddSell);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), BusinessSell.class);
                startActivity(i);
            }
        });
        return view;
    }
}