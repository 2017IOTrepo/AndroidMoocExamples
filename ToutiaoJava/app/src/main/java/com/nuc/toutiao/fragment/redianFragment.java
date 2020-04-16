package com.nuc.toutiao.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuc.toutiao.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class redianFragment extends Fragment {


    public redianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.redian, container, false);
    }

}
