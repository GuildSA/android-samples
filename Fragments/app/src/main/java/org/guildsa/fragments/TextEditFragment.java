package org.guildsa.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class TextEditFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private static int seekProgess = 20;
    private static EditText editText;

    TextEditListener activityCallback;

    public interface TextEditListener {
        public void onTextEditButtonClick(int fontsize, String text);
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        try {
            activityCallback = (TextEditListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ToolbarListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.text_edit_fragment, container, false);

        editText = (EditText) view.findViewById(R.id.editText1);
        editText.setText("New Text");

        final SeekBar seekbar = (SeekBar) view.findViewById(R.id.seekBar1);
        seekbar.setProgress(seekProgess);
        seekbar.setOnSeekBarChangeListener(this);

        final Button button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        return view;
    }

    public void buttonClicked(View view) {

        activityCallback.onTextEditButtonClick(seekProgess, editText.getText().toString());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        seekProgess = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
