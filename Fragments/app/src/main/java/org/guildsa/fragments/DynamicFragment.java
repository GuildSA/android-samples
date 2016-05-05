package org.guildsa.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DynamicFragment extends Fragment {

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflating the layout for this fragment
        View view = inflater.inflate(R.layout.dynamic_fragment, null);

        textView = (TextView)view.findViewById(R.id.textView1);

        return view;
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public String getText() {
        return textView.toString();
    }
}