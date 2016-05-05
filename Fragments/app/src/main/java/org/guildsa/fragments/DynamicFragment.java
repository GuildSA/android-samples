package org.guildsa.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DynamicFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflating the layout for this fragment
        View view = inflater.inflate(R.layout.dynamic_fragment, null);

        return view;
    }
}