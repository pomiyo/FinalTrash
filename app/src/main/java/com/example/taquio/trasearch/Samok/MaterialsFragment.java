package com.example.taquio.trasearch.Samok;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taquio.trasearch.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialsFragment extends Fragment {
    private View mMainView;


    public MaterialsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView = inflater
                .inflate(R.layout.fragment_materials,container,false);
        return mMainView;
    }

}
